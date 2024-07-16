package com.principal.apiforo.services;

import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.principal.apiforo.dto.usuario.UsuarioRequestDTO;
import com.principal.apiforo.dto.usuario.UsuarioResponseDTO;
import com.principal.apiforo.entities.Perfil;
import com.principal.apiforo.entities.Usuario;
import com.principal.apiforo.repositories.PerfilRepository;
import com.principal.apiforo.repositories.UsuarioRepository;

import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
@Service
public class UsuarioService implements IServices<UsuarioRequestDTO, UsuarioResponseDTO> {

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private PerfilRepository perfilRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    private ModelMapper modelMapper = new ModelMapper();

    @Transactional
    public UsuarioResponseDTO actualizar(Long id, UsuarioRequestDTO nuevosDatos) {
        Usuario usuario = usuarioRepository.findById(id).orElseThrow(() -> new EntityNotFoundException());
        Perfil perfil = perfilRepository.findById(nuevosDatos.getPerfil().getId()).orElseThrow(() -> new EntityNotFoundException());
        usuario.setContrasena(passwordEncoder.encode(nuevosDatos.getContrasena()));
        usuario.setCorreoElectronico(nuevosDatos.getCorreoElectronico());
        usuario.setNombre(nuevosDatos.getNombre());
        usuario.setPerfil(perfil);
        return modelMapper.map(usuarioRepository.save(usuario), UsuarioResponseDTO.class);
    }

    public void eliminar(Long id) {
        Usuario usuario = usuarioRepository.findById(id).orElseThrow(() -> new EntityNotFoundException());
        usuarioRepository.delete(usuario);
    }

    public UsuarioResponseDTO encontrar(Long id) {
        Usuario usuario = usuarioRepository.findById(id).orElseThrow(() -> new EntityNotFoundException());
        return modelMapper.map(usuario, UsuarioResponseDTO.class);
    }

    public UsuarioResponseDTO guardar(UsuarioRequestDTO nuevoRegistro) {
        Usuario usuario = modelMapper.map(nuevoRegistro, Usuario.class);
        usuario.setContrasena(passwordEncoder.encode(nuevoRegistro.getContrasena()));
        return modelMapper.map(usuarioRepository.save(usuario), UsuarioResponseDTO.class);
    }

    public List<UsuarioResponseDTO> listar() {
        List<Usuario> usuarios = usuarioRepository.findAll();
        List<UsuarioResponseDTO> usuarioResponseDTOs = usuarios.stream().map(u -> modelMapper.map(u, UsuarioResponseDTO.class)).toList();
        return usuarioResponseDTOs;
    }


    

}
