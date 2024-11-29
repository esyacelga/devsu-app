package ec.devsu.app.servicio.dominio.dto.request;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.experimental.SuperBuilder;

@Getter
@SuperBuilder
@AllArgsConstructor
public class RequestCliente extends RequestPersona {
    @NotNull
    private final String contrasenia;
    @NotNull
    private final String estado;
}
