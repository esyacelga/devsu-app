package ec.devsu.app.transacciones.servicio.dominio.helpers;

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
}
