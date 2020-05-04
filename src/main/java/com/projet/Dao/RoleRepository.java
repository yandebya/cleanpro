package com.projet.Dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.projet.Entite.Role;

public interface RoleRepository extends JpaRepository<Role, Long> {
	public Role findByRole(String role);
}
