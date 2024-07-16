package com.principal.apiforo.dto.categoria;

import com.principal.apiforo.entities.Categoria;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CategoriaRequestDTO {

    private String nombre;
    private Categoria categoria;
    
}
