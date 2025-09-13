package com.pixelpro.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "carrito_detalle")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CarritoDetalle {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idDetalle;

    @ManyToOne
    @JoinColumn(name = "id_carrito", nullable = false)
    private Carrito carrito;

    @ManyToOne
    @JoinColumn(name = "id_producto", nullable = false)
    private Producto producto;

    private Integer cantidad;
    private Double precioUnitario;
}