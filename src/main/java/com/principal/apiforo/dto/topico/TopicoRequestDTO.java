package com.principal.apiforo.dto.topico;

import com.principal.apiforo.entities.Curso;
import com.principal.apiforo.entities.Usuario;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class TopicoRequestDTO {

    private String titulo;
    private String mensaje;
    private Usuario autor;
    private Curso curso;

}
