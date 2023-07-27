package com.example.demo.controller;


import com.example.demo.entities.User;
import com.example.demo.service.RelatorioService;
import com.example.demo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping(value = "/relatorios")
public class RelatorioController {
    @Autowired
    private RelatorioService relatorioService;

    @GetMapping
    public String getRelatorio() throws IOException, ClassNotFoundException {
        return relatorioService.getRelatorio();
    }
}
