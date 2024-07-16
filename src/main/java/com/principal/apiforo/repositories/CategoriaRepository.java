package com.principal.apiforo.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.principal.apiforo.entities.Categoria;

public interface CategoriaRepository extends JpaRepository<Categoria, Long> {

}
