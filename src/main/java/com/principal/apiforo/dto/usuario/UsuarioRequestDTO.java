package com.principal.apiforo.dto.usuario;

import com.principal.apiforo.entities.Perfil;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UsuarioRequestDTO {

    @NotNull
    private String nombre;
    @Email
    private String correoElectronico;
    @NotNull
    @NotBlank
    private String contrasena;
    @NotNull
    private Perfil perfil;
}
