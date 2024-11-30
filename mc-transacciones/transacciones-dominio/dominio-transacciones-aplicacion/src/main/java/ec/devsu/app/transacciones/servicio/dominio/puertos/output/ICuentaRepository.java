package ec.devsu.app.transacciones.servicio.dominio.puertos.output;

import ec.devsu.app.transacciones.servicio.dominio.dto.CuentaDto;
import ec.devsu.app.transacciones.servicio.dominio.dto.request.RequestCuenta;

public interface ICuentaRepository {
    public CuentaDto insertarCuentaPersona(RequestCuenta requestCuenta, String numeroCuenta);

    Integer obtenerSiguienteSecuencial();

    CuentaDto actualizarCuenta(CuentaDto cuentaDto);

    CuentaDto obtenerCuentaPorNumero(String numeroCuenta);

}
