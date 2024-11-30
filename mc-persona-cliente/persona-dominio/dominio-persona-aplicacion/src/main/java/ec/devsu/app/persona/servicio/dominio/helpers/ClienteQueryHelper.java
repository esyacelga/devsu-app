package ec.devsu.app.persona.servicio.dominio.helpers;

import ec.devsu.app.persona.servicio.dominio.dto.response.ResponseClientePersona;
import ec.devsu.app.persona.servicio.dominio.exception.PersonaDomainException;
import ec.devsu.app.persona.servicio.dominio.exception.PersonaNotFoundDomainException;
import ec.devsu.app.persona.servicio.dominio.puertos.output.IClientePersonaRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Slf4j
@Component
public class ClienteQueryHelper {
    private final IClientePersonaRepository clientePersonaRepository;

    public ClienteQueryHelper(IClientePersonaRepository clientePersonaRepository) {
        this.clientePersonaRepository = clientePersonaRepository;
    }

    public ResponseClientePersona buscarClientePorId(UUID uuidCliente) {
        return clientePersonaRepository.buscarClientePorId(uuidCliente)
                .map(cl -> ResponseClientePersona.builder()
                        .estado(cl.getEstado())
                        .identificacion(cl.getIdentificacion())
                        .genero(cl.getGenero())
                        .direccion(cl.getDireccion())
                        .uuidPersona(cl.getUuidCliente())
                        .edad(cl.getEdad())
                        .nombre(cl.getNombre())
                        .telefono(cl.getTelefono())
                        .build())
                .orElseThrow(() -> new PersonaNotFoundDomainException("Cliente con UUID " + uuidCliente + " no encontrado"));
    }
}
