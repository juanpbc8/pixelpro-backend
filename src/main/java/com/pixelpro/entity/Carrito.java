package com.pixelpro.entity;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table(name = "carritos")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Carrito {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idCarrito;

    @ManyToOne
    @JoinColumn(name = "id_cliente", nullable = false)
    private Cliente cliente;

    private LocalDateTime fechaCreacion;

    @Column(nullable = false, length = 20)
    private String estado; // activo, convertido, cancelado

    @PrePersist
    public void prePersist() {
        fechaCreacion = LocalDateTime.now();
    }

    @OneToMany(mappedBy = "carrito", cascade = CascadeType.ALL)
    private List<CarritoDetalle> detalles;
}