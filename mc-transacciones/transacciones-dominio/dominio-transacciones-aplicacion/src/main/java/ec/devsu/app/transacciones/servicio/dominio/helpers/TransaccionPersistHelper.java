package ec.devsu.app.transacciones.servicio.dominio.helpers;

import ec.devsu.app.transacciones.servicio.dominio.dto.CuentaDto;
import ec.devsu.app.transacciones.servicio.dominio.dto.request.RequestCuenta;
import ec.devsu.app.transacciones.servicio.dominio.dto.response.ResponseCuenta;
import ec.devsu.app.transacciones.servicio.dominio.puertos.output.ICuentaRepository;
import ec.devsu.app.transacciones.servicio.dominio.puertos.output.ITransaccionesRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class TransaccionPersistHelper {
    private final ITransaccionesRepository transaccionesRepository;
    private final ICuentaRepository cuentaRepository;

    public TransaccionPersistHelper(ITransaccionesRepository transaccionesRepository, ICuentaRepository cuentaRepository) {
        this.transaccionesRepository = transaccionesRepository;
        this.cuentaRepository = cuentaRepository;
    }

    public ResponseCuenta insertarCuentaPersona(RequestCuenta requestCuenta) {
        Integer numeroCuenta = cuentaRepository.obtenerSiguienteSecuencial();
        if (numeroCuenta == null) {
            numeroCuenta = 1;
        }
        String no = rellenarConCeros(numeroCuenta);
        CuentaDto cuentaDto = transaccionesRepository.insertarCuentaPersona(requestCuenta, no);
        return ResponseCuenta.builder()
                .uuidCuenta(cuentaDto.getUuidCuenta())
                .mensaje("Cuenta registrada exitosamente")
                .build();
    }

    public String rellenarConCeros(int numero) {
        return String.format("%05d", numero);
    }
}
