package com.pixelpro.entity;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "clientes")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Cliente {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idCliente;

    @Column(nullable = false, length = 100)
    private String nombre;

    @Column(nullable = false, length = 100)
    private String apellido;

    @Column(nullable = false, unique = true, length = 150)
    private String email;

    @Column(nullable = false)
    private String password;


    private String telefono;
    private String direccion;
    private String ciudad;
    private String pais;

    @Column(updatable = false)
    private LocalDateTime fechaRegistro;

    @PrePersist
    public void prePersist() {
        fechaRegistro = LocalDateTime.now();
    }
//    @Column(nullable = false, length = 50)
//    private String rol; // Ej: "CLIENTE", "ADMIN"
}
