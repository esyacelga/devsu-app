package ec.devsu.app.transacciones.servicio.dominio.dto.request;

import ec.devsu.app.excepcion.comun.dominio.valor.TipoMovimiento;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

import java.math.BigDecimal;
import java.util.UUID;

@Getter
@Builder
@AllArgsConstructor
public class RequestMovimientoActualizacion {
    private final UUID uuidMovimiento;
    @NotNull
    private final String numeroCuenta;
    @NotNull
    private final TipoMovimiento tipoMovimiento;
    @NotNull
    private BigDecimal valor;
}
