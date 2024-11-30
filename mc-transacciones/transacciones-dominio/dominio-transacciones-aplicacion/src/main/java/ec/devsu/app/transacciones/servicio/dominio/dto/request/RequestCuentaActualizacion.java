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
    @NotNull
    private final String numeroCuenta;
    @NotNull
    private final TipoCuenta tipoCuenta;
    @NotNull
    private final BigDecimal saldo;
}
