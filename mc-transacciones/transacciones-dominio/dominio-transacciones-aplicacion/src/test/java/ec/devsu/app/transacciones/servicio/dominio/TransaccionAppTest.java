package ec.devsu.app.transacciones.servicio.dominio;

import ec.devsu.app.excepcion.comun.dominio.valor.TipoCuenta;
import ec.devsu.app.excepcion.comun.dominio.valor.TipoMovimiento;
import ec.devsu.app.transacciones.servicio.dominio.dto.CuentaDto;
import ec.devsu.app.transacciones.servicio.dominio.dto.MovimientoRegistroDto;
import ec.devsu.app.transacciones.servicio.dominio.dto.request.RequestCuenta;
import ec.devsu.app.transacciones.servicio.dominio.dto.request.RequestCuentaActualizacion;
import ec.devsu.app.transacciones.servicio.dominio.dto.request.RequestMovimiento;
import ec.devsu.app.transacciones.servicio.dominio.dto.response.ResponseCuenta;
import ec.devsu.app.transacciones.servicio.dominio.dto.response.ResponseMovimiento;
import ec.devsu.app.transacciones.servicio.dominio.exception.TransaccionDomainException;
import ec.devsu.app.transacciones.servicio.dominio.puertos.input.ITransaccionesAppService;
import ec.devsu.app.transacciones.servicio.dominio.puertos.output.ICuentaDomainRepository;
import ec.devsu.app.transacciones.servicio.dominio.puertos.output.ITransaccionesDomainRepository;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.math.BigDecimal;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.when;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
@SpringBootTest(classes = TransaccionTestConfiguration.class)
@Slf4j
public class TransaccionAppTest {

    @Autowired
    ITransaccionesAppService transaccionesAppService;

    @Autowired
    ITransaccionesDomainRepository transaccionesRepository;

    @Autowired
    ICuentaDomainRepository cuentaRepository;


    @Test
    @DisplayName("Registrar cuenta")
    public void testGenerarCuenta() {
        when(cuentaRepository.obtenerSiguienteSecuencial())
                .thenReturn(1);
        when(cuentaRepository.insertarCuentaPersona(any(RequestCuenta.class), eq("00001")))
                .thenReturn(CuentaDto.builder()
                        .saldo(new BigDecimal(1000))
                        .numeroCuenta("000011")
                        .tipoCuenta(TipoCuenta.AHORROS)
                        .identificacion("1721737243")
                        .uuidCuenta(UUID.randomUUID())
                        .build());

        ResponseCuenta responseCuenta = transaccionesAppService.insertarCuentaPersona(RequestCuenta.builder()
                .tipoCuenta(TipoCuenta.AHORROS.getTipo())
                .saldo(new BigDecimal(1000))
                .identificacion("1721737243")
                .build());

        assertEquals("Cuenta registrada exitosamente", responseCuenta.getMensaje());
    }

    @Test
    @DisplayName("Actualizar cuenta")
    public void actualizarCuenta() {
        when(cuentaRepository.obtenerCuentaPorNumero("00001"))
                .thenReturn(CuentaDto.builder()
                        .tipoCuenta(TipoCuenta.AHORROS)
                        .saldo(new BigDecimal(1000))
                        .identificacion("1721737243")
                        .numeroCuenta("00001")
                        .build());

        when(cuentaRepository.actualizarCuenta(any(CuentaDto.class)))
                .thenReturn(CuentaDto.builder()
                        .saldo(new BigDecimal(1000))
                        .numeroCuenta("00001")
                        .tipoCuenta(TipoCuenta.AHORROS)
                        .identificacion("1721737243")
                        .uuidCuenta(UUID.randomUUID())
                        .build());

        ResponseCuenta responseCuenta = transaccionesAppService.actualizarCuentaPersona(RequestCuentaActualizacion.builder()
                .tipoCuenta(TipoCuenta.AHORROS.getTipo())
                .numeroCuenta("00001")
                .saldo(new BigDecimal(1000))
                .build());
        assertEquals("Cuenta actualizada exitosamente", responseCuenta.getMensaje());
    }

    @Test
    @DisplayName("Registrar movimiento con saldo")
    public void registrarMovimiento() {
        when(cuentaRepository.obtenerSaldoActual(eq("001001")))
                .thenReturn(new BigDecimal(10000));
        when(transaccionesRepository.insertarMovimiento(any(RequestMovimiento.class), any(BigDecimal.class)))
                .thenReturn(MovimientoRegistroDto.builder()
                        .uuidMovimiento(UUID.randomUUID())
                        .valor(new BigDecimal(9000))
                        .build());
        ResponseMovimiento responseMovimiento = transaccionesAppService.insertarMovimiento(RequestMovimiento.builder()
                .tipoMovimiento(TipoMovimiento.DEBITO.getValue())
                .valor(new BigDecimal(1000))
                .numeroCuenta("001001")
                .build());
        assertEquals("Movimiento Registrado exitosamente", responseMovimiento.getMensaje());
    }

    @Test
    @DisplayName("Registrar movimiento sin saldo")
    public void registrarMovimientoSinSaldo() {
        when(cuentaRepository.obtenerSaldoActual(eq("001001")))
                .thenReturn(new BigDecimal(10));
        TransaccionDomainException transaccionDomainException = assertThrows(TransaccionDomainException.class,
                () -> transaccionesAppService.insertarMovimiento(RequestMovimiento.builder()
                        .tipoMovimiento(TipoMovimiento.DEBITO.getValue())
                        .valor(new BigDecimal(1000))
                        .numeroCuenta("001001")
                        .build()));
        assertEquals("Saldo insuficiente", transaccionDomainException.getMessage());
    }


}


