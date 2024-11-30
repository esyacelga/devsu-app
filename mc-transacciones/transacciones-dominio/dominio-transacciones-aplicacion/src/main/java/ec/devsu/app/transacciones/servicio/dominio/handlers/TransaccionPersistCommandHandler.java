package ec.devsu.app.transacciones.servicio.dominio.handlers;

import ec.devsu.app.transacciones.servicio.dominio.dto.request.RequestCuenta;
import ec.devsu.app.transacciones.servicio.dominio.dto.request.RequestCuentaActualizacion;
import ec.devsu.app.transacciones.servicio.dominio.dto.request.RequestMovimiento;
import ec.devsu.app.transacciones.servicio.dominio.dto.response.ResponseCuenta;
import ec.devsu.app.transacciones.servicio.dominio.dto.response.ResponseMovimiento;
import ec.devsu.app.transacciones.servicio.dominio.helpers.TransaccionPersistHelper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class TransaccionPersistCommandHandler {
    public final TransaccionPersistHelper transaccionPersistHelper;

    public TransaccionPersistCommandHandler(TransaccionPersistHelper transaccionPersistHelper) {
        this.transaccionPersistHelper = transaccionPersistHelper;
    }

    public ResponseMovimiento insertarMovimiento(RequestMovimiento requestMovimiento) {
        return transaccionPersistHelper.insertarMovimiento(requestMovimiento);
    }
    public ResponseCuenta insertarCuentaPersona(RequestCuenta requestCuenta) {
        return transaccionPersistHelper.insertarCuentaPersona(requestCuenta);
    }

    public ResponseCuenta actualizarCuentaPersona(RequestCuentaActualizacion cuentaActualizacion) {
        return transaccionPersistHelper.actualizarCuentaPersona(RequestCuentaActualizacion.builder()
                .tipoCuenta(cuentaActualizacion.getTipoCuenta())
                .numeroCuenta(cuentaActualizacion.getNumeroCuenta())
                .saldo(cuentaActualizacion.getSaldo())
                .build());
    }




}
