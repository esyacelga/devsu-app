package ec.devsu.app.servicio.dominio.exception;

import ec.devsu.app.excepcion.comun.dominio.DomainException;

public class PersonaDomainException extends DomainException {
    public PersonaDomainException(String message) {
        super(message);
    }

    public PersonaDomainException(String message, Throwable cause) {
        super(message, cause);
    }
}
