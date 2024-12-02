package ec.devsu.app.transacciones.servicio.dominio.handlers;

import ec.devsu.app.transacciones.servicio.dominio.dto.MovientoReporte;
import ec.devsu.app.transacciones.servicio.dominio.dto.MovimientoRegistroDto;
import ec.devsu.app.transacciones.servicio.dominio.exception.TransaccionDomainException;
import ec.devsu.app.transacciones.servicio.dominio.helpers.CuentaQueryHelper;
import ec.devsu.app.transacciones.servicio.dominio.helpers.TransaccionQueryHelper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Slf4j
@Component
public class TransaccionesQueryCommandHandler {
    private final TransaccionQueryHelper cuentaQueryHelper;

    public TransaccionesQueryCommandHandler(TransaccionQueryHelper cuentaQueryHelper) {
        this.cuentaQueryHelper = cuentaQueryHelper;
    }

    public MovimientoRegistroDto buscarMovimientoPorId(UUID uuidMovimiento) throws TransaccionDomainException {
        return cuentaQueryHelper.buscarMovimientoPorId(uuidMovimiento);
    }

    public List<MovientoReporte> obtenerMovimientosPorRango(LocalDateTime fechaInicial, LocalDateTime fechaFinal) throws TransaccionDomainException {
        return cuentaQueryHelper.obtenerMovimientosPorRango(fechaInicial, fechaFinal);
    }
}

