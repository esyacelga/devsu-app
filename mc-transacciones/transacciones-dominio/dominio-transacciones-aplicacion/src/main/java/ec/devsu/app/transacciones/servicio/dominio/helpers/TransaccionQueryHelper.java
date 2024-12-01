package ec.devsu.app.transacciones.servicio.dominio.helpers;

import ec.devsu.app.transacciones.servicio.dominio.dto.MovimientoRegistroDto;
import ec.devsu.app.transacciones.servicio.dominio.exception.TransaccionDomainException;
import ec.devsu.app.transacciones.servicio.dominio.puertos.output.ITransaccionesDomainRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Slf4j
@Component
public class TransaccionQueryHelper {
    private final ITransaccionesDomainRepository transaccionesDomainRepository;

    public TransaccionQueryHelper(ITransaccionesDomainRepository transaccionesDomainRepository) {
        this.transaccionesDomainRepository = transaccionesDomainRepository;
    }

    public MovimientoRegistroDto buscarMovimientoPorId(UUID uuidMovimiento) throws TransaccionDomainException {
        return transaccionesDomainRepository.buscarMovimientoPorId(uuidMovimiento);
    }

}
