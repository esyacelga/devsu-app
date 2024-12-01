package ec.devsu.app.transacciones.servicio;

import ec.devsu.app.transacciones.servicio.dominio.dto.request.RequestMovimiento;
import ec.devsu.app.transacciones.servicio.dominio.dto.response.ResponseMovimiento;
import ec.devsu.app.transacciones.servicio.dominio.puertos.input.ITransaccionesAppService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequestMapping(value = "/movimientos", produces = "application/json")
@RequiredArgsConstructor
public class MovimientosController {
    private final ITransaccionesAppService transaccionesAppService;

    @PostMapping
    public ResponseEntity<ResponseMovimiento> insertarMovimiento(@RequestBody RequestMovimiento requestMovimiento) {
        return ResponseEntity.ok(transaccionesAppService.insertarMovimiento(requestMovimiento));
    }

}
