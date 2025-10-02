package com.pixelpro.entity;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

import jakarta.validation.constraints.*;

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
    @NotBlank
    @Size(max = 100)
    private String nombre;

    @Column(nullable = false, length = 100)
    @NotBlank
    @Size(max = 100)
    private String apellido;

    @Column(nullable = false, unique = true, length = 150)
    @NotBlank
    @Email
    @Size(max = 150)
    private String email;

    @Column(nullable = false)
    @NotBlank
    @Size(min = 8, max = 100)
    private String password;

    @Size(max = 50)
    private String telefono;
    @Size(max = 255)
    private String direccion;
    @Size(max = 100)
    private String ciudad;
    @Size(max = 100)
    private String pais;

    @Column(updatable = false)
    @PastOrPresent
    private LocalDateTime fechaRegistro;

    @PrePersist
    public void prePersist() {
        fechaRegistro = LocalDateTime.now();
    }
//    @Column(nullable = false, length = 50)
//    private String rol; // Ej: "CLIENTE", "ADMIN"
}
