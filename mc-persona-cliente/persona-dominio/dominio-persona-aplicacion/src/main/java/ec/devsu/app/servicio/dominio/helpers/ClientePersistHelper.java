package ec.devsu.app.servicio.dominio.helpers;

import ec.devsu.app.servicio.dominio.dto.ClienteDto;
import ec.devsu.app.servicio.dominio.dto.request.RequestCliente;
import ec.devsu.app.servicio.dominio.dto.response.ResponseCliente;
import ec.devsu.app.servicio.dominio.puertos.output.IClientePersonaRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Slf4j
@Component
public class ClientePersistHelper {
    private final IClientePersonaRepository clientePersonaRepository;

    public ClientePersistHelper(IClientePersonaRepository clientePersonaRepository) {
        this.clientePersonaRepository = clientePersonaRepository;
    }

    public ResponseCliente insertarCliente(RequestCliente cliente) {
        ClienteDto cl = clientePersonaRepository.insertarCliente(ClienteDto.builder()
                .direccion(cliente.getDireccion())
                .genero(cliente.getGenero())
                .nombre(cliente.getNombre())
                .telefono(cliente.getTelefono())
                .identificacion(cliente.getIdentificacion())
                .estado("1").build());
        return ResponseCliente.builder()
                .mensaje("CLIENTE REGISTRADO CORRECTAMENTE")
                .poaUUID(cl.getUuidCliente())
                .build();
    }

    public void elimininarCliente(UUID idCliente) {
        clientePersonaRepository.eliminarCliente(idCliente);
    }

    public ResponseCliente updateCliente(UUID idCliente, RequestCliente cliente) {
        ClienteDto cl = clientePersonaRepository.insertarCliente(ClienteDto.builder()
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
