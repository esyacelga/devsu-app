package ec.devsu.app.transacciones.servicio.acceso.datos.adaptador;

import ec.devsu.app.transacciones.servicio.dominio.dto.MovimientoRegistroDto;
import ec.devsu.app.transacciones.servicio.dominio.dto.request.RequestMovimiento;
import ec.devsu.app.transacciones.servicio.dominio.puertos.output.ITransaccionesDomainRepository;
import org.springframework.stereotype.Component;

import java.util.UUID;
@Component
public class TransaccionesDomainRepositoryImpl implements ITransaccionesDomainRepository {
    @Override
    public MovimientoRegistroDto insertarMovimiento(RequestMovimiento requestMovimiento) {
        return null;
    }

    @Override
    public MovimientoRegistroDto buscarMovimientoPorId(UUID uuidMovimiento) {
        return null;
    }

    @Override
    public MovimientoRegistroDto actualizarMovimiento(RequestMovimiento requestMovimiento) {
        return null;
    }
}
