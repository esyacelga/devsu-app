package ec.devsu.app.persona.servicio.acceso.datos.repository.impl;

import ec.devsu.app.persona.servicio.acceso.datos.entity.Cliente;
import ec.devsu.app.persona.servicio.acceso.datos.entity.Persona;
import ec.devsu.app.persona.servicio.acceso.datos.repository.IClienteRepository;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityNotFoundException;
import jakarta.persistence.NoResultException;
import jakarta.persistence.PersistenceContext;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public class ClienteRepositoryImpl implements IClienteRepository {
    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public Optional<Cliente> buscarPorId(UUID uuid) {
        Cliente cliente = entityManager.find(Cliente.class, uuid);
        return Optional.ofNullable(cliente);
    }

    @Override
    public Cliente actualizar(UUID uuid, Cliente cliente) throws EntityNotFoundException {
        Cliente clienteExistente = entityManager.find(Cliente.class, uuid);
        if (clienteExistente == null) {
            throw new EntityNotFoundException("Cliente con UUID " + uuid + " no encontrado.");
        }
        Persona persona =entityManager.find(Persona.class,clienteExistente.getPersona().getId());
        clienteExistente.setContrasenia(cliente.getContrasenia());
        clienteExistente.setEstado(cliente.getEstado());
        clienteExistente.setPersona(persona);
        entityManager.merge(clienteExistente);
        return clienteExistente;
    }

    @Override
    public Cliente insertarCliente(Cliente cliente) {
        entityManager.persist(cliente);
        return cliente;
    }

    @Override
    public void eliminarCliente(UUID clienteId) throws EntityNotFoundException {
        Cliente cliente = entityManager.find(Cliente.class, clienteId);
        if (cliente == null)
            throw new EntityNotFoundException("El recurso solicitado no existe");
        entityManager.remove(cliente);
    }

    @Override
    public void eliminarClientePorIdentificacion(String identificacion) {
        try {
            Persona persona = entityManager.createQuery(
                            "SELECT p FROM Persona p WHERE p.identificacion = :identificacion", Persona.class)
                    .setParameter("identificacion", identificacion)
                    .getSingleResult();

            if (persona.getCliente() == null) {
                throw new IllegalStateException("La persona con identificación " + identificacion + " no tiene un cliente asociado.");
            }
            entityManager.remove(persona.getCliente());
        } catch (NoResultException e) {
            throw new EntityNotFoundException("No se encontró una persona con la identificación: " + identificacion);
        }
    }

}
