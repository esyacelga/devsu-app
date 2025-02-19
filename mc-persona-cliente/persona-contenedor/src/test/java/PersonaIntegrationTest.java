import ec.banca.app.persona.servicio.contendor.ClientePersonaServiceApplication;
import ec.banca.app.persona.servicio.dominio.dto.response.ResponseClientePersona;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@Slf4j
@SpringBootTest(classes = ClientePersonaServiceApplication.class, webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
@Tag("integation")
public class PersonaIntegrationTest {

    @LocalServerPort
    private int port;

    @Autowired
    private TestRestTemplate restTemplate;

    @Test
    @Order(1)
    @DisplayName("Prueba de búsqueda de cliente por ID")
    public void testBuscarClientePorId() {
        String clienteId = "CLI001";
        String url = "http://localhost:" + port + "/clientes/" + clienteId;
        ResponseEntity<ResponseClientePersona> response = restTemplate.getForEntity(url, ResponseClientePersona.class);
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertNotNull(response.getBody());
        assertEquals(clienteId, response.getBody().getClienteId());
        log.info("Cliente encontrado por ID: {}", response.getBody().getNombre());
    }

    @Test
    @Order(2)
    @DisplayName("Prueba de búsqueda de cliente por identificación")
    public void testBuscarClientePorIdentificacion() {
        String identificacion = "1721737243";
        String url = "http://localhost:" + port + "/clientes/buscarClientePorIdentificacion/" + identificacion;
        ResponseEntity<ResponseClientePersona> response = restTemplate.getForEntity(url, ResponseClientePersona.class);
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertNotNull(response.getBody());
        assertEquals(identificacion, response.getBody().getIdentificacion());
        log.info("Cliente encontrado por identificación: {}", response.getBody().getNombre());
    }

    @Test
    @Order(3)
    @DisplayName("Prueba de cliente no encontrado")
    public void testBuscarClienteNoExistente() {
        String clienteId = "NO_EXISTE";
        String url = "http://localhost:" + port + "/clientes/" + clienteId;
        ResponseEntity<ResponseClientePersona> response = restTemplate.getForEntity(url, ResponseClientePersona.class);
        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
        log.info("Cliente con ID {} no encontrado, respuesta correcta.", clienteId);
    }
}
