package ec.devsu.app.persona.servicio.dominio.exception;

import ec.devsu.app.excepcion.comun.dominio.DomainException;

public class PersonaNotFoundDomainException extends DomainException {
    public PersonaNotFoundDomainException(String message) {
        super(message);
    }

    public PersonaNotFoundDomainException(String message, Throwable cause) {
        super(message, cause);
    }
}
