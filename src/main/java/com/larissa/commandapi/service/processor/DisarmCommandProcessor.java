package com.larissa.commandapi.service.processor;

import com.larissa.commandapi.model.CommandRequest;
import org.springframework.stereotype.Component;

@Component
public class DisarmCommandProcessor implements CommandProcessor {

    @Override
    public boolean supports(String commandType) {
        return "DISARM".equalsIgnoreCase(commandType);
        // Verifica se o processador suporta o comando do tipo DISARM
    }

    @Override
    public void process(CommandRequest command) {
        // Lógica específica para processar o comando DISARM
        System.out.println("Processando comando DISARM para o device: " + command.getDeviceId());
    }
}
