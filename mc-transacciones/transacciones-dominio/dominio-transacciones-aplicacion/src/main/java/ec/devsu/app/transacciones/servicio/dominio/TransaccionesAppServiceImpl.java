package ec.devsu.app.transacciones.servicio.dominio;

import ec.devsu.app.transacciones.servicio.dominio.dto.CuentaDto;
import ec.devsu.app.transacciones.servicio.dominio.dto.MovientoReporte;
import ec.devsu.app.transacciones.servicio.dominio.dto.MovimientoRegistroDto;
import ec.devsu.app.transacciones.servicio.dominio.dto.request.RequestCuenta;
import ec.devsu.app.transacciones.servicio.dominio.dto.request.RequestCuentaActualizacion;
import ec.devsu.app.transacciones.servicio.dominio.dto.request.RequestMovimiento;
import ec.devsu.app.transacciones.servicio.dominio.dto.request.RequestMovimientoActualizacion;
import ec.devsu.app.transacciones.servicio.dominio.dto.response.ResponseCuenta;
import ec.devsu.app.transacciones.servicio.dominio.dto.response.ResponseMovimiento;
import ec.devsu.app.transacciones.servicio.dominio.exception.CuentaDomainException;
import ec.devsu.app.transacciones.servicio.dominio.exception.TransaccionDomainException;
import ec.devsu.app.transacciones.servicio.dominio.handlers.CuentaQueryCommandHandler;
import ec.devsu.app.transacciones.servicio.dominio.handlers.TransaccionPersistCommandHandler;
import ec.devsu.app.transacciones.servicio.dominio.handlers.TransaccionesQueryCommandHandler;
import ec.devsu.app.transacciones.servicio.dominio.puertos.input.ITransaccionesAppService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

import java.time.LocalDateTime;
import java.util.List;
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
    public CuentaDto obtenerCuentaPorNumero(String numeroCuenta) throws CuentaDomainException {
        return cuentaQueryCommandHandler.obtenerCuentaPorNumero(numeroCuenta);
    }

    @Override
    public MovimientoRegistroDto buscarMovimientoPorId(UUID uuidMovimiento) throws TransaccionDomainException {
        return transaccionesQueryCommandHandler.buscarMovimientoPorId(uuidMovimiento);

    }

    @Override
    public ResponseMovimiento actualizarMovimiento(RequestMovimientoActualizacion requestMovimiento) throws TransaccionDomainException {
        MovimientoRegistroDto movimientoRegistroDto = transaccionPersistCommandHandler.actualizarMovimiento(requestMovimiento);
        return ResponseMovimiento.builder()
                .mensaje("Movimiento actualizado con exito")
                .uuidMovimiento(movimientoRegistroDto.getUuidMovimiento()).build();
    }

    @Override
    public List<MovientoReporte> obtenerMovimientosPorRango(LocalDateTime fechaInicial, LocalDateTime fechaFinal) throws TransaccionDomainException {
        return transaccionesQueryCommandHandler.obtenerMovimientosPorRango(fechaInicial, fechaFinal);
    }

    @Override
    public ResponseCuenta insertarCuentaPersona(RequestCuenta requestCuenta) throws CuentaDomainException {
        return transaccionPersistCommandHandler.insertarCuentaPersona(requestCuenta);
    }

    @Override
    public ResponseCuenta actualizarCuentaPersona(RequestCuentaActualizacion cuentaActualizacion) throws CuentaDomainException {
        return transaccionPersistCommandHandler.actualizarCuentaPersona(cuentaActualizacion);
    }

    @Override
    public ResponseMovimiento insertarMovimiento(RequestMovimiento requestMovimiento) throws TransaccionDomainException{
        return transaccionPersistCommandHandler.insertarMovimiento(requestMovimiento);
    }


}
