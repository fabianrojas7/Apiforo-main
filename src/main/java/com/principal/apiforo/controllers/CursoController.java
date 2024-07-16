package com.principal.apiforo.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.principal.apiforo.dto.curso.CursoRequestDTO;
import com.principal.apiforo.dto.curso.CursoResponseDTO;
import com.principal.apiforo.services.CursoService;

@RestController
@RequestMapping("/curso")
public class CursoController implements IControllers<CursoRequestDTO, CursoResponseDTO> {


    @Autowired
    private CursoService cursoService;

    @PutMapping("/update/{id}")
    public ResponseEntity<CursoResponseDTO> actualizar(Long id, CursoRequestDTO nuevosDatos) {
        return new ResponseEntity<>(cursoService.actualizar(id, nuevosDatos), HttpStatus.ACCEPTED);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> eliminar(Long id) {
        cursoService.eliminar(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @GetMapping("/find/{id}")
    public ResponseEntity<CursoResponseDTO> encontrar(Long id) {
        return new ResponseEntity<>(cursoService.encontrar(id), HttpStatus.OK);
    }

    @PostMapping("/create")
    public ResponseEntity<CursoResponseDTO> guardar(CursoRequestDTO nuevoRegistro) {
        return new ResponseEntity<>(cursoService.guardar(nuevoRegistro), HttpStatus.CREATED);
    }

    @GetMapping("/find/all")
    public ResponseEntity<List<CursoResponseDTO>> listar() {
        return new ResponseEntity<>(cursoService.listar(), HttpStatus.OK);
    }

}
