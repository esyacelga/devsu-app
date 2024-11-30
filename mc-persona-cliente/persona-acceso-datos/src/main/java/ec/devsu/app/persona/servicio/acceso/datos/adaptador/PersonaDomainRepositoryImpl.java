package ec.devsu.app.persona.servicio.acceso.datos.adaptador;

import ec.devsu.app.persona.servicio.acceso.datos.entity.Cliente;
import ec.devsu.app.persona.servicio.acceso.datos.entity.Persona;
import ec.devsu.app.persona.servicio.acceso.datos.repository.IPersonaRepository;
import ec.devsu.app.persona.servicio.dominio.dto.PersonaDto;
import ec.devsu.app.persona.servicio.dominio.exception.PersonaDomainException;
import ec.devsu.app.persona.servicio.dominio.puertos.output.IPersonaDomainRepository;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
public class PersonaDomainRepositoryImpl implements IPersonaDomainRepository {
    private final IPersonaRepository personaRepository;

    public PersonaDomainRepositoryImpl(IPersonaRepository personaRepository) {
        this.personaRepository = personaRepository;
    }

    @Override
    public PersonaDto actualizarPersona(UUID uuidCliente, PersonaDto personaDto) throws PersonaDomainException {
        Persona per = personaRepository.actualizarPersona(Persona.builder()
                .genero(personaDto.getGenero())
                .id(personaDto.getUuidPersona())
                .edad(personaDto.getEdad())
                .nombre(personaDto.getNombre())
                .identificacion(personaDto.getIdentificacion())
                .nombre(personaDto.getNombre())
                .direccion(personaDto.getDireccion())
                .telefono(personaDto.getTelefono())
                .cliente(Cliente.builder().clienteid(personaDto.getUuidCliente()).build())
                .build());
        return PersonaDto.builder()
                .genero(per.getGenero())
                .nombre(per.getNombre())
                .identificacion(per.getIdentificacion())
                .nombre(per.getNombre())
                .direccion(per.getDireccion())
                .telefono(per.getTelefono())
                .build();

    }
}
