package com.larissa.commandapi.service.processor;

import com.larissa.commandapi.model.CommandRequest;

public interface CommandProcessor {
    // Verifica se o processador suporta o tipo de comando
    boolean supports(String commandType);
    
    // Processa o comando recebido
    void process(CommandRequest command);
}
