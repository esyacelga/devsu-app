package ec.devsu.app.persona.servicio.dominio.puertos.output;

import ec.devsu.app.persona.servicio.dominio.dto.PersonaDto;
import ec.devsu.app.persona.servicio.dominio.exception.PersonaDomainException;

import java.util.UUID;

public interface IPersonaRepositoryOut {

    PersonaDto actualizarPersona(UUID uuidCliente, PersonaDto personaDto) throws PersonaDomainException;
}