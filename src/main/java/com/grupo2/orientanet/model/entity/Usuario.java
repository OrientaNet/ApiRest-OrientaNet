package com.grupo2.orientanet.model.entity;

import com.grupo2.orientanet.exception.CustomErrorResponse;
import com.grupo2.orientanet.model.entity.Role;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "usuarios")
public class Usuario {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "email", nullable = false)
    private String email;

    @Column(name = "contrasena", nullable = false)
    private String contrasena;

    @ManyToOne(fetch = FetchType.EAGER) //carga temprana
    @JoinColumn(name="role_id", referencedColumnName = "id")
    private Role role;

    @OneToOne(mappedBy = "usuario", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private Estudiante estudiante;

    @OneToOne(mappedBy = "usuario", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private Experto experto;
}
