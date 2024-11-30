package ec.devsu.app.transacciones.servicio.acceso.datos.entity;

import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;
import java.util.UUID;

@Entity
@Table(name = "cuenta")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Cuenta {

    @Id
    private UUID id;

    @ManyToOne
    @JoinColumn(name = "id_persona", nullable = false)
    private Persona persona;

    @Column(name = "numero_cuenta", nullable = false, length = 20, unique = true)
    private String numeroCuenta;

    @Column(name = "tipo_cuenta", nullable = false, length = 50)
    private String tipoCuenta;

    @Column(name = "saldo_inicial_estado", nullable = false, precision = 15, scale = 2)
    private BigDecimal saldoInicialEstado;
}