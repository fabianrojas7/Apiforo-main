package com.principal.apiforo.repositories;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.principal.apiforo.entities.Status;
import com.principal.apiforo.entities.Topico;

public interface TopicoRepository extends JpaRepository<Topico,Long> {

    @Query("select t from Topico t where t.status = :status")
    List<Topico> encontrarTopicosActivos(Status status);


    List<Topico> findTop10ByOrderByFechaCreacionAsc();

    @Query("SELECT t FROM Topico t JOIN t.curso c where c.nombre = :curso and t.fechaCreacion between :inicioDelAnio and :finDelAnio")
    Page<Topico> encontrarPorCursoYAnio(Pageable pageable, String curso, LocalDateTime inicioDelAnio, LocalDateTime finDelAnio);

}
