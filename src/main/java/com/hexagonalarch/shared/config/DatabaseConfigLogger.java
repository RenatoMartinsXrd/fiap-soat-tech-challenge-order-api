package com.hexagonalarch.shared.config;

import jakarta.annotation.PostConstruct;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class DatabaseConfigLogger {

    @Value("${spring.datasource.url}")
    private String dataSourceUrl;

    @PostConstruct
    public void logDatabaseUrl() {
        log.info("Banco de Dados conectado: {}", dataSourceUrl);
    }
}

