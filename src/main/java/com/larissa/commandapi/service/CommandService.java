package com.larissa.commandapi.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.larissa.commandapi.model.CommandRequest;
import com.larissa.commandapi.repository.CommandLogRepository;
import com.larissa.commandapi.service.processor.CommandProcessor;
import java.util.List;
import org.springframework.stereotype.Service;



@Service
public class CommandService {

    private final List<CommandProcessor> processors; // Spring injeta todos os processadores aqui automaticamente
    private final CommandLogRepository repository;
    private final ObjectMapper objectMapper;

    // Construtor para injeção de dependências
    public CommandService(List<CommandProcessor> processors, CommandLogRepository repository, ObjectMapper objectMapper) {
        this.processors = processors;
        this.repository = repository;
        this.objectMapper = objectMapper;
    }

    // Método principal para processar comandos
    public void processCommand(CommandRequest request) throws Exception {
        
        //Seleciona o processador adequado
        CommandProcessor processor = processors.stream()
                .filter(p -> p.supports(request.getType()))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("Tipo de comando não suportado: " + request.getType()));

        // Processa o comando
        try {
            processor.process(request);
            
            //Log de Sucesso
            String payloadJson = objectMapper.writeValueAsString(request.getPayload());
            repository.logCommand(request.getDeviceId(), request.getType(), payloadJson, "SUCCESS");
            
        } catch (Exception e) {

            //Log de Erro
            try {
                String payloadJson = objectMapper.writeValueAsString(request.getPayload());
                repository.logCommand(request.getDeviceId(), request.getType(), payloadJson, "ERROR: " + e.getMessage());
                //Tenta converter o payload para JSON para logar
            } catch (Exception jsonEx) {
                //Se falhar, loga sem o payload
            }
            throw e; //Re-lança a exceção para tratamento adicional, se necessário
        }
    }
}
