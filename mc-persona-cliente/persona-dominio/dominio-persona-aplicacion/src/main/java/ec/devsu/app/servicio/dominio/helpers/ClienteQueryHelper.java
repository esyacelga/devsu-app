package ec.devsu.app.servicio.dominio.helpers;

import ec.devsu.app.servicio.dominio.dto.ClienteDto;
import ec.devsu.app.servicio.dominio.dto.response.ResponseClientePersona;
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
        ClienteDto cl = clientePersonaRepository.buscarClientePorId(uuidCliente);
        return ResponseClientePersona.builder()
                .estado(cl.getEstado())
                .identificacion(cl.getIdentificacion())
                .genero(cl.getGenero())
                .edad(cl.getEdad())
                .nombre(cl.getNombre())
                .telefono(cl.getTelefono())
                .build();
    }
}
