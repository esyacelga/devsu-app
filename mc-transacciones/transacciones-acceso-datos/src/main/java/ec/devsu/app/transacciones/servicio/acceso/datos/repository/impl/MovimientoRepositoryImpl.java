package ec.devsu.app.transacciones.servicio.acceso.datos.repository.impl;

import ec.devsu.app.transacciones.servicio.acceso.datos.entity.Movimientos;
import ec.devsu.app.transacciones.servicio.acceso.datos.repository.IMovimientoRepository;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public class MovimientoRepositoryImpl implements IMovimientoRepository {
    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public Movimientos insertarMovimiento(Movimientos movimientos) {
        entityManager.persist(movimientos);
        return movimientos;
    }

    @Override
    public Optional<Movimientos> buscarMovimientoPorId(UUID uuidMovimiento) {
        return Optional.ofNullable(entityManager.find(Movimientos.class, uuidMovimiento));
    }

    @Override
    public Movimientos actualizarMovimiento(Movimientos movimientos) {
        return entityManager.merge(movimientos);
    }
}
