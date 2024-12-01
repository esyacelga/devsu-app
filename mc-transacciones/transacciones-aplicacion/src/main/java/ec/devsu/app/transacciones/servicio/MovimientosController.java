package ec.devsu.app.transacciones.servicio;

import ec.devsu.app.transacciones.servicio.dominio.dto.MovimientoRegistroDto;
import ec.devsu.app.transacciones.servicio.dominio.dto.request.RequestMovimiento;
import ec.devsu.app.transacciones.servicio.dominio.dto.request.RequestMovimientoActualizacion;
import ec.devsu.app.transacciones.servicio.dominio.dto.response.ResponseMovimiento;
import ec.devsu.app.transacciones.servicio.dominio.exception.TransaccionDomainException;
import ec.devsu.app.transacciones.servicio.dominio.puertos.input.ITransaccionesAppService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

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

    @PutMapping
    public ResponseEntity<ResponseMovimiento> actualizarMovimiento(@RequestBody RequestMovimientoActualizacion requestMovimiento) {
        return ResponseEntity.ok(transaccionesAppService.actualizarMovimiento(requestMovimiento));
    }

    @GetMapping("/{uuidMovimiento}")
    public MovimientoRegistroDto movimientoRegistroDto(@PathVariable String uuidMovimiento) throws TransaccionDomainException {
        return transaccionesAppService.buscarMovimientoPorId(UUID.fromString(uuidMovimiento));
    }
}
