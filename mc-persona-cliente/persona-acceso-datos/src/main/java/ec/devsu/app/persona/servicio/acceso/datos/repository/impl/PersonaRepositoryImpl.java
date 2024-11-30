package ec.devsu.app.persona.servicio.acceso.datos.repository.impl;

import ec.devsu.app.persona.servicio.acceso.datos.entity.Persona;
import ec.devsu.app.persona.servicio.acceso.datos.repository.IPersonaRepository;
import jakarta.persistence.EntityManager;
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
