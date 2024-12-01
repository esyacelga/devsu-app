package ec.devsu.app.transacciones.servicio.acceso.datos.adaptador;

import ec.devsu.app.excepcion.comun.dominio.valor.TipoMovimiento;
import ec.devsu.app.transacciones.servicio.acceso.datos.entity.Cuenta;
import ec.devsu.app.transacciones.servicio.acceso.datos.entity.Movimientos;
import ec.devsu.app.transacciones.servicio.acceso.datos.repository.ICuentaRepository;
import ec.devsu.app.transacciones.servicio.acceso.datos.repository.IMovimientoRepository;
import ec.devsu.app.transacciones.servicio.dominio.dto.MovimientoRegistroDto;
import ec.devsu.app.transacciones.servicio.dominio.dto.request.RequestMovimiento;
import ec.devsu.app.transacciones.servicio.dominio.dto.request.RequestMovimientoActualizacion;
import ec.devsu.app.transacciones.servicio.dominio.exception.TransaccionDomainException;
import ec.devsu.app.transacciones.servicio.dominio.puertos.output.ITransaccionesDomainRepository;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Optional;
import java.util.UUID;

@Component
public class TransaccionesDomainRepositoryImpl implements ITransaccionesDomainRepository {
    private final IMovimientoRepository movimientoRepository;
    private final ICuentaRepository cuentaRepository;

    public TransaccionesDomainRepositoryImpl(IMovimientoRepository movimientoRepository, ICuentaRepository cuentaRepository) {
        this.movimientoRepository = movimientoRepository;
        this.cuentaRepository = cuentaRepository;
    }

    @Override
    public MovimientoRegistroDto insertarMovimiento(RequestMovimiento requestMovimiento, BigDecimal nuevoSaldo) {
        Optional<Cuenta> cuentaOptional = cuentaRepository.obtenerCuentaPorNumero(requestMovimiento.getNumeroCuenta());
        Cuenta cuenta = cuentaOptional.orElseThrow(() -> new TransaccionDomainException("No se ha encontrado el numero de cuenta " + requestMovimiento.getNumeroCuenta() + " "));
        Movimientos movimientos = movimientoRepository.insertarMovimiento(Movimientos.builder()
                .tipoMovimiento(requestMovimiento.getTipoMovimiento().getValue())
                .cuenta(cuenta)
                .id(UUID.randomUUID())
                .saldo(nuevoSaldo)
                .fechaMovimiento(LocalDateTime.now())
                .valor(requestMovimiento.getValor())
                .build());
        return MovimientoRegistroDto.builder()
                .valor(movimientos.getValor())
                .saldo(movimientos.getSaldo())
                .uuidMovimiento(movimientos.getId())
                .tipoMovimiento(TipoMovimiento.valueOf(movimientos.getTipoMovimiento()))
                .build();
    }

    @Override
    public MovimientoRegistroDto buscarMovimientoPorId(UUID uuidMovimiento) {
        Movimientos movimientos = movimientoRepository.buscarMovimientoPorId(uuidMovimiento);
        return MovimientoRegistroDto.builder()
                .tipoMovimiento(TipoMovimiento.valueOf(movimientos.getTipoMovimiento()))
                .saldo(movimientos.getSaldo())
                .valor(movimientos.getValor())
                .build();
    }

    @Override
    public MovimientoRegistroDto actualizarMovimiento(RequestMovimientoActualizacion requestMovimiento) {
        Movimientos movimientos = movimientoRepository.buscarMovimientoPorId(requestMovimiento.getUuidMovimiento());
        movimientos.setTipoMovimiento(requestMovimiento.getTipoMovimiento().getValue());
        movimientos.setValor(requestMovimiento.getValor());
        movimientoRepository.actualizarMovimiento(movimientos);
        return MovimientoRegistroDto.builder()
                .uuidMovimiento(movimientos.getId())
                .build();
    }
}
