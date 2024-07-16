package com.principal.apiforo.controllers;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

public interface IControllers<P, R> {


    ResponseEntity<List<R>> listar();

    ResponseEntity<R> encontrar(@PathVariable Long id);

    ResponseEntity<R> guardar(@RequestBody P nuevoRegistro);

    ResponseEntity<R> actualizar(@PathVariable Long id, @RequestBody P nuevosDatos);

    ResponseEntity<?> eliminar(@PathVariable Long id);

}
