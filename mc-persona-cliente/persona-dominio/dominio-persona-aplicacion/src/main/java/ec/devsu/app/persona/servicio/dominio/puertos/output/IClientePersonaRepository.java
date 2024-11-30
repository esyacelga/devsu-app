package ec.devsu.app.persona.servicio.dominio.puertos.output;

import ec.devsu.app.persona.servicio.dominio.dto.ClienteDto;
import ec.devsu.app.persona.servicio.dominio.exception.PersonaDomainException;
import ec.devsu.app.persona.servicio.dominio.exception.PersonaNotFoundDomainException;

import java.util.Optional;
import java.util.UUID;

public interface IClientePersonaRepository {
    Optional<ClienteDto> buscarClientePorId(UUID uuidCliente);

    ClienteDto buscarClientePorIdentificacion(String identificacion);

    void eliminarCliente(UUID uuidCliente) throws PersonaDomainException, PersonaNotFoundDomainException;

    ClienteDto insertarCliente(ClienteDto clienteDto);

    ClienteDto actualizarCliente(UUID uuidCliente, ClienteDto clienteDto) throws PersonaDomainException;


}
