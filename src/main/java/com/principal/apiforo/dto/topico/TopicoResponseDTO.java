package com.principal.apiforo.dto.topico;

import java.time.LocalDateTime;
import java.util.List;

import com.principal.apiforo.entities.Curso;
import com.principal.apiforo.entities.Respuesta;
import com.principal.apiforo.entities.Status;
import com.principal.apiforo.entities.Usuario;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class TopicoResponseDTO {
    
    private String titulo;
    private String mensaje;
    private LocalDateTime fechaCreacion;
    private Status status;
    private Usuario autor;
    private Curso curso;
    private List<Respuesta> respuestas;

}
