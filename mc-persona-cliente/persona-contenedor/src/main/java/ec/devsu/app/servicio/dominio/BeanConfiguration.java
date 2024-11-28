package ec.devsu.app.servicio.dominio;

import ec.devsu.app.servicio.dominio.impl.PersonaDomainServiceImpl;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class BeanConfiguration {
    @Bean
    IPersonaDomainService iPersonaDomainService(){
        return new PersonaDomainServiceImpl();
    }

}
