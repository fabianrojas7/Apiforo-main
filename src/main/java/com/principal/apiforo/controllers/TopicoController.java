package com.principal.apiforo.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.principal.apiforo.dto.topico.TopicoRequestDTO;
import com.principal.apiforo.dto.topico.TopicoResponseDTO;
import com.principal.apiforo.services.TopicoService;
@RestController
@RequestMapping("/topico")
public class TopicoController implements IControllers<TopicoRequestDTO, TopicoResponseDTO>{

    @Autowired
    private TopicoService topicoService;

    @PutMapping("/update/{id}")
    public ResponseEntity<TopicoResponseDTO> actualizar(Long id, TopicoRequestDTO nuevosDatos) {
        return new ResponseEntity<>(topicoService.actualizar(id, nuevosDatos), HttpStatus.ACCEPTED);
    }
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> eliminar(Long id) {
        topicoService.eliminar(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
    @GetMapping("/find/{id}")
    public ResponseEntity<TopicoResponseDTO> encontrar(Long id) {
        return new ResponseEntity<>(topicoService.encontrar(id), HttpStatus.OK);
    }
    @PostMapping("/create")
    public ResponseEntity<TopicoResponseDTO> guardar(TopicoRequestDTO nuevoRegistro) {
        return new ResponseEntity<>(topicoService.guardar(nuevoRegistro), HttpStatus.CREATED);
    }
    @GetMapping("/find/all")
    public ResponseEntity<List<TopicoResponseDTO>> listar() {
        return new ResponseEntity<>(topicoService.listar(), HttpStatus.OK);
    }

    @GetMapping("/find/ten/asc")
    public ResponseEntity<List<TopicoResponseDTO>> listarAsc() {
        return new ResponseEntity<>(topicoService.listarAscendente(), HttpStatus.OK);
    }

    @GetMapping("/find")
    public ResponseEntity<Page<TopicoResponseDTO>> listarPorCursoYAnio(@PageableDefault(size = 1) Pageable pageable,@RequestParam("curso") String curso, @RequestParam("anio") int anio) {
        return new ResponseEntity<>(topicoService.listarPorCursoYAnio(pageable,curso, anio), HttpStatus.OK);
    }
    


}
