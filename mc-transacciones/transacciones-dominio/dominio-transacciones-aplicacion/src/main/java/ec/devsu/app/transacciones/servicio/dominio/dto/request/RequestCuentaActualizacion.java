package ec.devsu.app.transacciones.servicio.dominio.dto.request;

import ec.devsu.app.excepcion.comun.dominio.valor.TipoCuenta;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

import java.math.BigDecimal;

@Getter
@Builder
@AllArgsConstructor
public class RequestCuentaActualizacion {
    @NotNull(message = "El numero de cuenta no puede estar en nulo")
    private final String numeroCuenta;
    @NotNull(message = "El tipo de cuenta no puede estar en nulo")
    private final TipoCuenta tipoCuenta;
    @NotNull(message = "El saldo no puede estar en nulo")
    private final BigDecimal saldo;
}
