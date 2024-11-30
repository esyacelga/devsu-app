package ec.devsu.app.transacciones.servicio.dominio;

import ec.devsu.app.excepcion.comun.dominio.valor.TipoCuenta;
import ec.devsu.app.transacciones.servicio.dominio.dto.CuentaDto;
import ec.devsu.app.transacciones.servicio.dominio.dto.request.RequestCuenta;
import ec.devsu.app.transacciones.servicio.dominio.dto.response.ResponseCuenta;
import ec.devsu.app.transacciones.servicio.dominio.puertos.input.ITransaccionesAppService;
import ec.devsu.app.transacciones.servicio.dominio.puertos.output.ICuentaRepository;
import ec.devsu.app.transacciones.servicio.dominio.puertos.output.ITransaccionesRepository;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;
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
    ITransaccionesRepository transaccionesRepository;
    @Autowired
    ICuentaRepository cuentaRepository;

    @Test
    @DisplayName("Registrar cuenta ")
    public void testGenerarCuenta() {
        when(cuentaRepository.obtenerSiguienteSecuencial())
                .thenReturn(1);
        when(transaccionesRepository.insertarCuentaPersona(any(RequestCuenta.class), eq("00001")))
                .thenReturn(CuentaDto.builder()
                        .saldo("1000")
                        .numeroCuenta("000011")
                        .tipoCuenta(TipoCuenta.AHORROS)
                        .identificacion("1721737243")
                        .uuidCuenta(UUID.randomUUID())
                        .build());

        // Ejecución del método a probar
        ResponseCuenta responseCuenta = transaccionesAppService.insertarCuentaPersona(RequestCuenta.builder()
                .tipoCuenta(TipoCuenta.AHORROS)
                .identificacion("1721737243")
                .build());

        // Validación de resultados
        assertEquals("Cuenta registrada exitosamente", responseCuenta.getMensaje());

    }
   /* @Test
    @DisplayName("Registrar cliente ")
    public void testGenerarSecuencia() {
        when(clientePersonaRepository.insertarCliente(any(ClienteDto.class)))
                .thenReturn(ClienteDto.builder()
                        .uuidCliente(UUID.randomUUID())
                        .nombre("Santiago Yacelga")
                        .genero("Masculino")
                        .identificacion("1721737243")
                        .direccion("Quito-Ecuador")
                        .telefono("0979151957")
                        .estado("ACTIVO")
                        .edad("36")
                        .build());
        RequestCliente requestCliente = RequestCliente.builder()
                .nombre("Santiago Yacelga")
                .genero("Masculino")
                .identificacion("1721737243")
                .direccion("Quito-Ecuador")
                .telefono("0979151957")
                .estado("ACTIVO")
                .edad("36")
                .contrasenia("123456")
                .build();
        ResponseCliente cliente = clienteAppService.insertarCliente(requestCliente);
        assertEquals("CLIENTE REGISTRADO CORRECTAMENTE", cliente.getMensaje());
    }*/
}


