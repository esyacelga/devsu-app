package ec.devsu.app.transacciones.servicio.dominio.puertos.output;

import ec.devsu.app.transacciones.servicio.dominio.dto.CuentaDto;
import ec.devsu.app.transacciones.servicio.dominio.dto.MovimientoRegistroDto;
import ec.devsu.app.transacciones.servicio.dominio.dto.request.RequestCuenta;
import ec.devsu.app.transacciones.servicio.dominio.dto.request.RequestMovimiento;

public interface ITransaccionesRepository {
    public MovimientoRegistroDto insertarMovimiento(RequestMovimiento requestMovimiento);
    public CuentaDto insertarCuentaPersona(RequestCuenta requestCuenta, String numeroCuenta);

    public CuentaDto actualizarCuentaPersona(CuentaDto cuentaDto);

}
