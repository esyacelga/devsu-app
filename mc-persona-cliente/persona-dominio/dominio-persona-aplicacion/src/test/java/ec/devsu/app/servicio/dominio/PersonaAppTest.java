package ec.devsu.app.servicio.dominio;

import ec.devsu.app.servicio.dominio.dto.ClienteDto;
import ec.devsu.app.servicio.dominio.dto.request.RequestCliente;
import ec.devsu.app.servicio.dominio.dto.response.ResponseCliente;
import ec.devsu.app.servicio.dominio.puertos.input.IClienteAppService;
import ec.devsu.app.servicio.dominio.puertos.output.IClientePersonaRepository;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
@SpringBootTest(classes = PersonaTestConfiguration.class)
@Slf4j
public class PersonaAppTest {

    @Autowired
    IClienteAppService clienteAppService;

    @Autowired
    IClientePersonaRepository clientePersonaRepository;

    @Test
    @DisplayName("Registrar cliente ")
    public void testGenerarSecuencia() {
        when(clientePersonaRepository.insertarCliente(any(ClienteDto.class)))
                .thenReturn(ClienteDto.builder()
                        .uuidCliente(UUID.randomUUID())
                        .nombre("Santiago Yacelga")
                        .genero("Masculino")
                        .identificacion("1721737243")
                        .direccion("Quito-Ecuador")
                        .telefono("0979151957")
                        .estado("ACTIVO")
                        .edad("36")
                        .build());
        RequestCliente requestCliente = RequestCliente.builder()
                .nombre("Santiago Yacelga")
                .genero("Masculino")
                .identificacion("1721737243")
                .direccion("Quito-Ecuador")
                .telefono("0979151957")
                .estado("ACTIVO")
                .edad("36")
                .contrasenia("123456")
                .build();
        ResponseCliente cliente =  clienteAppService.insertarCliente(requestCliente);
        assertEquals("CLIENTE REGISTRADO CORRECTAMENTE", cliente.getMensaje());
    }
}