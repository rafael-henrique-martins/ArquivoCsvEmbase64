package com.example.demo.config;

import com.example.demo.entities.User;
import com.example.demo.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

import java.util.Arrays;

@Configuration
@Profile("test")
public class TestConfig implements CommandLineRunner {

    @Autowired
    private UserRepository userRepository;

    @Override
    public void run(String... args) throws Exception{
        User u1 = new User(null, "Maria Brown", "maria@gmail.com", "988888888", "123456");
        User u2 = new User(null, "Alex Green", "alex@gmail.com", "977777777", "123456");
        User u3 = new User(null, "Rafael", "Rafael@gmail.com", "911111111", "123456");
        User u4 = new User(null, "João Victor", "Victor@gmail.com", "922222222", "123456");
        User u5 = new User(null, "Pedro", "Pedro@gmail.com", "933333333", "123456");
        User u6 = new User(null, "Talita", "Talita@gmail.com", "974444444", "123456");
        User u7 = new User(null, "Sebastião", "Sebastiao@gmail.com", "985555555", "123456");
        User u8 = new User(null, "Carmem", "Carmem@gmail.com", "9666666666", "123456");
        User u9 = new User(null, "Mario", "Mario@gmail.com", "999999999", "123456");
        User u10 = new User(null, "Van Damme", "Damme@gmail.com", "977777777", "123456");

        userRepository.saveAll(Arrays.asList(u1,u2,u3,u4,u5,u6,u7,u8,u9,u10));
    }
}
