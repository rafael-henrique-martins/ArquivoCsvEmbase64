package com.example.demo.service.serviceImpl;

import com.example.demo.entities.User;
import com.example.demo.service.RelatorioService;
import com.opencsv.CSVWriter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Base64;
import java.util.List;


@Service
public class RelatorioServiceImpl implements RelatorioService {
    @Autowired
    private UserServiceImpl userService;

    private String codificarArquivovParaBinarioBase64(File arquivo) throws IOException {
        byte[] arquivoCodificado = Files.readAllBytes(Paths.get(arquivo.toURI()));
        return Base64.getEncoder().encodeToString(arquivoCodificado);
    }

    private File gerarArquivoEmMemoria(String prefixoArquivo, String[] cabecalhoPlanilha, List<String[]> listaDadosPlanilha) throws ClassNotFoundException {
        String local = "endereco para criar o arquivo";
        File arquivo;

        try {
            arquivo = File.createTempFile(prefixoArquivo, ".csv", new File(local));
            arquivo.deleteOnExit();

            BufferedWriter out = new BufferedWriter(
                    new OutputStreamWriter(new FileOutputStream(arquivo), StandardCharsets.UTF_8));

            CSVWriter csvWriter = new CSVWriter(out);
            csvWriter.writeNext(cabecalhoPlanilha);

            csvWriter.writeAll(listaDadosPlanilha);
            csvWriter.flush();
            out.close();

        } catch (IOException e) {
            String msg = "Ocorreu um erro ao tentar criar o arquivo em memoria";
            throw new ClassNotFoundException(msg);
        }
        return arquivo;
    }

    public String getRelatorio() throws ClassNotFoundException, IOException {
        File arquivo;

        String[] cabecalhoPlanilha = {"id", "Nome", "email", "telefone", "senha"};

        List<String[]> listaDadosPlanilha = new ArrayList<>();
        List<User> listaDadosUsers= userService.findAll();

        for (User dados : listaDadosUsers) {
            listaDadosPlanilha.add(new String[]{
                    String.valueOf(dados.getId()),
                    dados.getName(),
                    dados.getEmail(),
                    dados.getPhone(),
                    dados.getPassword()
            });
        }

        String prefixoAqruivo="rhm";

        arquivo = gerarArquivoEmMemoria(prefixoAqruivo, cabecalhoPlanilha, listaDadosPlanilha);

        return codificarArquivovParaBinarioBase64(arquivo);
    }
}
