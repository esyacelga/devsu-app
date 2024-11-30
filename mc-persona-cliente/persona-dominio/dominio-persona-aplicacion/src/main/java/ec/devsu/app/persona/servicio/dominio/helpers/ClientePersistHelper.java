package ec.devsu.app.persona.servicio.dominio.helpers;

import ec.devsu.app.persona.servicio.dominio.dto.ClienteDto;
import ec.devsu.app.persona.servicio.dominio.dto.request.RequestCliente;
import ec.devsu.app.persona.servicio.dominio.dto.response.ResponseCliente;
import ec.devsu.app.persona.servicio.dominio.exception.PersonaDomainException;
import ec.devsu.app.persona.servicio.dominio.exception.PersonaNotFoundDomainException;
import ec.devsu.app.persona.servicio.dominio.puertos.output.IClientePersonaRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.UUID;

@Slf4j
@Component
public class ClientePersistHelper {
    private final IClientePersonaRepository clientePersonaRepository;

    public ClientePersistHelper(IClientePersonaRepository clientePersonaRepository) {
        this.clientePersonaRepository = clientePersonaRepository;
    }

    @Transactional
    public ResponseCliente insertarCliente(RequestCliente cliente) {
        ClienteDto cl = clientePersonaRepository.insertarCliente(ClienteDto.builder()
                .direccion(cliente.getDireccion())
                .genero(cliente.getGenero())
                .nombre(cliente.getNombre())
                .password(cliente.getContrasenia())
                .edad(cliente.getEdad())
                .direccion(cliente.getDireccion())
                .telefono(cliente.getTelefono())
                .identificacion(cliente.getIdentificacion())
                .estado("1").build());
        return ResponseCliente.builder()
                .mensaje("CLIENTE REGISTRADO CORRECTAMENTE")
                .poaUUID(cl.getUuidCliente())
                .build();
    }

    @Transactional
    public void elimininarCliente(UUID idCliente) throws PersonaDomainException, PersonaNotFoundDomainException {
        clientePersonaRepository.eliminarCliente(idCliente);
    }

    @Transactional
    public ResponseCliente updateCliente(UUID idCliente, RequestCliente cliente) throws PersonaDomainException {
        ClienteDto cl = clientePersonaRepository.actualizarCliente(idCliente, ClienteDto.builder()
                .direccion(cliente.getDireccion())
                .genero(cliente.getGenero())
                .nombre(cliente.getNombre())
                .telefono(cliente.getTelefono())
                .identificacion(cliente.getIdentificacion())
                .uuidCliente(idCliente)
                .estado("1").build());
        return ResponseCliente.builder()
                .mensaje("CLIENTE ACTUALIZADO CORRECTAMENTE")
                .poaUUID(cl.getUuidCliente())
                .build();
    }

}
