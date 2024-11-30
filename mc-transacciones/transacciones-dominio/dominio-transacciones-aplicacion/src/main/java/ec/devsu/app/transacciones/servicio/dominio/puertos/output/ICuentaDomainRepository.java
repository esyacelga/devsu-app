package ec.devsu.app.transacciones.servicio.dominio.puertos.output;

import ec.devsu.app.transacciones.servicio.dominio.dto.CuentaDto;
import ec.devsu.app.transacciones.servicio.dominio.dto.request.RequestCuenta;

import java.math.BigDecimal;

public interface ICuentaDomainRepository {
    public CuentaDto insertarCuentaPersona(RequestCuenta requestCuenta, String numeroCuenta);

    Integer obtenerSiguienteSecuencial();

    BigDecimal obtenerSaldoActual(String numeroCuenta);


    CuentaDto actualizarCuenta(CuentaDto cuentaDto);

    CuentaDto actualizarNuevoSaldo(String numeroCuenta, BigDecimal nuevoSaldo);

    CuentaDto obtenerCuentaPorNumero(String numeroCuenta);

}
