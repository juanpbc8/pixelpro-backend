package com.pixelpro.entity;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "pagos")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Pago {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idPago;

    @OneToOne
    @JoinColumn(name = "id_orden", nullable = false)
    private Orden orden;

    private String metodo; // tarjeta, Yape, etc.
    private Double monto;
    private LocalDateTime fechaPago;

    @Column(nullable = false, length = 20)
    private String estado; // pendiente, exitoso, fallido

    private String referenciaPasarela;
}
