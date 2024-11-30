package ec.devsu.app.transacciones.servicio.dominio;

import ec.devsu.app.transacciones.servicio.dominio.puertos.output.ICuentaRepository;
import ec.devsu.app.transacciones.servicio.dominio.puertos.output.ITransaccionesRepository;
import org.mockito.Mockito;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication(scanBasePackages = "ec.devsu.app.transacciones.servicio.dominio")
public class TransaccionTestConfiguration {
    @Bean
    ITransaccionesRepository clientePersonaRepository() {
        return Mockito.mock(ITransaccionesRepository.class);
    }

    @Bean
    ICuentaRepository cuentaPersonaRepository() {
        return Mockito.mock(ICuentaRepository.class);
    }


}
