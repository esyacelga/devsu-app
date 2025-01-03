package ec.devsu.app.persona.servicio.dominio;

import ec.devsu.app.persona.servicio.dominio.puertos.output.IClientePersonaDomainRepository;
import ec.devsu.app.persona.servicio.dominio.puertos.output.IPersonaDomainRepository;
import org.mockito.Mockito;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication(scanBasePackages = "ec.devsu.app.persona.servicio.dominio")
public class PersonaTestConfiguration {
    @Bean
    IClientePersonaDomainRepository clientePersonaRepository() {
        return Mockito.mock(IClientePersonaDomainRepository.class);
    }
    @Bean
    IPersonaDomainRepository personaRepositoryOut() {
        return Mockito.mock(IPersonaDomainRepository.class);
    }

}
