package ec.devsu.app.transacciones.servicio.dominio.puertos.input;

import ec.devsu.app.transacciones.servicio.dominio.dto.request.RequestCuenta;
import ec.devsu.app.transacciones.servicio.dominio.dto.request.RequestCuentaActualizacion;
import ec.devsu.app.transacciones.servicio.dominio.dto.request.RequestMovimiento;
import ec.devsu.app.transacciones.servicio.dominio.dto.response.ResponseCuenta;
import ec.devsu.app.transacciones.servicio.dominio.dto.response.ResponseMovimiento;

public interface ITransaccionesAppService {

    ResponseCuenta insertarCuentaPersona(RequestCuenta requestCuenta);

    ResponseCuenta actualizarCuentaPersona(RequestCuentaActualizacion cuentaActualizacion);

    ResponseMovimiento insertarMovimiento(RequestMovimiento requestMovimiento);


}
