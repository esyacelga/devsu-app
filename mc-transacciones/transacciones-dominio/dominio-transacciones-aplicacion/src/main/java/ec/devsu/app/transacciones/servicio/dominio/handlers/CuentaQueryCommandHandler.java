package ec.devsu.app.transacciones.servicio.dominio.handlers;

import ec.devsu.app.transacciones.servicio.dominio.dto.CuentaDto;
import ec.devsu.app.transacciones.servicio.dominio.helpers.CuentaQueryHelper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class CuentaQueryCommandHandler {
    private final CuentaQueryHelper cuentaQueryHelper;

    public CuentaQueryCommandHandler(CuentaQueryHelper cuentaQueryHelper) {
        this.cuentaQueryHelper = cuentaQueryHelper;
    }

    public CuentaDto obtenerCuentaPorNumero(String numeroCuenta) {
        return cuentaQueryHelper.obtenerCuentaPorNumero(numeroCuenta);
    }

}
