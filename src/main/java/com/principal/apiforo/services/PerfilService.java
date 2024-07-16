package com.principal.apiforo.services;

import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.principal.apiforo.dto.perfil.PerfilRequestDTO;
import com.principal.apiforo.dto.perfil.PerfilResponseDTO;
import com.principal.apiforo.entities.Perfil;
import com.principal.apiforo.repositories.PerfilRepository;

import jakarta.persistence.EntityNotFoundException;

@Service
public class PerfilService implements IServices<PerfilRequestDTO, PerfilResponseDTO>{

    @Autowired
    private PerfilRepository perfilRepository;

    private ModelMapper modelMapper = new ModelMapper();

    public PerfilResponseDTO actualizar(Long id, PerfilRequestDTO nuevosDatos) {
        Perfil perfil = perfilRepository.findById(id).orElseThrow(() -> new EntityNotFoundException());
        perfil.setNombre(nuevosDatos.getNombre());
        return modelMapper.map(perfilRepository.save(perfil), PerfilResponseDTO.class);
    }

    public void eliminar(Long id) {
        Perfil perfil = perfilRepository.findById(id).orElseThrow(() -> new EntityNotFoundException());
        perfilRepository.delete(perfil);
    }

    public PerfilResponseDTO encontrar(Long id) {
        Perfil perfilEncontrado = perfilRepository.findById(id).orElseThrow(() -> new EntityNotFoundException());
        return modelMapper.map(perfilEncontrado, PerfilResponseDTO.class);
    }

    public PerfilResponseDTO guardar(PerfilRequestDTO nuevoRegistro) {
        Perfil perfilNuevo = modelMapper.map(nuevoRegistro, Perfil.class);
        return modelMapper.map(perfilRepository.save(perfilNuevo), PerfilResponseDTO.class);
    }

    public List<PerfilResponseDTO> listar() {
        List<Perfil> perfiles = perfilRepository.findAll();
        List<PerfilResponseDTO> perfilesResponseDTOs = perfiles.stream().map(p -> modelMapper.map(p, PerfilResponseDTO.class)).toList();
        return perfilesResponseDTOs;
    }
}
