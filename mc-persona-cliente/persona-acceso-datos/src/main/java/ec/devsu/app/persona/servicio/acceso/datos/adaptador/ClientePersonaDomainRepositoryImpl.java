package ec.devsu.app.persona.servicio.acceso.datos.adaptador;

import ec.devsu.app.persona.servicio.acceso.datos.entity.Cliente;
import ec.devsu.app.persona.servicio.acceso.datos.entity.Persona;
import ec.devsu.app.persona.servicio.acceso.datos.repository.IClienteRepository;
import ec.devsu.app.persona.servicio.acceso.datos.repository.IPersonaRepository;
import ec.devsu.app.persona.servicio.dominio.dto.ClienteDto;
import ec.devsu.app.persona.servicio.dominio.exception.PersonaDomainException;
import ec.devsu.app.persona.servicio.dominio.exception.PersonaNotFoundDomainException;
import ec.devsu.app.persona.servicio.dominio.puertos.output.IClientePersonaDomainRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.stereotype.Component;

import java.util.Optional;
import java.util.UUID;

@Component
public class ClientePersonaDomainRepositoryImpl implements IClientePersonaDomainRepository {
    private final IPersonaRepository personaRepository;
    private final IClienteRepository clientePersonaRepository;

    public ClientePersonaDomainRepositoryImpl(IPersonaRepository personaRepository,
                                              IClienteRepository clientePersonaRepository) {
        this.personaRepository = personaRepository;
        this.clientePersonaRepository = clientePersonaRepository;
    }


    @Override
    public Optional<ClienteDto> buscarClientePorId(UUID uuidCliente) {
        return clientePersonaRepository.buscarPorId(uuidCliente)
                .map(cliente -> ClienteDto.builder()
                        .uuidCliente(uuidCliente)
                        .estado(cliente.getEstado())
                        .uuidPersona(cliente.getPersona().getId())
                        .genero(cliente.getPersona().getGenero())
                        .direccion(cliente.getPersona().getDireccion())
                        .telefono(cliente.getPersona().getTelefono())
                        .identificacion(cliente.getPersona().getIdentificacion())
                        .nombre(cliente.getPersona().getNombre())
                        .password(cliente.getContrasenia())
                        .edad(cliente.getPersona().getEdad())
                        .build());
    }

    @Override
    public ClienteDto buscarClientePorIdentificacion(String identificacion) {
        Persona persona = personaRepository.buscarPersonaPorIdentificacion(identificacion);
        return ClienteDto.builder()
                .direccion(persona.getDireccion())
                .genero(persona.getGenero())
                .nombre(persona.getNombre())
                .telefono(persona.getTelefono())
                .edad(persona.getEdad())
                .direccion(persona.getDireccion())
                .identificacion(persona.getIdentificacion())
                .build();
    }

    @Override
    public void eliminarCliente(UUID uuidCliente) throws PersonaNotFoundDomainException {
        try {
            clientePersonaRepository.eliminarCliente(uuidCliente);
        } catch (EntityNotFoundException e) {
            throw new PersonaNotFoundDomainException("Cliente con UUID " + uuidCliente + " no encontrado.", e);
        }

    }

    @Override
    public ClienteDto insertarCliente(ClienteDto clienteDto) {
        Persona persona = personaRepository.insertarPersona(Persona.builder()
                .cliente(Cliente.builder()
                        .clienteid(UUID.randomUUID())
                        .contrasenia(clienteDto.getPassword())
                        .estado(Boolean.TRUE).build())
                .identificacion(clienteDto.getIdentificacion())
                .nombre(clienteDto.getNombre())
                .telefono(clienteDto.getTelefono())
                .genero(clienteDto.getGenero())
                .direccion(clienteDto.getDireccion())
                .edad(clienteDto.getEdad())
                .id(UUID.randomUUID())
                .build());
        return ClienteDto.builder()
                .nombre(persona.getNombre())
                .telefono(persona.getTelefono())
                .uuidCliente(persona.getCliente().getClienteid())
                .build();

    }


    @Override
    public ClienteDto actualizarCliente(UUID uuidCliente, ClienteDto clienteDto) throws PersonaNotFoundDomainException {
        try {
            Cliente cl = clientePersonaRepository.actualizar(uuidCliente, Cliente.builder()
                    .estado(stringToBoolean(clienteDto.getEstado()))
                    .contrasenia(clienteDto.getPassword())
                    .build());
            return ClienteDto.builder()
                    .uuidCliente(cl.getClienteid())
                    .estado(cl.getEstado().toString())
                    .build();
        } catch (EntityNotFoundException exception) {
            throw new PersonaNotFoundDomainException("Persona no encontrada: " + uuidCliente + " ", exception);
        }
    }

    public boolean stringToBoolean(String value) {
        if (value.equalsIgnoreCase("true") || value.equalsIgnoreCase("false")) {
            throw new PersonaDomainException("Entrada invalida, el estado puede ser true o false" + value);
        }
        return "1".equals(value) || "true".equalsIgnoreCase(value);
    }
}
