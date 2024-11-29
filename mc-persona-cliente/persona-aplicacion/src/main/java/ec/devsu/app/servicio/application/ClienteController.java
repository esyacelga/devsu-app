package ec.devsu.app.servicio.application;

import ec.devsu.app.servicio.dominio.dto.request.RequestCliente;
import ec.devsu.app.servicio.dominio.dto.response.ResponseCliente;
import ec.devsu.app.servicio.dominio.puertos.input.IClienteAppService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequestMapping(value = "/clientes", produces = "application/json")
public class ClienteController {
    private final IClienteAppService clienteAppService;

    public ClienteController(IClienteAppService clienteAppService) {
        this.clienteAppService = clienteAppService;
    }

    @PostMapping("/")
    public ResponseEntity<ResponseCliente> insertarSolicitud(@RequestBody RequestCliente cliente) {
        return ResponseEntity.ok(clienteAppService.insertarCliente(cliente));
    }
}
