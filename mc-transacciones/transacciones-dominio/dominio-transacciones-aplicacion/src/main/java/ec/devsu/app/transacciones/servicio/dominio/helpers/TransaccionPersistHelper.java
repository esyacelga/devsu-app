package ec.devsu.app.transacciones.servicio.dominio.helpers;

import ec.devsu.app.excepcion.comun.dominio.valor.TipoCuenta;
import ec.devsu.app.excepcion.comun.dominio.valor.TipoMovimiento;
import ec.devsu.app.transacciones.servicio.dominio.dto.CuentaDto;
import ec.devsu.app.transacciones.servicio.dominio.dto.MovimientoRegistroDto;
import ec.devsu.app.transacciones.servicio.dominio.dto.request.RequestCuenta;
import ec.devsu.app.transacciones.servicio.dominio.dto.request.RequestCuentaActualizacion;
import ec.devsu.app.transacciones.servicio.dominio.dto.request.RequestMovimiento;
import ec.devsu.app.transacciones.servicio.dominio.dto.request.RequestMovimientoActualizacion;
import ec.devsu.app.transacciones.servicio.dominio.dto.response.ResponseCuenta;
import ec.devsu.app.transacciones.servicio.dominio.dto.response.ResponseMovimiento;
import ec.devsu.app.transacciones.servicio.dominio.exception.CuentaDomainException;
import ec.devsu.app.transacciones.servicio.dominio.exception.TransaccionDomainException;
import ec.devsu.app.transacciones.servicio.dominio.puertos.output.ICuentaDomainRepository;
import ec.devsu.app.transacciones.servicio.dominio.puertos.output.ITransaccionesDomainRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;

@Slf4j
@Component
public class TransaccionPersistHelper {
    private final ICuentaDomainRepository cuentaRepository;
    private final ITransaccionesDomainRepository transaccionesRepository;

    public TransaccionPersistHelper(ICuentaDomainRepository cuentaRepository,
                                    ITransaccionesDomainRepository transaccionesRepository) {
        this.cuentaRepository = cuentaRepository;
        this.transaccionesRepository = transaccionesRepository;
    }

    @Transactional
    public MovimientoRegistroDto actualizarMovimiento(RequestMovimientoActualizacion requestMovimiento) throws TransaccionDomainException {
        return transaccionesRepository.actualizarMovimiento(requestMovimiento);
    }

    @Transactional
    public ResponseMovimiento insertarMovimiento(RequestMovimiento requestMovimiento) throws TransaccionDomainException {
        BigDecimal saldoActual = cuentaRepository.obtenerSaldoActual(requestMovimiento.getNumeroCuenta());
        TipoMovimiento tipoMovimiento;
        try {
            tipoMovimiento = TipoMovimiento.valueOf(requestMovimiento.getTipoMovimiento());
        } catch (IllegalArgumentException ex) {
            throw new TransaccionDomainException("Tipo de movimiento incorrecto, tipo de movimiento correcto es DEBITO o CREDITO ", ex);
        }

        if (tipoMovimiento == TipoMovimiento.DEBITO &&
                saldoActual.compareTo(requestMovimiento.getValor()) < 0) {
            throw new TransaccionDomainException("Saldo insuficiente");
        }

        BigDecimal nuevoSaldo = tipoMovimiento == TipoMovimiento.CREDITO
                ? saldoActual.add(requestMovimiento.getValor())
                : saldoActual.subtract(requestMovimiento.getValor());

        MovimientoRegistroDto mov = transaccionesRepository.insertarMovimiento(requestMovimiento, nuevoSaldo);
        cuentaRepository.actualizarNuevoSaldo(requestMovimiento.getNumeroCuenta(), nuevoSaldo);
        return ResponseMovimiento.builder()
                .uuidMovimiento(mov.getUuidMovimiento())
                .mensaje("Movimiento Registrado exitosamente")
                .build();
    }

    @Transactional
    public ResponseCuenta actualizarCuentaPersona(RequestCuentaActualizacion cuentaActualizacion) throws CuentaDomainException {
        CuentaDto cuenta = cuentaRepository.obtenerCuentaPorNumero(cuentaActualizacion.getNumeroCuenta());
        try {
            TipoCuenta.valueOf(cuentaActualizacion.getTipoCuenta());
        } catch (IllegalArgumentException ex) {
            throw new CuentaDomainException("El tipo de cuenta valido es AHORROS, CORRIENTE", ex);
        }
        CuentaDto cuentaDto = cuentaRepository.actualizarCuenta(CuentaDto.builder()
                .uuidCuenta(cuenta.getUuidCuenta())
                .numeroCuenta(cuentaActualizacion.getNumeroCuenta())
                .estado(stringToBoolean(cuentaActualizacion.getEstado()))
                .tipoCuenta(TipoCuenta.valueOf(cuentaActualizacion.getTipoCuenta()))
                .saldo(cuentaActualizacion.getSaldo())
                .build());
        return ResponseCuenta.builder()
                .uuidCuenta(cuentaDto.getUuidCuenta())
                .mensaje("Cuenta actualizada exitosamente")
                .build();
    }

    @Transactional
    public ResponseCuenta insertarCuentaPersona(RequestCuenta requestCuenta) throws CuentaDomainException {
        try {
            TipoCuenta.valueOf(requestCuenta.getTipoCuenta());
        } catch (IllegalArgumentException ex) {
            throw new CuentaDomainException("El tipo de cuenta debe ser DEBITO, CREDITO", ex);
        }

        Integer numeroCuenta = cuentaRepository.obtenerSiguienteSecuencial();
        if (numeroCuenta == null) {
            numeroCuenta = 1;
        }
        String no = rellenarConCeros(numeroCuenta);
        CuentaDto cuentaDto = cuentaRepository.insertarCuentaPersona(requestCuenta, no);
        return ResponseCuenta.builder()
                .uuidCuenta(cuentaDto.getUuidCuenta())
                .mensaje("Cuenta registrada exitosamente")
                .build();
    }

    public boolean stringToBoolean(String value) {
        if (!"1".equals(value) && !"0".equals(value) && !"true".equalsIgnoreCase(value) && !"false".equalsIgnoreCase(value)) {
            throw new CuentaDomainException("Entrada inválida, el estado puede ser 'true', 'false', '1' o '0'");
        }
        return "1".equals(value) || "true".equalsIgnoreCase(value);
    }

    public String rellenarConCeros(int numero) {
        return String.format("%05d", numero);
    }
}

