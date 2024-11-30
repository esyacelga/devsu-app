package ec.devsu.app.transacciones.servicio.contendor;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@EnableJpaRepositories(basePackages = {"ec.devsu.app.transacciones.servicio.acceso.datos", "ec.devsu.app.comun.acceso.datos"})
@EntityScan(basePackages = {"ec.devsu.app.transacciones.servicio.acceso.datos", "ec.devsu.app.comun.acceso.datos"})
@SpringBootApplication(scanBasePackages = {"ec.devsu.app.transacciones.servicio","ec.devsu.app.comun"} )

public class TransaccionesServiceApplication {
    public static void main(String[] args) {
        SpringApplication.run(TransaccionesServiceApplication.class, args);
    }
}
