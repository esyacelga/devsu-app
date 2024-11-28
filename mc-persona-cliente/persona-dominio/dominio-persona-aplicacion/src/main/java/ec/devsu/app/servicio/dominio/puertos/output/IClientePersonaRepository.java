package ec.devsu.app.servicio.dominio.puertos.output;

import ec.devsu.app.servicio.dominio.dto.ClienteDto;

import java.util.UUID;

public interface IClientePersonaRepository {
    ClienteDto buscarClientePorId(UUID uuidCliente);
    ClienteDto buscarClientePorIdentificacion(String identificacion);

    void eliminarCliente(UUID uuidCliente);

    ClienteDto insertarCliente(ClienteDto clienteDto);




}
