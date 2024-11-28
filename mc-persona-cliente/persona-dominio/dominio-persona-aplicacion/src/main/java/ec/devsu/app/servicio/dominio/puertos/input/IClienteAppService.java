package ec.devsu.app.servicio.dominio.puertos.input;

import ec.devsu.app.servicio.dominio.dto.request.RequestCliente;
import ec.devsu.app.servicio.dominio.dto.response.ResponseCliente;
import ec.devsu.app.servicio.dominio.dto.response.ResponseClientePersona;
import jakarta.validation.Valid;

import java.util.UUID;

public interface IClienteAppService {

    ResponseCliente insertarCliente(@Valid RequestCliente cliente);

    ResponseCliente updateCliente(@Valid UUID idCliente, RequestCliente cliente);
    ResponseClientePersona buscarClientePorId(@Valid UUID uuidCliente);
    void elimininarCliente(@Valid UUID uuidCliente);




}
