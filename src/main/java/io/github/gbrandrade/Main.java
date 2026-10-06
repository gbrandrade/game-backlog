package io.github.gbrandrade;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class Main {
    public static void main(String[] args) {
        // Isso aqui vai "ligar" o servidor web na porta 8080
        SpringApplication.run(Main.class, args);
        System.out.println("Servidor rodando! Acesse http://localhost:8080");
    }
}