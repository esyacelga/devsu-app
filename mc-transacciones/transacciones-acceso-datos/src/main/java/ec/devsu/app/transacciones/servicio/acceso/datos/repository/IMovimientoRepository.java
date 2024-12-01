package ec.devsu.app.transacciones.servicio.acceso.datos.repository;

import ec.devsu.app.transacciones.servicio.acceso.datos.entity.Movimientos;

import java.util.Optional;
import java.util.UUID;

public interface IMovimientoRepository {

    public Movimientos insertarMovimiento(Movimientos movimientos);

    public Optional<Movimientos> buscarMovimientoPorId(UUID uuidMovimiento);

    public Movimientos actualizarMovimiento(Movimientos movimientos);
}
