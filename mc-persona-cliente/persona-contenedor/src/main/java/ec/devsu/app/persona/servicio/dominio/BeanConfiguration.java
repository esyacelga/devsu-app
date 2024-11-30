package ec.devsu.app.persona.servicio.dominio;

import ec.devsu.app.persona.servicio.dominio.impl.PersonaDomainServiceImpl;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class BeanConfiguration {
    @Bean
    IPersonaDomainService iPersonaDomainService() {
        return new PersonaDomainServiceImpl();
    }

}
