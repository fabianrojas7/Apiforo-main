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

import com.principal.apiforo.dto.respuesta.RespuestaRequestDTO;
import com.principal.apiforo.dto.respuesta.RespuestaResponseDTO;
import com.principal.apiforo.services.RespuestaService;

@RestController
@RequestMapping("/respuesta")
public class RespuestaController implements IControllers<RespuestaRequestDTO, RespuestaResponseDTO> {

    @Autowired
    private RespuestaService respuestaService;

    @PutMapping("/update/{id}")
    public ResponseEntity<RespuestaResponseDTO> actualizar(Long id, RespuestaRequestDTO nuevosDatos) {
        return new ResponseEntity<>(respuestaService.actualizar(id, nuevosDatos), HttpStatus.ACCEPTED);
    }
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> eliminar(Long id) {
        respuestaService.eliminar(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
    @GetMapping("/find/{id}")
    public ResponseEntity<RespuestaResponseDTO> encontrar(Long id) {
        return new ResponseEntity<>(respuestaService.encontrar(id), HttpStatus.OK);
    }
    @PostMapping("/create")
    public ResponseEntity<RespuestaResponseDTO> guardar(RespuestaRequestDTO nuevoRegistro) {
        return new ResponseEntity<>(respuestaService.guardar(nuevoRegistro), HttpStatus.CREATED);
    }
    @GetMapping("/find/all")
    public ResponseEntity<List<RespuestaResponseDTO>> listar() {
        return new ResponseEntity<>(respuestaService.listar(), HttpStatus.OK);
    }

    




}
