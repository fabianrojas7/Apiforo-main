package com.principal.apiforo.dto.respuesta;

import java.time.LocalDateTime;

import com.principal.apiforo.entities.Usuario;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class RespuestaResponseDTO {

    private Long id;
    private String mensaje;
    private LocalDateTime fechaCreacion;
    private Usuario autor;
    

}
