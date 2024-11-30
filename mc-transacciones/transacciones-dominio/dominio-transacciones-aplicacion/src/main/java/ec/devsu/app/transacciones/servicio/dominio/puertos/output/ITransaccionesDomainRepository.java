package ec.devsu.app.transacciones.servicio.dominio.puertos.output;

import ec.devsu.app.transacciones.servicio.dominio.dto.MovimientoRegistroDto;
import ec.devsu.app.transacciones.servicio.dominio.dto.request.RequestMovimiento;

import java.util.UUID;

public interface ITransaccionesDomainRepository {
    public MovimientoRegistroDto insertarMovimiento(RequestMovimiento requestMovimiento);

    public MovimientoRegistroDto buscarMovimientoPorId(UUID uuidMovimiento);

    public MovimientoRegistroDto actualizarMovimiento(RequestMovimiento requestMovimiento);


}
