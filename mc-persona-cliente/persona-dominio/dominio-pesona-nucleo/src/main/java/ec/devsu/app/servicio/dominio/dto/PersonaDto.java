package ec.devsu.app.servicio.dominio.dto;

import lombok.*;
import lombok.experimental.SuperBuilder;

@Getter
@SuperBuilder
@AllArgsConstructor
@NoArgsConstructor
public class PersonaDto {
    private  String nombre;
    private  String genero;
    private  String edad;
    private  String identificacion;
    private  String direccion;
    private  String telefono;

}
