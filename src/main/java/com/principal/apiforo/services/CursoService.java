package com.principal.apiforo.services;

import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.principal.apiforo.dto.curso.CursoRequestDTO;
import com.principal.apiforo.dto.curso.CursoResponseDTO;
import com.principal.apiforo.entities.Categoria;
import com.principal.apiforo.entities.Curso;
import com.principal.apiforo.repositories.CategoriaRepository;
import com.principal.apiforo.repositories.CursoRepository;

import jakarta.persistence.EntityNotFoundException;

@Service
public class CursoService implements IServices<CursoRequestDTO, CursoResponseDTO> {

    @Autowired
    private CursoRepository cursoRepository;

    @Autowired
    private CategoriaRepository categoriaRepository;

    private ModelMapper modelMapper = new ModelMapper();

    @Override
    public CursoResponseDTO actualizar(Long id, CursoRequestDTO nuevosDatos) {
        Curso curso = cursoRepository.findById(id).orElseThrow(() -> new EntityNotFoundException());
        Categoria categoria = categoriaRepository.findById(nuevosDatos.getCategoria().getId()).orElseThrow(() -> new EntityNotFoundException());
        curso.setCategoria(categoria);
        curso.setNombre(nuevosDatos.getNombre());
        return modelMapper.map(cursoRepository.save(curso), CursoResponseDTO.class);
    }

    @Override
    public void eliminar(Long id) {
        Curso curso = cursoRepository.findById(id).orElseThrow(() -> new EntityNotFoundException());
        cursoRepository.delete(curso);
    }

    @Override
    public CursoResponseDTO encontrar(Long id) {
        Curso cursoEncontrado = cursoRepository.findById(id).orElseThrow(() -> new EntityNotFoundException());
        return modelMapper.map(cursoEncontrado, CursoResponseDTO.class);
    }

    @Override
    public CursoResponseDTO guardar(CursoRequestDTO nuevoRegistro) {
        Curso cursoNuevo = modelMapper.map(nuevoRegistro, Curso.class);
        return modelMapper.map(cursoRepository.save(cursoNuevo), CursoResponseDTO.class);
    }

    @Override
    public List<CursoResponseDTO> listar() {
        List<Curso> cursos = cursoRepository.findAll();
        List<CursoResponseDTO> cursoResponseDTOs = cursos.stream().map(c -> modelMapper.map(c, CursoResponseDTO.class)).toList();
        return cursoResponseDTOs;
    }


    



}
