package ec.devsu.app.persona.servicio.application;

import ec.devsu.app.persona.servicio.dominio.dto.request.RequestCliente;
import ec.devsu.app.persona.servicio.dominio.dto.response.ResponseCliente;
import ec.devsu.app.persona.servicio.dominio.dto.response.ResponseClientePersona;
import ec.devsu.app.persona.servicio.dominio.puertos.input.IClienteAppService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/clientes", produces = "application/json")
@RequiredArgsConstructor
public class ClienteController {

    private final IClienteAppService clienteAppService;

    @PostMapping("/")
    public ResponseEntity<ResponseCliente> insertarSolicitud(@RequestBody RequestCliente cliente) {
        return ResponseEntity.ok(clienteAppService.insertarCliente(cliente));
    }


    @GetMapping("/{idCliente}")
    public ResponseEntity<ResponseClientePersona> buscarClientePorID(@PathVariable String idCliente) {
        return ResponseEntity.ok(clienteAppService.buscarClientePorId(idCliente));

    }
}
