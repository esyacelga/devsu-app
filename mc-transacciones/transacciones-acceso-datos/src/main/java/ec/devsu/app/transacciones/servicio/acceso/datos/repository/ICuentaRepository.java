package ec.devsu.app.transacciones.servicio.acceso.datos.repository;

import ec.devsu.app.transacciones.servicio.acceso.datos.entity.Cuenta;
import jakarta.persistence.EntityNotFoundException;

import java.math.BigDecimal;
import java.util.Optional;

public interface ICuentaRepository {

    public Cuenta insertarCuentaPersona(Cuenta cuenta);

    Integer obtenerSiguienteSecuencial();

    Optional<BigDecimal> obtenerSaldoActual(String numeroCuenta);

    Cuenta actualizarCuenta(Cuenta cuenta) throws EntityNotFoundException;

    void actualizarNuevoSaldo(String numeroCuenta, BigDecimal nuevoSaldo) throws EntityNotFoundException;

    Optional<Cuenta> obtenerCuentaPorNumero(String numeroCuenta);
}
