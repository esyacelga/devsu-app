package ec.devsu.app.transacciones.servicio.dominio.puertos.input;

import ec.devsu.app.transacciones.servicio.dominio.dto.CuentaDto;
import ec.devsu.app.transacciones.servicio.dominio.dto.request.RequestCuenta;
import ec.devsu.app.transacciones.servicio.dominio.dto.request.RequestCuentaActualizacion;
import ec.devsu.app.transacciones.servicio.dominio.dto.response.ResponseCuenta;

public interface ITransaccionesAppService {

    ResponseCuenta insertarCuentaPersona(RequestCuenta requestCuenta);

    ResponseCuenta actualizarCuentaPersona(RequestCuentaActualizacion cuentaActualizacion);

    CuentaDto obtenerCuentaPorNumero(String numeroCuenta);
}
