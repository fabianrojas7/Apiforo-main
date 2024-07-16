package com.principal.apiforo.services;

import java.util.List;
//request - response
public interface IServices<P, R> {

    

    List<R> listar();

    R encontrar(Long id);

    R guardar( P nuevoRegistro);

    R actualizar(Long id, P nuevosDatos);

    void eliminar(Long id);

}
