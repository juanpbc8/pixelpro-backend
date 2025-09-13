package com.pixelpro.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "orden_detalle")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class OrdenDetalle {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idDetalle;

    @ManyToOne
    @JoinColumn(name = "id_orden", nullable = false)
    private Orden orden;

    @ManyToOne
    @JoinColumn(name = "id_producto", nullable = false)
    private Producto producto;

    private Integer cantidad;
    private Double precioUnitario;
    private Double subtotal;
}
