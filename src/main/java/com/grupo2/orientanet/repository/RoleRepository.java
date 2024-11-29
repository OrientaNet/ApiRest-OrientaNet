package com.grupo2.orientanet.repository;

import com.grupo2.orientanet.model.entity.Role;
import com.grupo2.orientanet.model.enums.ERole;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface RoleRepository extends JpaRepository<Role, Long> {

    Optional<Role> findByName(ERole name);
}
