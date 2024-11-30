package ec.devsu.app.transacciones.servicio.dominio;

import ec.devsu.app.transacciones.servicio.dominio.dto.request.RequestCuenta;
import ec.devsu.app.transacciones.servicio.dominio.dto.response.ResponseCuenta;
import ec.devsu.app.transacciones.servicio.dominio.handlers.TransaccionPersistCommandHandler;
import ec.devsu.app.transacciones.servicio.dominio.puertos.input.ITransaccionesAppService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

@Slf4j
@Validated
@Service
public class TransaccionesAppServiceImpl implements ITransaccionesAppService {
    private final TransaccionPersistCommandHandler transaccionPersistCommandHandler;

    public TransaccionesAppServiceImpl(TransaccionPersistCommandHandler transaccionPersistCommandHandler) {
        this.transaccionPersistCommandHandler = transaccionPersistCommandHandler;
    }

    @Override
    public ResponseCuenta insertarCuentaPersona(RequestCuenta requestCuenta) {
        return transaccionPersistCommandHandler.insertarCuentaPersona(requestCuenta);
    }
}
