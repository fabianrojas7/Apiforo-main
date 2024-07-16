package com.principal.apiforo.dto.usuario;

import com.principal.apiforo.entities.Perfil;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UsuarioResponseDTO {

    private Long id;
    private String nombre;
    private String correoElectronico;
    private String contrasena;
    private Perfil perfil;
}
