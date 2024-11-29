package ec.devsu.app.servicio.dominio.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.experimental.SuperBuilder;

import java.util.UUID;

@Getter
@SuperBuilder
@AllArgsConstructor
public class ClienteDto extends PersonaDto {

    private UUID uuidCliente;
    private String estado;
    private String password;
}
