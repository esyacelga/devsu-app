package ec.devsu.app.persona.servicio.dominio.handlers;

import ec.devsu.app.persona.servicio.dominio.dto.request.RequestCliente;
import ec.devsu.app.persona.servicio.dominio.dto.response.ResponseCliente;
import ec.devsu.app.persona.servicio.dominio.exception.PersonaConstrainViolationException;
import ec.devsu.app.persona.servicio.dominio.helpers.ClientePersistHelper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Slf4j
@Component
public class ClientePersistCommandHandler {
    private final ClientePersistHelper clientePersistHelper;

    public ClientePersistCommandHandler(ClientePersistHelper clientePersistHelper) {
        this.clientePersistHelper = clientePersistHelper;
    }

    public ResponseCliente insertarCliente(RequestCliente cliente) {
        try {
            return clientePersistHelper.insertarCliente(cliente);
        } catch (Exception exception) {
            throw new PersonaConstrainViolationException("Error al insertar cliente con identificacion {" + cliente.getIdentificacion().toString() + "}", exception);
        }
    }

    public void elimininarCliente(UUID uuidCliente) {
        clientePersistHelper.elimininarCliente(uuidCliente);
    }

    public ResponseCliente updateCliente(UUID idCliente, RequestCliente cliente) {
        return clientePersistHelper.updateCliente(idCliente, cliente);
    }
}
