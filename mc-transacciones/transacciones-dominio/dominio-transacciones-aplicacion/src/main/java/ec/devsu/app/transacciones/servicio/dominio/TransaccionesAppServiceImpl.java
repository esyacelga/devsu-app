package ec.devsu.app.transacciones.servicio.dominio;

import ec.devsu.app.transacciones.servicio.dominio.dto.CuentaDto;
import ec.devsu.app.transacciones.servicio.dominio.dto.MovimientoRegistroDto;
import ec.devsu.app.transacciones.servicio.dominio.dto.request.RequestCuenta;
import ec.devsu.app.transacciones.servicio.dominio.dto.request.RequestCuentaActualizacion;
import ec.devsu.app.transacciones.servicio.dominio.dto.request.RequestMovimiento;
import ec.devsu.app.transacciones.servicio.dominio.dto.response.ResponseCuenta;
import ec.devsu.app.transacciones.servicio.dominio.dto.response.ResponseMovimiento;
import ec.devsu.app.transacciones.servicio.dominio.handlers.CuentaQueryCommandHandler;
import ec.devsu.app.transacciones.servicio.dominio.handlers.TransaccionPersistCommandHandler;
import ec.devsu.app.transacciones.servicio.dominio.handlers.TransaccionesQueryCommandHandler;
import ec.devsu.app.transacciones.servicio.dominio.puertos.input.ITransaccionesAppService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

import java.util.UUID;

@Slf4j
@Validated
@Service
public class TransaccionesAppServiceImpl implements ITransaccionesAppService {
    private final TransaccionesQueryCommandHandler transaccionesQueryCommandHandler;
    private final TransaccionPersistCommandHandler transaccionPersistCommandHandler;
    private final CuentaQueryCommandHandler cuentaQueryCommandHandler;

    public TransaccionesAppServiceImpl(TransaccionesQueryCommandHandler transaccionesQueryCommandHandler,
                                       TransaccionPersistCommandHandler transaccionPersistCommandHandler,
                                       CuentaQueryCommandHandler cuentaQueryCommandHandler) {
        this.transaccionesQueryCommandHandler = transaccionesQueryCommandHandler;
        this.transaccionPersistCommandHandler = transaccionPersistCommandHandler;
        this.cuentaQueryCommandHandler = cuentaQueryCommandHandler;
    }

    @Override
    public CuentaDto obtenerCuentaPorNumero(String numeroCuenta) {
        return cuentaQueryCommandHandler.obtenerCuentaPorNumero(numeroCuenta);
    }

    @Override
    public MovimientoRegistroDto buscarMovimientoPorId(UUID uuidMovimiento) {
        return transaccionesQueryCommandHandler.buscarMovimientoPorId(uuidMovimiento);

    }

    @Override
    public ResponseCuenta insertarCuentaPersona(RequestCuenta requestCuenta) {
        return transaccionPersistCommandHandler.insertarCuentaPersona(requestCuenta);
    }

    @Override
    public ResponseCuenta actualizarCuentaPersona(RequestCuentaActualizacion cuentaActualizacion) {
        return transaccionPersistCommandHandler.actualizarCuentaPersona(cuentaActualizacion);
    }

    @Override
    public ResponseMovimiento insertarMovimiento(RequestMovimiento requestMovimiento) {
        return transaccionPersistCommandHandler.insertarMovimiento(requestMovimiento);
    }


}
