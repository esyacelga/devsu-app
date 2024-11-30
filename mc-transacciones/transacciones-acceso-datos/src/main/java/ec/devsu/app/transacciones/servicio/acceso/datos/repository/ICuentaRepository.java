package ec.devsu.app.transacciones.servicio.acceso.datos.repository;

import ec.devsu.app.transacciones.servicio.acceso.datos.entity.Cuenta;

import java.math.BigDecimal;

public interface ICuentaRepository {

    public Cuenta insertarCuentaPersona(Cuenta cuenta);

    Integer obtenerSiguienteSecuencial();

    BigDecimal obtenerSaldoActual(String numeroCuenta);

    Cuenta actualizarCuenta(Cuenta cuenta);

    Cuenta actualizarNuevoSaldo(String numeroCuenta, BigDecimal nuevoSaldo);

    Cuenta obtenerCuentaPorNumero(String numeroCuenta);
}
