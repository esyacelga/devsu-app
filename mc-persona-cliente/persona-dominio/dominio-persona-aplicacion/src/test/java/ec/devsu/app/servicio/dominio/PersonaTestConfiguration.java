package ec.devsu.app.servicio.dominio;

import ec.devsu.app.servicio.dominio.puertos.output.IClientePersonaRepository;
import org.mockito.Mockito;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication(scanBasePackages = "ec.devsu.app.servicio.dominio")
public class PersonaTestConfiguration {
    @Bean
    IClientePersonaRepository clientePersonaRepository() {
        return Mockito.mock(IClientePersonaRepository.class);
    }
}
