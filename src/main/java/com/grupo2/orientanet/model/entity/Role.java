package com.grupo2.orientanet.model.entity;

import jakarta.persistence.*;
import lombok.Data;
import com.grupo2.orientanet.model.enums.ERole;

@Entity
@Data
@Table(name = "roles")
public class Role {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Enumerated(EnumType.STRING)
    private ERole name;

}
