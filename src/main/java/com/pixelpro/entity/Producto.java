package com.pixelpro.entity;

import jakarta.persistence.*;
import lombok.*;
import jakarta.validation.constraints.*;

import java.util.List;

@Entity
@Table(name = "productos")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Producto {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idProducto;

    @Column(nullable = false, length = 150)
    @NotBlank
    @Size(max = 150)
    private String nombre;

    @Column(columnDefinition = "TEXT")
    private String descripcion;

    @Column(nullable = false)
    @NotNull
    @Positive
    private Double precio;

    @Column(nullable = false)
    @NotNull
    @PositiveOrZero
    private Integer stock;

    private String marca;
    private String modelo;
    private String imagenUrl;

    @ManyToOne
    @JoinColumn(name = "id_categoria", nullable = false)
    @NotNull
    private Categoria categoria;

    @OneToMany(mappedBy = "producto", cascade = CascadeType.ALL)
    private List<CarritoDetalle> carritoDetalles;

    @OneToMany(mappedBy = "producto", cascade = CascadeType.ALL)
    private List<OrdenDetalle> ordenDetalles;
}
