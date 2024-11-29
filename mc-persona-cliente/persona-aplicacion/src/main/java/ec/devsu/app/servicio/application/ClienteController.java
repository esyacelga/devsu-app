package ec.devsu.app.servicio.application;

import ec.devsu.app.servicio.dominio.dto.request.RequestCliente;
import ec.devsu.app.servicio.dominio.dto.response.ResponseCliente;
import ec.devsu.app.servicio.dominio.dto.response.ResponseClientePersona;
import ec.devsu.app.servicio.dominio.puertos.input.IClienteAppService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(produces = "application/json")
@RequiredArgsConstructor
public class ClienteController {

    private final IClienteAppService clienteAppService;




    @GetMapping("/clientes/{idCliente}")
    public ResponseEntity<ResponseClientePersona> buscarClientePorID(@PathVariable String idCliente) {
        return ResponseEntity.ok(clienteAppService.buscarClientePorId(idCliente));

    }
}
