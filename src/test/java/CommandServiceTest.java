import com.larissa.commandapi.model.CommandRequest;
import com.larissa.commandapi.repository.CommandLogRepository;
import com.larissa.commandapi.service.CommandService;
import com.larissa.commandapi.service.processor.CommandProcessor;
import java.util.Collections;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;


import static org.junit.jupiter.api.Assertions.assertThrows;

@ExtendWith(MockitoExtension.class)
class CommandServiceTest { //

    @Mock CommandLogRepository repository;
    @Mock CommandProcessor processor;
    

    @Test
    void deveLancarErroParaTipoNaoSuportado() {
        //Arrange
        CommandRequest request = new CommandRequest();
        request.setType("UNKNOWN");
        
        //Criando o serviço com uma lista vazia de processadores
        CommandService service = new CommandService(Collections.emptyList(), repository, null);

        //Act & Assert
        assertThrows(IllegalArgumentException.class, () -> service.processCommand(request));
    }
}
