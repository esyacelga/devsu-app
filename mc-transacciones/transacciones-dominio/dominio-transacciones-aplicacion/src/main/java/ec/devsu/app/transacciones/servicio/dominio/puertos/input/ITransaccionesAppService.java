package ec.devsu.app.transacciones.servicio.dominio.puertos.input;

import ec.devsu.app.transacciones.servicio.dominio.dto.CuentaDto;
import ec.devsu.app.transacciones.servicio.dominio.dto.MovimientoRegistroDto;
import ec.devsu.app.transacciones.servicio.dominio.dto.request.RequestCuenta;
import ec.devsu.app.transacciones.servicio.dominio.dto.request.RequestCuentaActualizacion;
import ec.devsu.app.transacciones.servicio.dominio.dto.request.RequestMovimiento;
import ec.devsu.app.transacciones.servicio.dominio.dto.response.ResponseCuenta;
import ec.devsu.app.transacciones.servicio.dominio.dto.response.ResponseMovimiento;
import jakarta.validation.Valid;

import java.util.UUID;

public interface ITransaccionesAppService {

    ResponseCuenta insertarCuentaPersona(@Valid RequestCuenta requestCuenta);

    ResponseCuenta actualizarCuentaPersona(@Valid RequestCuentaActualizacion cuentaActualizacion);

    ResponseMovimiento insertarMovimiento(@Valid RequestMovimiento requestMovimiento);

    CuentaDto obtenerCuentaPorNumero(@Valid String numeroCuenta);
    public MovimientoRegistroDto buscarMovimientoPorId(@Valid UUID uuidMovimiento);

}
