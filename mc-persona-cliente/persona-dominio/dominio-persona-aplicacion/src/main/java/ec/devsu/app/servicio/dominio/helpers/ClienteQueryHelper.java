package ec.devsu.app.servicio.dominio.helpers;

import ec.devsu.app.servicio.dominio.dto.response.ResponseClientePersona;
import ec.devsu.app.servicio.dominio.exception.PersonaDomainException;
import ec.devsu.app.servicio.dominio.puertos.output.IClientePersonaRepository;
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
                        .uuidPersona(cl.getUuidCliente())
                        .edad(cl.getEdad())
                        .nombre(cl.getNombre())
                        .telefono(cl.getTelefono())
                        .build())
                .orElseThrow(() -> new PersonaDomainException("Cliente con UUID " + uuidCliente + " no encontrado"));
    }
}
