package com.principal.apiforo.dto.respuesta;

import com.principal.apiforo.entities.Topico;
import com.principal.apiforo.entities.Usuario;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class RespuestaRequestDTO {

    @NotBlank
    private String mensaje;
    @NotNull
    private Topico topico;
    @NotNull
    private Usuario autor;

}
