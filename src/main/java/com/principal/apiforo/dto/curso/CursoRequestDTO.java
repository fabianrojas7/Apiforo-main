package com.principal.apiforo.dto.curso;

import com.principal.apiforo.entities.Categoria;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CursoRequestDTO {

    private String nombre;
    private Categoria categoria;

}
