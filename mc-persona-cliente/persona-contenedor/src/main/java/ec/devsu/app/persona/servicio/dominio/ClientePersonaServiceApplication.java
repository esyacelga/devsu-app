package ec.devsu.app.persona.servicio.dominio;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@EnableJpaRepositories(basePackages = {"ec.devsu.app.persona.servicio.acceso.datos", "ec.devsu.app.comun.acceso.datos"})
@EntityScan(basePackages = {"ec.devsu.app.persona.servicio.acceso.datos", "ec.devsu.app.comun.acceso.datos"})
@SpringBootApplication(scanBasePackages = {"ec.devsu.app.persona.servicio","ec.devsu.app.comun"} )

public class ClientePersonaServiceApplication {
    public static void main(String[] args) {
        SpringApplication.run(ClientePersonaServiceApplication.class, args);
    }
}
