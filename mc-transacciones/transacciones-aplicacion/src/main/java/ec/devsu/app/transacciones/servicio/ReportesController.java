package ec.devsu.app.transacciones.servicio;

import ec.devsu.app.transacciones.servicio.dominio.dto.MovientoReporte;
import ec.devsu.app.transacciones.servicio.dominio.exception.TransaccionDomainException;
import ec.devsu.app.transacciones.servicio.dominio.puertos.input.ITransaccionesAppService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeParseException;
import java.util.List;

@Slf4j
@RestController
@RequestMapping(value = "/reportes", produces = "application/json")
@RequiredArgsConstructor
public class ReportesController {
    private final ITransaccionesAppService transaccionesAppService;

    @GetMapping("")
    public List<MovientoReporte> obtenerMovimientosPorRangoDeFechas(@RequestParam("fechaInicio") String fechaInicio,
                                                                    @RequestParam("fechaFin") String fechaFin) {
        try {
            LocalDateTime inicio = LocalDate.parse(fechaInicio).atStartOfDay();
            LocalDateTime fin = LocalDate.parse(fechaFin).atTime(23, 59, 59);
            if (inicio.isAfter(fin)) {
                throw new TransaccionDomainException("La fecha inicial no puede ser posterior a la fecha final.");
            }
            return transaccionesAppService.obtenerMovimientosPorRango(inicio, fin);
        } catch (DateTimeParseException e) {
            throw new TransaccionDomainException("Formato de fecha inválido. Use el formato 'yyyy-MM-dd'.", e);
        }
    }
}