package ec.devsu.app.persona.servicio.acceso.datos.repository;

import ec.devsu.app.persona.servicio.acceso.datos.entity.Cliente;
import jakarta.persistence.EntityNotFoundException;

import java.util.Optional;
import java.util.UUID;

public interface IClienteRepository {
    Optional<Cliente> buscarPorId(UUID uuid);

    Cliente actualizar(UUID uuid, Cliente cliente) throws EntityNotFoundException;

    Cliente insertarCliente(Cliente cliente);

    void eliminarCliente(UUID cliente) throws EntityNotFoundException;

    public void eliminarClientePorIdentificacion(String identificacion);
}