package com.principal.apiforo.services;

import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.principal.apiforo.dto.categoria.CategoriaRequestDTO;
import com.principal.apiforo.dto.categoria.CategoriaResponseDTO;
import com.principal.apiforo.entities.Categoria;
import com.principal.apiforo.repositories.CategoriaRepository;

import jakarta.persistence.EntityNotFoundException;

@Service
public class CategoriaService implements IServices<CategoriaRequestDTO, CategoriaResponseDTO>{

    @Autowired
    private CategoriaRepository categoriaRepository;

    
    private ModelMapper modelMapper = new ModelMapper();

    @Override
    public CategoriaResponseDTO actualizar(Long id, CategoriaRequestDTO nuevosDatos) {
        Categoria categoria = categoriaRepository.findById(id).orElseThrow(() -> new EntityNotFoundException());

        categoria.setNombre(nuevosDatos.getNombre());

        return modelMapper.map(categoriaRepository.save(categoria), CategoriaResponseDTO.class);
    }

    @Override
    public void eliminar(Long id) {
        Categoria categoria = categoriaRepository.findById(id).orElseThrow(() -> new EntityNotFoundException());
        categoriaRepository.delete(categoria);
    
    }

    @Override
    public CategoriaResponseDTO encontrar(Long id) {
        Categoria categoriaEncontrada = categoriaRepository.findById(id).orElseThrow(() -> new EntityNotFoundException());
        return modelMapper.map(categoriaEncontrada, CategoriaResponseDTO.class);
    }

    @Override
    public CategoriaResponseDTO guardar(CategoriaRequestDTO nuevoRegistro) {
        Categoria nuevaCategoria = modelMapper.map(nuevoRegistro, Categoria.class);
        CategoriaResponseDTO categoriaResponseDTO = modelMapper.map(categoriaRepository.save(nuevaCategoria), CategoriaResponseDTO.class);
        return categoriaResponseDTO;
    }

    @Override
    public List<CategoriaResponseDTO> listar() {
        List<Categoria> lista = categoriaRepository.findAll();
        List<CategoriaResponseDTO> listaCategoriaResponseDTOs = lista.stream().map(c -> modelMapper.map(c, CategoriaResponseDTO.class)).toList();
        return listaCategoriaResponseDTOs;
    }

}
