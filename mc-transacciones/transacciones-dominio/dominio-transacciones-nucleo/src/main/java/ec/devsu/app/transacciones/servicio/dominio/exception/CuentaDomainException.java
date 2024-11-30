package ec.devsu.app.transacciones.servicio.dominio.exception;

import ec.devsu.app.excepcion.comun.dominio.DomainException;

public class CuentaDomainException extends DomainException {
    public CuentaDomainException(String message) {
        super(message);
    }

    public CuentaDomainException(String message, Throwable cause) {
        super(message, cause);
    }
}
