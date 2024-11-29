package ec.devsu.app.persona.servicio.dominio.puertos.output;

import ec.devsu.app.persona.servicio.dominio.dto.ClienteDto;

import java.util.Optional;
import java.util.UUID;

public interface IClientePersonaRepository {
    Optional<ClienteDto> buscarClientePorId(UUID uuidCliente);
    ClienteDto buscarClientePorIdentificacion(String identificacion);

    void eliminarCliente(UUID uuidCliente);

    ClienteDto insertarCliente(ClienteDto clienteDto);




}
