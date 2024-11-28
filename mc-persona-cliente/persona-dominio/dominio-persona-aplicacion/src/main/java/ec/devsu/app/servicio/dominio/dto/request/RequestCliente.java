package ec.devsu.app.servicio.dominio.dto.request;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

import java.util.UUID;

@Getter
@Builder
@AllArgsConstructor
public class RequestCliente extends RequestPersona {
    @NotNull
    private final UUID clienteID;
    @NotNull
    private final String contrasenia;
    @NotNull
    private final String estado;
}
