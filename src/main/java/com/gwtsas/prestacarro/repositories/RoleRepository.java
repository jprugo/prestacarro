package com.gwtsas.prestacarro.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.gwtsas.prestacarro.entities.EnumRole;
import com.gwtsas.prestacarro.entities.Role;

public interface RoleRepository extends JpaRepository<Role, Long>{

	Optional<Role> findByName(EnumRole name);
}
