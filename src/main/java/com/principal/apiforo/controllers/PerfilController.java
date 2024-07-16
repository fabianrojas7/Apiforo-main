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

import com.principal.apiforo.dto.perfil.PerfilRequestDTO;
import com.principal.apiforo.dto.perfil.PerfilResponseDTO;
import com.principal.apiforo.services.PerfilService;
@RestController
@RequestMapping("/perfil")
public class PerfilController implements IControllers<PerfilRequestDTO, PerfilResponseDTO> {

    @Autowired
    private PerfilService perfilService;

    @PutMapping("/update/{id}")
    public ResponseEntity<PerfilResponseDTO> actualizar(Long id, PerfilRequestDTO nuevosDatos) {
        return new ResponseEntity<>(perfilService.actualizar(id, nuevosDatos), HttpStatus.ACCEPTED);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> eliminar(Long id) {
        perfilService.eliminar(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @GetMapping("/find/{id}")
    public ResponseEntity<PerfilResponseDTO> encontrar(Long id) {
        return new ResponseEntity<>(perfilService.encontrar(id), HttpStatus.OK);
    }

    @PostMapping("/create")
    public ResponseEntity<PerfilResponseDTO> guardar(PerfilRequestDTO nuevoRegistro) {
        return new ResponseEntity<>(perfilService.guardar(nuevoRegistro), HttpStatus.CREATED);
    }

    @GetMapping("/find/all")
    public ResponseEntity<List<PerfilResponseDTO>> listar() {
        return new ResponseEntity<>(perfilService.listar(), HttpStatus.OK);
    }
}
