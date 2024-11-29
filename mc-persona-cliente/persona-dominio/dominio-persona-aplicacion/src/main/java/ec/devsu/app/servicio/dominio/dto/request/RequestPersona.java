package ec.devsu.app.servicio.dominio.dto.request;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import java.util.UUID;

@Getter
@SuperBuilder
@AllArgsConstructor
@NoArgsConstructor
public class RequestPersona {
    private UUID uuidPersona;
    @NotNull
    private String nombre;
    @NotNull
    private String genero;
    @NotNull
    private String edad;
    @NotNull
    private String identificacion;
    @NotNull
    private String direccion;
    @NotNull
    private String telefono;
}
