package com.principal.apiforo.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.principal.apiforo.entities.Curso;

public interface CursoRepository extends JpaRepository<Curso,Long> {

}
