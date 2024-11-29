package ec.devsu.app.persona.servicio.dominio.dto.response;

import ec.devsu.app.persona.servicio.dominio.dto.request.RequestPersona;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.experimental.SuperBuilder;

@Getter
@SuperBuilder
@AllArgsConstructor
public class ResponseClientePersona extends RequestPersona {
    @NotNull
    private final String estado;
}
