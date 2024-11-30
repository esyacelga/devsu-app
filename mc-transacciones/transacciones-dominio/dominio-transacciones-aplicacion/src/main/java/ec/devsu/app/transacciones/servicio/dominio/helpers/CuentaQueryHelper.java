package ec.devsu.app.transacciones.servicio.dominio.helpers;

import ec.devsu.app.transacciones.servicio.dominio.dto.CuentaDto;
import ec.devsu.app.transacciones.servicio.dominio.puertos.output.ICuentaRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class CuentaQueryHelper {
    private final ICuentaRepository cuentaRepository;

    public CuentaQueryHelper(ICuentaRepository cuentaRepository) {
        this.cuentaRepository = cuentaRepository;
    }

    public Integer obtenerSiguienteSecuencial() {
        return cuentaRepository.obtenerSiguienteSecuencial();
    }

    public CuentaDto obtenerCuentaPersona(String numeroCuenta) {
        return cuentaRepository.obtenerCuentaPersona(numeroCuenta);
    }
    public CuentaDto obtenerCuentaPorNumero(String numeroCuenta) {
        return cuentaRepository.obtenerCuentaPorNumero(numeroCuenta);
    }
}
