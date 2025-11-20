package com.larissa.commandapi.controller;

import com.larissa.commandapi.model.CommandRequest;
import com.larissa.commandapi.service.CommandService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/commands")
public class CommandController {

    private final CommandService service;

    public CommandController(CommandService service) {
        this.service = service;
    }


    @PostMapping
    public ResponseEntity<String> receiveCommand(@RequestBody CommandRequest request) {
        //Chama o serviço para processar o comando
        try {
            service.processCommand(request);
            return ResponseEntity.ok("Comando processado com sucesso.");
            //Retorna sucesso se tudo ocorrer bem

        } catch (IllegalArgumentException e) {
            // Tipo de comando não suportado
            return ResponseEntity.badRequest().body(e.getMessage());

        } catch (Exception e) {
            //Erro genérico ao processar o comando
            return ResponseEntity.internalServerError().body("Erro interno ao processar comando.");
        }
    }
}
