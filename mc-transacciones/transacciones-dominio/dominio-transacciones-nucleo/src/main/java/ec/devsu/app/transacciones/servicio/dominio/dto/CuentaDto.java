package ec.devsu.app.transacciones.servicio.dominio.dto;

import ec.devsu.app.excepcion.comun.dominio.valor.TipoCuenta;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

import java.math.BigDecimal;
import java.util.UUID;

@Getter
@Builder
@AllArgsConstructor
public class CuentaDto {
    private final UUID uuidCuenta;
    private final String identificacion;
    private final String numeroCuenta;
    private final BigDecimal saldo;
    private final TipoCuenta tipoCuenta;
    private final Boolean estado;
}
