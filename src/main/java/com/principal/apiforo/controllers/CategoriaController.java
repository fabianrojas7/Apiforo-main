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

import com.principal.apiforo.dto.categoria.CategoriaRequestDTO;
import com.principal.apiforo.dto.categoria.CategoriaResponseDTO;
import com.principal.apiforo.services.CategoriaService;

@RestController
@RequestMapping("/categoria")
public class CategoriaController implements IControllers<CategoriaRequestDTO, CategoriaResponseDTO> {

    @Autowired
    private CategoriaService categoriaService;

    @PutMapping("/update/{id}")
    public ResponseEntity<CategoriaResponseDTO> actualizar(Long id, CategoriaRequestDTO nuevosDatos) {
        return new ResponseEntity<>(categoriaService.actualizar(id, nuevosDatos), HttpStatus.OK);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> eliminar(Long id) {
        categoriaService.eliminar(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @GetMapping("/find/{id}")
    public ResponseEntity<CategoriaResponseDTO> encontrar(Long id) {
        return new ResponseEntity<>(categoriaService.encontrar(id), HttpStatus.OK);
    }

    @PostMapping("/create")
    public ResponseEntity<CategoriaResponseDTO> guardar(CategoriaRequestDTO nuevoRegistro) {
        return new ResponseEntity<>(categoriaService.guardar(nuevoRegistro), HttpStatus.OK);
    }

    @GetMapping("/find/all")
    public ResponseEntity<List<CategoriaResponseDTO>> listar() {
        return new ResponseEntity<>(categoriaService.listar(), HttpStatus.OK);
    }

}
