package ec.devsu.app.servicio.acceso.datos.repository;

import ec.devsu.app.servicio.acceso.datos.entity.Persona;

import java.util.UUID;

public interface IPersonaRepository {


    Persona insertarPersona(Persona persona);

    void eliminarPersona(UUID uuid);

    Persona buscarPersonaPorIdentificacion(String identificacion);

    Persona buscarPersonaPorId(UUID id);

}
