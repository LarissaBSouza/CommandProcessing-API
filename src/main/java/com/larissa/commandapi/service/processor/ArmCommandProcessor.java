package com.larissa.commandapi.service.processor;
import com.larissa.commandapi.model.CommandRequest;
import org.springframework.stereotype.Component;

@Component
public class ArmCommandProcessor implements CommandProcessor {

    @Override
    public boolean supports(String commandType) {
        return "ARM".equalsIgnoreCase(commandType);
        // Verifica se o processador suporta o comando do tipo ARM
    }

    @Override
    public void process(CommandRequest command) {
        
        // Lógica específica para processar o comando ARM
        System.out.println("Processando comando ARM para o device: " + command.getDeviceId());
    }
}
