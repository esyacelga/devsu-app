package ec.devsu.app.transacciones.servicio.dominio.helpers;

import ec.devsu.app.excepcion.comun.dominio.valor.TipoMovimiento;
import ec.devsu.app.transacciones.servicio.dominio.dto.CuentaDto;
import ec.devsu.app.transacciones.servicio.dominio.dto.MovimientoRegistroDto;
import ec.devsu.app.transacciones.servicio.dominio.dto.request.RequestCuenta;
import ec.devsu.app.transacciones.servicio.dominio.dto.request.RequestCuentaActualizacion;
import ec.devsu.app.transacciones.servicio.dominio.dto.request.RequestMovimiento;
import ec.devsu.app.transacciones.servicio.dominio.dto.response.ResponseCuenta;
import ec.devsu.app.transacciones.servicio.dominio.dto.response.ResponseMovimiento;
import ec.devsu.app.transacciones.servicio.dominio.exception.TransaccionDomainException;
import ec.devsu.app.transacciones.servicio.dominio.puertos.output.ICuentaRepository;
import ec.devsu.app.transacciones.servicio.dominio.puertos.output.ITransaccionesRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;

@Slf4j
@Component
public class TransaccionPersistHelper {
    private final ICuentaRepository cuentaRepository;
    private final ITransaccionesRepository transaccionesRepository;

    public TransaccionPersistHelper(ICuentaRepository cuentaRepository,
                                    ITransaccionesRepository transaccionesRepository) {
        this.cuentaRepository = cuentaRepository;
        this.transaccionesRepository = transaccionesRepository;
    }

    public ResponseMovimiento insertarMovimiento(RequestMovimiento requestMovimiento) {
        BigDecimal saldoActual = cuentaRepository.obtenerSaldoActual(requestMovimiento.getNumeroCuenta());

        if (requestMovimiento.getTipoMovimiento() == TipoMovimiento.DEBITO &&
                saldoActual.compareTo(requestMovimiento.getValor()) < 0) {
            throw new TransaccionDomainException("Saldo insuficiente");
        }

        MovimientoRegistroDto mov = transaccionesRepository.insertarMovimiento(requestMovimiento);
        BigDecimal nuevoSaldo = requestMovimiento.getTipoMovimiento() == TipoMovimiento.CREDITO
                ? saldoActual.add(requestMovimiento.getValor())
                : saldoActual.subtract(requestMovimiento.getValor());

        cuentaRepository.actualizarNuevoSaldo(requestMovimiento.getNumeroCuenta(), nuevoSaldo);
        return ResponseMovimiento.builder()
                .uuidMovimiento(mov.getUuidMovimiento())
                .mensaje("Movimiento Registrado exitosamente")
                .build();
    }

    public ResponseCuenta actualizarCuentaPersona(RequestCuentaActualizacion cuentaActualizacion) {
        CuentaDto cuenta = cuentaRepository.obtenerCuentaPorNumero(cuentaActualizacion.getNumeroCuenta());
        CuentaDto cuentaDto = cuentaRepository.actualizarCuenta(CuentaDto.builder()
                .uuidCuenta(cuenta.getUuidCuenta())
                .tipoCuenta(cuenta.getTipoCuenta())
                .saldo(cuenta.getSaldo())
                .build());
        return ResponseCuenta.builder()
                .uuidCuenta(cuentaDto.getUuidCuenta())
                .mensaje("Cuenta actualizada exitosamente")
                .build();
    }

    public ResponseCuenta insertarCuentaPersona(RequestCuenta requestCuenta) {
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

    public String rellenarConCeros(int numero) {
        return String.format("%05d", numero);
    }
}