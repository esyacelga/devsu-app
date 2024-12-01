package ec.devsu.app.persona.servicio.dominio.handlers;

import ec.devsu.app.persona.servicio.dominio.dto.response.ResponseClientePersona;
import ec.devsu.app.persona.servicio.dominio.exception.PersonaNotFoundDomainException;
import ec.devsu.app.persona.servicio.dominio.helpers.ClienteQueryHelper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Slf4j
@Component
public class ClienteQueryCommandHandler {
    private final ClienteQueryHelper clienteQueryHelper;

    public ClienteQueryCommandHandler(ClienteQueryHelper clienteQueryHelper) {
        this.clienteQueryHelper = clienteQueryHelper;
    }

    public ResponseClientePersona buscarClientePorId(UUID uuidCliente) throws PersonaNotFoundDomainException {
        return clienteQueryHelper.buscarClientePorId(uuidCliente);
    }
}
