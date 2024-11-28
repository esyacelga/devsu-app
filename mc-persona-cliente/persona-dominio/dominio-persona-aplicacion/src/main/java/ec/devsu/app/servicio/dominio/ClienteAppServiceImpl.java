package ec.devsu.app.servicio.dominio;

import ec.devsu.app.servicio.dominio.dto.request.RequestCliente;
import ec.devsu.app.servicio.dominio.dto.response.ResponseCliente;
import ec.devsu.app.servicio.dominio.dto.response.ResponseClientePersona;
import ec.devsu.app.servicio.dominio.handlers.ClientePersistCommandHandler;
import ec.devsu.app.servicio.dominio.handlers.ClienteQueryCommandHandler;
import ec.devsu.app.servicio.dominio.puertos.input.IClienteAppService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

import java.util.UUID;

@Slf4j
@Validated
@Service
public class ClienteAppServiceImpl implements IClienteAppService {

    private final ClientePersistCommandHandler clienteCreateCommandHandler;
    private final ClienteQueryCommandHandler clienteQueryCommandHandler;

    public ClienteAppServiceImpl(ClientePersistCommandHandler clienteCreateCommandHandler,
                                 ClienteQueryCommandHandler clienteQueryCommandHandler1) {
        this.clienteCreateCommandHandler = clienteCreateCommandHandler;
        this.clienteQueryCommandHandler = clienteQueryCommandHandler1;
    }

    @Override
    public ResponseCliente insertarCliente(RequestCliente cliente) {
        return clienteCreateCommandHandler.insertarCliente(cliente);
    }

    @Override
    public void elimininarCliente(UUID uuidCliente) {
        clienteCreateCommandHandler.elimininarCliente(uuidCliente);
    }

    @Override
    public ResponseCliente updateCliente(UUID idCliente, RequestCliente cliente) {
        return clienteCreateCommandHandler.updateCliente(idCliente, cliente);
    }

    @Override
    public ResponseClientePersona buscarClientePorId(UUID uuidCliente) {
        return clienteQueryCommandHandler.buscarClientePorId(uuidCliente);
    }


}
