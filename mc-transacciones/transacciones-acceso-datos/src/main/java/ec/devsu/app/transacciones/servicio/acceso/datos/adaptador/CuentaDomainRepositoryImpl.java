package ec.devsu.app.transacciones.servicio.acceso.datos.adaptador;

import ec.devsu.app.transacciones.servicio.acceso.datos.entity.Cuenta;
import ec.devsu.app.transacciones.servicio.acceso.datos.entity.Persona;
import ec.devsu.app.transacciones.servicio.acceso.datos.repository.ICuentaRepository;
import ec.devsu.app.transacciones.servicio.acceso.datos.repository.IPersonaRepository;
import ec.devsu.app.transacciones.servicio.dominio.dto.CuentaDto;
import ec.devsu.app.transacciones.servicio.dominio.dto.request.RequestCuenta;
import ec.devsu.app.transacciones.servicio.dominio.exception.CuentaDomainException;
import ec.devsu.app.transacciones.servicio.dominio.puertos.output.ICuentaDomainRepository;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.Optional;

@Component
public class CuentaDomainRepositoryImpl implements ICuentaDomainRepository {
    private final ICuentaRepository cuentaDomainRepository;
    private final IPersonaRepository personaRepository;

    public CuentaDomainRepositoryImpl(ICuentaRepository cuentaDomainRepository,
                                      IPersonaRepository personaRepository) {
        this.cuentaDomainRepository = cuentaDomainRepository;
        this.personaRepository = personaRepository;
    }

    @Override
    public CuentaDto insertarCuentaPersona(RequestCuenta requestCuenta, String numeroCuenta) {
        Optional<Persona> personaOptional = personaRepository.findByIdentificacion(requestCuenta.getIdentificacion());
        Persona personaBD = personaOptional.orElseThrow(() ->
                new CuentaDomainException("Persona no encontrada con la identificación especificada"));
        Cuenta cuenta = cuentaDomainRepository.insertarCuentaPersona(Cuenta.builder()
                .tipoCuenta(requestCuenta.getTipoCuenta().getTipo())
                .numeroCuenta(numeroCuenta)
                .persona(personaBD)
                .saldoInicialEstado(requestCuenta.getSaldo())
                .build());
        return CuentaDto.builder()
                .numeroCuenta(cuenta.getNumeroCuenta())
                .uuidCuenta(cuenta.getId())
                .build();
    }

    @Override
    public Integer obtenerSiguienteSecuencial() {
        return null;
    }

    @Override
    public BigDecimal obtenerSaldoActual(String numeroCuenta) {
        return null;
    }

    @Override
    public CuentaDto obtenerCuentaPersona(String numeroCuenta) {
        return null;
    }

    @Override
    public CuentaDto actualizarCuenta(CuentaDto cuentaDto) {
        return null;
    }

    @Override
    public CuentaDto actualizarNuevoSaldo(String numeroCuenta, BigDecimal nuevoSaldo) {
        return null;
    }

    @Override
    public CuentaDto obtenerCuentaPorNumero(String numeroCuenta) {
        return null;
    }
}
