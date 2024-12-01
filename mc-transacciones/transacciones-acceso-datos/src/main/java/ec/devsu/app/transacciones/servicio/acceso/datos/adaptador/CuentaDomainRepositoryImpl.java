package ec.devsu.app.transacciones.servicio.acceso.datos.adaptador;

import ec.devsu.app.excepcion.comun.dominio.valor.TipoCuenta;
import ec.devsu.app.transacciones.servicio.acceso.datos.entity.Cuenta;
import ec.devsu.app.transacciones.servicio.acceso.datos.entity.Persona;
import ec.devsu.app.transacciones.servicio.acceso.datos.repository.ICuentaRepository;
import ec.devsu.app.transacciones.servicio.acceso.datos.repository.IPersonaRepository;
import ec.devsu.app.transacciones.servicio.dominio.dto.CuentaDto;
import ec.devsu.app.transacciones.servicio.dominio.dto.request.RequestCuenta;
import ec.devsu.app.transacciones.servicio.dominio.exception.CuentaDomainException;
import ec.devsu.app.transacciones.servicio.dominio.puertos.output.ICuentaDomainRepository;
import jakarta.persistence.EntityNotFoundException;
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
        return cuentaDomainRepository.obtenerSiguienteSecuencial();
    }

    @Override
    public BigDecimal obtenerSaldoActual(String numeroCuenta) {
        Optional<BigDecimal> saldoOptional = cuentaDomainRepository.obtenerSaldoActual(numeroCuenta);
        return saldoOptional.orElseThrow(() -> new CuentaDomainException("Cuenta no ha sido encontrada"));
    }

    @Override
    public CuentaDto actualizarCuenta(CuentaDto cuentaDto) throws CuentaDomainException {
        if (!esNumero(cuentaDto.getNumeroCuenta()))
            throw new CuentaDomainException("No es un numero de cuenta valida");
        Cuenta cuenta = cuentaDomainRepository.actualizarCuenta(Cuenta.builder()
                .tipoCuenta(cuentaDto.getTipoCuenta().getTipo())
                .numeroCuenta(cuentaDto.getNumeroCuenta())
                .id(cuentaDto.getUuidCuenta())
                .build());
        return CuentaDto.builder()
                .uuidCuenta(cuenta.getId())
                .saldo(cuenta.getSaldoInicialEstado())
                .build();
    }

    @Override
    public void actualizarNuevoSaldo(String numeroCuenta, BigDecimal nuevoSaldo) throws CuentaDomainException {
        try {
            cuentaDomainRepository.actualizarNuevoSaldo(numeroCuenta, nuevoSaldo);
        } catch (EntityNotFoundException exception) {
            throw new CuentaDomainException("No se ha encontrado la cuenta " + numeroCuenta + " ", exception);
        }


    }

    @Override
    public CuentaDto obtenerCuentaPorNumero(String numeroCuenta) throws CuentaDomainException {
        Optional<Cuenta> cuentaOptional = cuentaDomainRepository.obtenerCuentaPorNumero(numeroCuenta);
        Cuenta cuenta = cuentaOptional.orElseThrow(() ->
                new CuentaDomainException("Persona no encontrada con la identificación especificada"));
        TipoCuenta d = TipoCuenta.valueOf(cuenta.getTipoCuenta().toUpperCase());
        return CuentaDto.builder()
                .uuidCuenta(cuenta.getId())
                .numeroCuenta(cuenta.getNumeroCuenta())
                .tipoCuenta(TipoCuenta.valueOf(cuenta.getTipoCuenta().toUpperCase()))
                .build();
    }

    public boolean esNumero(String cadena) {
        if (cadena == null || cadena.isEmpty()) {
            return false;
        }
        return cadena.matches("\\d+");
    }
}
