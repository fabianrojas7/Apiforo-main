package com.principal.apiforo.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.principal.apiforo.entities.Usuario;

public interface UsuarioRepository extends JpaRepository<Usuario, Long>{

    Optional<Usuario> findByNombre(String username);

}
