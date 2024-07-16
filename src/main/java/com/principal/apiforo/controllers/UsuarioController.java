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

import com.principal.apiforo.dto.usuario.UsuarioRequestDTO;
import com.principal.apiforo.dto.usuario.UsuarioResponseDTO;
import com.principal.apiforo.services.UsuarioService;
@RestController
@RequestMapping("/usuario")
public class UsuarioController implements IControllers<UsuarioRequestDTO, UsuarioResponseDTO> {

    @Autowired
    private UsuarioService usuarioService;

    @PutMapping("/update/{id}")
    public ResponseEntity<UsuarioResponseDTO> actualizar(Long id, UsuarioRequestDTO nuevosDatos) {
        return new ResponseEntity<>(usuarioService.actualizar(id, nuevosDatos), HttpStatus.ACCEPTED);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> eliminar(Long id) {
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @GetMapping("/find/{id}")
    public ResponseEntity<UsuarioResponseDTO> encontrar(Long id) {
        return new ResponseEntity<>(usuarioService.encontrar(id), HttpStatus.OK);
    }
    @PostMapping("/create")
    public ResponseEntity<UsuarioResponseDTO> guardar(UsuarioRequestDTO nuevoRegistro) {
        return new ResponseEntity<>(usuarioService.guardar(nuevoRegistro), HttpStatus.CREATED);
    }
    @GetMapping("/find/all")
    public ResponseEntity<List<UsuarioResponseDTO>> listar() {
        return new ResponseEntity<>(usuarioService.listar(), HttpStatus.OK);
    }
}
