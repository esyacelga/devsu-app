package ec.devsu.app.transacciones.servicio.dominio.dto.request;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

import java.math.BigDecimal;

@Getter
@Builder
@AllArgsConstructor
public class RequestCuenta {
    @NotNull(message = "Identificacion no debe ser nula")
    private final String identificacion;
    @NotNull(message = "Tipo de cuenta no debe ser nula")
    private final String tipoCuenta;
    @NotNull(message = "El saldo no debe ser nulo")
    private final BigDecimal saldo;
}
