package ec.devsu.app.transacciones.servicio.dominio.puertos.output;

import ec.devsu.app.transacciones.servicio.dominio.dto.MovimientoRegistroDto;
import ec.devsu.app.transacciones.servicio.dominio.dto.request.RequestMovimiento;
import ec.devsu.app.transacciones.servicio.dominio.dto.request.RequestMovimientoActualizacion;
import ec.devsu.app.transacciones.servicio.dominio.exception.TransaccionDomainException;

import java.math.BigDecimal;
import java.util.UUID;

public interface ITransaccionesDomainRepository {
    public MovimientoRegistroDto insertarMovimiento(RequestMovimiento requestMovimiento, BigDecimal nuevoSaldo);

    public MovimientoRegistroDto buscarMovimientoPorId(UUID uuidMovimiento) throws TransaccionDomainException;

    public MovimientoRegistroDto actualizarMovimiento(RequestMovimientoActualizacion requestMovimiento) throws TransaccionDomainException;


}
