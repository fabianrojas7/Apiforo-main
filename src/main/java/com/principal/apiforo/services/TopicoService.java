package com.principal.apiforo.services;

import java.time.LocalDateTime;
import java.time.Month;
import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.principal.apiforo.dto.topico.TopicoRequestDTO;
import com.principal.apiforo.dto.topico.TopicoResponseDTO;
import com.principal.apiforo.entities.Curso;
import com.principal.apiforo.entities.Status;
import com.principal.apiforo.entities.Topico;
import com.principal.apiforo.entities.Usuario;
import com.principal.apiforo.repositories.CursoRepository;
import com.principal.apiforo.repositories.TopicoRepository;
import com.principal.apiforo.repositories.UsuarioRepository;

import jakarta.persistence.EntityNotFoundException;
@Service
public class TopicoService implements IServices<TopicoRequestDTO, TopicoResponseDTO> {

    @Autowired
    private TopicoRepository topicoRepository;

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private CursoRepository cursoRepository;

    private ModelMapper modelMapper = new ModelMapper();

    public TopicoResponseDTO actualizar(Long id, TopicoRequestDTO nuevosDatos) {
        Usuario autor = usuarioRepository.findById(nuevosDatos.getAutor().getId()).orElseThrow(() -> new EntityNotFoundException());
        Curso curso = cursoRepository.findById(nuevosDatos.getCurso().getId()).orElseThrow(() -> new EntityNotFoundException());
        Topico topicoActualizado = topicoRepository.findById(id).orElseThrow(() -> new EntityNotFoundException());  
        topicoActualizado.setTitulo(nuevosDatos.getTitulo());
        topicoActualizado.setMensaje(nuevosDatos.getMensaje());
        topicoActualizado.setAutor(autor);
        topicoActualizado.setCurso(curso);
        return modelMapper.map(topicoRepository.save(topicoActualizado), TopicoResponseDTO.class);
    }

    public void eliminar(Long id) {
        Topico topico = topicoRepository.findById(id).orElseThrow(() -> new EntityNotFoundException());
        topicoRepository.delete(topico);
    }

    public TopicoResponseDTO encontrar(Long id) {
        Topico topico = topicoRepository.findById(id).orElseThrow(() -> new EntityNotFoundException());
        return modelMapper.map(topico, TopicoResponseDTO.class);
    }

    public TopicoResponseDTO guardar(TopicoRequestDTO nuevoRegistro) {
        Usuario autor = usuarioRepository.findById(nuevoRegistro.getAutor().getId()).orElseThrow(() -> new EntityNotFoundException());
        Curso curso = cursoRepository.findById(nuevoRegistro.getCurso().getId()).orElseThrow(() -> new EntityNotFoundException());
        Topico topicoNuevo = modelMapper.map(nuevoRegistro, Topico.class);
        topicoNuevo.setStatus(Status.ACTIVO);
        topicoNuevo.setFechaCreacion(LocalDateTime.now());
        topicoNuevo.setAutor(autor);
        topicoNuevo.setCurso(curso);
        return modelMapper.map(topicoRepository.save(topicoNuevo), TopicoResponseDTO.class);
    }

    public List<TopicoResponseDTO> listar() {
        // List<Topico> topicos = topicoRepository.encontrarTopicosActivos(Status.ACTIVO);
        List<Topico> topicos = topicoRepository.findAll();
        List<TopicoResponseDTO> listaTopicoResponseDTOs = topicos.stream().map(t -> modelMapper.map(t, TopicoResponseDTO.class)).toList();
        return listaTopicoResponseDTOs;
    }
    // public List<TopicoResponseDTO> listarInactivos() {
    //     List<Topico> topicos = topicoRepository.encontrarTopicosActivos(Status.INACTIVO);
    //     List<TopicoResponseDTO> listaTopicoResponseDTOs = topicos.stream().map(t -> modelMapper.map(t, TopicoResponseDTO.class)).toList();
    //     return listaTopicoResponseDTOs;
    // }

    public List<TopicoResponseDTO> listarAscendente() {
        List<Topico> topicos = topicoRepository.findTop10ByOrderByFechaCreacionAsc();
        List<TopicoResponseDTO> topicoResponseDTOs = topicos.stream().map(t -> modelMapper.map(t, TopicoResponseDTO.class)).toList();
        return topicoResponseDTOs;
    }

    public Page<TopicoResponseDTO> listarPorCursoYAnio(Pageable pageable, String curso, int anio){
        LocalDateTime inicioDelAnio = LocalDateTime.of(anio, Month.JANUARY, 1, 0, 0, 0);
        LocalDateTime finDelAnio = LocalDateTime.of(anio, Month.DECEMBER, 31, 23, 59, 59);
        Page<Topico> topicos = topicoRepository.encontrarPorCursoYAnio(pageable, curso, inicioDelAnio, finDelAnio);
        return topicos.map(t -> modelMapper.map(t, TopicoResponseDTO.class));
    }
}
