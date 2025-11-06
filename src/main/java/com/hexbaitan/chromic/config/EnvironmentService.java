package com.hexbaitan.chromic.config;


import io.github.cdimascio.dotenv.Dotenv;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Service;

@Service
public class EnvironmentService {

    private final Dotenv dotenv;

    public EnvironmentService() {
        dotenv = Dotenv.load();
    }


    public String getJwtSecretKey() {
        return dotenv.get("JWT_SECRET_KEY");
    }
}
