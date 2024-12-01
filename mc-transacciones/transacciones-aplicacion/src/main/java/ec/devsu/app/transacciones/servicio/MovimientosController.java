package ec.devsu.app.transacciones.servicio;

import ec.devsu.app.transacciones.servicio.dominio.puertos.input.ITransaccionesAppService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequestMapping(value = "/movienientos", produces = "application/json")
@RequiredArgsConstructor
public class MovimientosController {
    private final ITransaccionesAppService transaccionesAppService;



}
