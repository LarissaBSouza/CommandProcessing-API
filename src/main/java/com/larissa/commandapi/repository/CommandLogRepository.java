package com.larissa.commandapi.repository;

import java.time.LocalDateTime;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class CommandLogRepository {

    // Injeção do JdbcTemplate para operações com o banco de dados
    private final JdbcTemplate jdbcTemplate;

    // Construtor para injeção do JdbcTemplate
    public CommandLogRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    // Método para registrar o log do comando no banco de dados
    public void logCommand(String deviceId, String type, String payloadJson, String status) {
        String sql = "INSERT INTO COMMAND_LOG (device_id, command_type, payload, status, created_at) VALUES (?, ?, ?, ?, ?)";
        
        jdbcTemplate.update(sql, deviceId, type, payloadJson, status, LocalDateTime.now());
    }
}