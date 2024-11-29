package ec.devsu.app.servicio.acceso.datos.repository;

import ec.devsu.app.servicio.acceso.datos.entity.Cliente;

import java.util.UUID;

public interface IClienteRepository {
    Cliente buscarPorId(UUID uuid);

    Cliente actualizar(UUID uuid, Cliente cliente);

    Cliente insertarCliente(Cliente cliente);

    void eliminarCliente(UUID cliente);

    public void eliminarClientePorIdentificacion(String identificacion);
}
