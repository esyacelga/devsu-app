package ec.devsu.app.transacciones.servicio.acceso.datos.repository.impl;

import ec.devsu.app.transacciones.servicio.acceso.datos.entity.Cuenta;
import ec.devsu.app.transacciones.servicio.acceso.datos.repository.ICuentaRepository;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityNotFoundException;
import jakarta.persistence.PersistenceContext;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.util.Optional;

@Repository
public class CuentaRepositoryImpl implements ICuentaRepository {
    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public Cuenta insertarCuentaPersona(Cuenta cuenta) {
        entityManager.persist(cuenta);
        return cuenta;
    }

    @Override
    public Integer obtenerSiguienteSecuencial() {
        String hql = "SELECT COALESCE(MAX(c.numeroCuenta), '0000') FROM Cuenta c";
        String ultimoSecuencial = entityManager.createQuery(hql, String.class).getSingleResult();
        if (ultimoSecuencial == null)
            return 1;
        return Integer.parseInt(ultimoSecuencial) + 1;
    }

    @Override
    public Optional<BigDecimal> obtenerSaldoActual(String numeroCuenta) {
        String hql = "SELECT c.saldoInicialEstado FROM Cuenta c WHERE c.numeroCuenta = :numeroCuenta";
        return Optional.of(entityManager.createQuery(hql, BigDecimal.class)
                .setParameter("numeroCuenta", numeroCuenta)
                .getSingleResult());
    }


    @Override
    public Cuenta actualizarCuenta(Cuenta cuenta) throws EntityNotFoundException {
        Cuenta cu = entityManager.find(Cuenta.class, cuenta.getId());
        if (cu == null) {
            throw new EntityNotFoundException("No se encontró la cuenta con número " + cuenta.getNumeroCuenta());
        }
        cu.setTipoCuenta(cuenta.getTipoCuenta());
        cu.setNumeroCuenta(cuenta.getNumeroCuenta());
        return entityManager.merge(cuenta);
    }

    @Override
    public void actualizarNuevoSaldo(String numeroCuenta, BigDecimal nuevoSaldo) throws EntityNotFoundException {
        String hql = "UPDATE Cuenta c SET c.saldoInicialEstado = :nuevoSaldo WHERE c.numeroCuenta = :numeroCuenta";
        int filasActualizadas = entityManager.createQuery(hql)
                .setParameter("nuevoSaldo", nuevoSaldo)
                .setParameter("numeroCuenta", numeroCuenta)
                .executeUpdate();
        if (filasActualizadas == 0) {
            throw new EntityNotFoundException("No se encontró la cuenta con número " + numeroCuenta);
        }
    }

    @Override
    public Optional<Cuenta> obtenerCuentaPorNumero(String numeroCuenta) {
        String hql = "SELECT c FROM Cuenta c WHERE c.numeroCuenta = :numeroCuenta";
        Cuenta cuenta = entityManager.createQuery(hql, Cuenta.class)
                .setParameter("numeroCuenta", numeroCuenta)
                .getSingleResult();
        return Optional.of(cuenta);
    }
}