package ec.devsu.app.persona.servicio.acceso.datos.repository.impl;

import ec.devsu.app.persona.servicio.acceso.datos.entity.Persona;
import ec.devsu.app.persona.servicio.acceso.datos.repository.IPersonaRepository;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityNotFoundException;
import jakarta.persistence.PersistenceContext;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public class PersonaRepositoryImpl implements IPersonaRepository {
    @PersistenceContext
    private EntityManager entityManager;


    @Override
    public Persona insertarPersona(Persona persona) {
        if (persona.getCliente() != null) {
            persona.getCliente().setPersona(persona);
        }
        entityManager.persist(persona);
        return persona;

    }

    @Override
    public Persona actualizarPersona(Persona persona) {
        Persona personaExistente = entityManager.find(Persona.class, persona.getId());
        if (personaExistente == null) {
            throw new EntityNotFoundException("Persona con ID " + persona.getId() + " no encontrada.");
        }
        personaExistente.setNombre(persona.getNombre());
        personaExistente.setDireccion(persona.getDireccion());
        personaExistente.setEdad(persona.getEdad());
        personaExistente.setGenero(persona.getGenero());
        personaExistente.setTelefono(persona.getTelefono());
        entityManager.merge(personaExistente);
        return personaExistente;
    }

    @Override
    public void eliminarPersona(UUID uuid) {
        Persona persona = entityManager.find(Persona.class, uuid);
        entityManager.remove(persona);
    }

    @Override
    public Persona buscarPersonaPorIdentificacion(String identificacion) {
        return entityManager.createQuery(
                        "SELECT p FROM Persona p " +
                                "LEFT JOIN FETCH p.cliente " +
                                "WHERE p.identificacion = :identificacion", Persona.class)
                .setParameter("identificacion", identificacion)
                .getSingleResult();
    }

    @Override
    public Persona buscarPersonaPorId(UUID id) {
        return entityManager.find(Persona.class, id);
    }
}
