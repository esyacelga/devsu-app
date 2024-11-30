package ec.devsu.app.persona.servicio.dominio;

import ec.devsu.app.persona.servicio.dominio.ClienteAppServiceImpl;
import ec.devsu.app.persona.servicio.dominio.puertos.input.IClienteAppService;
import ec.devsu.app.persona.servicio.dominio.puertos.output.IClientePersonaRepository;
import ec.devsu.app.persona.servicio.dominio.puertos.output.IPersonaRepositoryOut;
import org.mockito.Mockito;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication(scanBasePackages = "ec.devsu.app.persona.servicio.dominio")
public class PersonaTestConfiguration {
    @Bean
    IClientePersonaRepository clientePersonaRepository() {
        return Mockito.mock(IClientePersonaRepository.class);
    }
    @Bean
    IPersonaRepositoryOut personaRepositoryOut() {
        return Mockito.mock(IPersonaRepositoryOut.class);
    }

}
