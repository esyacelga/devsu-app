package ec.devsu.app.servicio.acceso.datos.adaptador;

import ec.devsu.app.servicio.acceso.datos.entity.Cliente;
import ec.devsu.app.servicio.acceso.datos.entity.Persona;
import ec.devsu.app.servicio.acceso.datos.repository.IClienteRepository;
import ec.devsu.app.servicio.acceso.datos.repository.IPersonaRepository;
import ec.devsu.app.servicio.dominio.dto.ClienteDto;
import ec.devsu.app.servicio.dominio.puertos.output.IClientePersonaRepository;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
public class ClientePersonaRepositoryImpl implements IClientePersonaRepository {
    private final IPersonaRepository personaRepository;
    private final IClienteRepository clientePersonaRepository;

    public ClientePersonaRepositoryImpl(IPersonaRepository personaRepository,
                                        IClienteRepository clientePersonaRepository) {
        this.personaRepository = personaRepository;
        this.clientePersonaRepository = clientePersonaRepository;
    }


    @Override
    public ClienteDto buscarClientePorId(UUID uuidCliente) {
        Cliente cliente = clientePersonaRepository.buscarPorId(uuidCliente);
        return ClienteDto.builder()
                .uuidCliente(uuidCliente)
                .estado(cliente.getEstado())
                .build();
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
    public void eliminarCliente(UUID uuidCliente) {
        clientePersonaRepository.eliminarCliente(uuidCliente);
    }

    @Override
    public ClienteDto insertarCliente(ClienteDto clienteDto) {
        Persona persona = personaRepository.insertarPersona(Persona.builder()
                .cliente(Cliente.builder()
                        .clienteid(UUID.randomUUID())
                        .contrasenia(clienteDto.getPassword())
                        .estado(clienteDto.getEstado()).build())
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
                .build();
    }


}
