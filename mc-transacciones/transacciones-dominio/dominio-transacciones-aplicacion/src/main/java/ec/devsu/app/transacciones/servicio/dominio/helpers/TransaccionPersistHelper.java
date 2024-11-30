package ec.devsu.app.transacciones.servicio.dominio.helpers;

import ec.devsu.app.transacciones.servicio.dominio.dto.CuentaDto;
import ec.devsu.app.transacciones.servicio.dominio.dto.request.RequestCuenta;
import ec.devsu.app.transacciones.servicio.dominio.dto.request.RequestCuentaActualizacion;
import ec.devsu.app.transacciones.servicio.dominio.dto.response.ResponseCuenta;
import ec.devsu.app.transacciones.servicio.dominio.puertos.output.ICuentaRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class TransaccionPersistHelper {
    private final ICuentaRepository cuentaRepository;


    public TransaccionPersistHelper(ICuentaRepository cuentaRepository) {
        this.cuentaRepository = cuentaRepository;
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
