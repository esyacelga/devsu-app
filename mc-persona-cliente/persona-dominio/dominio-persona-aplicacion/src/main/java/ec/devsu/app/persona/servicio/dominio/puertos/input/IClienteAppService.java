package ec.devsu.app.persona.servicio.dominio.puertos.input;

import ec.devsu.app.persona.servicio.dominio.dto.request.RequestCliente;
import ec.devsu.app.persona.servicio.dominio.dto.response.ResponseCliente;
import ec.devsu.app.persona.servicio.dominio.dto.response.ResponseClientePersona;
import ec.devsu.app.persona.servicio.dominio.exception.PersonaConstrainViolationException;
import ec.devsu.app.persona.servicio.dominio.exception.PersonaDomainException;
import ec.devsu.app.persona.servicio.dominio.exception.PersonaNotFoundDomainException;
import jakarta.validation.Valid;

import java.util.UUID;

public interface IClienteAppService {
    ResponseCliente insertarCliente(@Valid RequestCliente cliente) throws PersonaConstrainViolationException;

    ResponseCliente updateCliente(@Valid UUID idCliente, RequestCliente cliente) throws PersonaDomainException;

    ResponseClientePersona buscarClientePorId(@Valid String idCliente) throws PersonaNotFoundDomainException;

    void elimininarCliente(@Valid UUID uuidCliente) throws PersonaDomainException, PersonaNotFoundDomainException;

}
