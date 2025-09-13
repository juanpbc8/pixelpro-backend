package com.pixelpro.entity;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table(name = "ordenes")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Orden {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idOrden;

    @ManyToOne
    @JoinColumn(name = "id_cliente", nullable = false)
    private Cliente cliente;

    private LocalDateTime fechaCreacion;

    @Column(nullable = false, length = 20)
    private String estado; // pendiente, pagado, enviado, entregado, cancelado

    private Double total;

    @PrePersist
    public void prePersist() {
        fechaCreacion = LocalDateTime.now();
    }

    @OneToMany(mappedBy = "orden", cascade = CascadeType.ALL)
    private List<OrdenDetalle> detalles;

    @OneToOne(mappedBy = "orden", cascade = CascadeType.ALL)
    private Pago pago;

//    @OneToOne(mappedBy = "orden", cascade = CascadeType.ALL)
//    private Envio envio;
}
