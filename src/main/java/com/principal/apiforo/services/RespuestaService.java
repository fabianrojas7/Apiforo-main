package com.principal.apiforo.services;

import java.time.LocalDateTime;
import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.principal.apiforo.dto.respuesta.RespuestaRequestDTO;
import com.principal.apiforo.dto.respuesta.RespuestaResponseDTO;
import com.principal.apiforo.entities.Respuesta;
import com.principal.apiforo.entities.Topico;
import com.principal.apiforo.entities.Usuario;
import com.principal.apiforo.repositories.RespuestaRepository;
import com.principal.apiforo.repositories.TopicoRepository;
import com.principal.apiforo.repositories.UsuarioRepository;

import jakarta.persistence.EntityNotFoundException;

@Service
public class RespuestaService implements IServices<RespuestaRequestDTO, RespuestaResponseDTO> {

    @Autowired
    private RespuestaRepository respuestaRepository;

    @Autowired
    private TopicoRepository topicoRepository;

    @Autowired
    private UsuarioRepository usuarioRepository;

    private ModelMapper modelMapper = new ModelMapper();

    public RespuestaResponseDTO actualizar(Long id, RespuestaRequestDTO nuevosDatos) {
        Topico topico = topicoRepository.findById(nuevosDatos.getTopico().getId()).orElseThrow(() -> new EntityNotFoundException());
        Usuario autor = usuarioRepository.findById(nuevosDatos.getAutor().getId()).orElseThrow(() -> new EntityNotFoundException());
        Respuesta respuesta = respuestaRepository.findById(id).orElseThrow(() -> new EntityNotFoundException());
        respuesta.setAutor(autor);
        respuesta.setTopico(topico);
        respuesta.setMensaje(nuevosDatos.getMensaje());
        return modelMapper.map(respuestaRepository.save(respuesta), RespuestaResponseDTO.class);
    }

    public void eliminar(Long id) {
        Respuesta respuesta = respuestaRepository.findById(id).orElseThrow(() -> new EntityNotFoundException());
        respuestaRepository.delete(respuesta);
    }

    public RespuestaResponseDTO encontrar(Long id) {
        Respuesta respuesta = respuestaRepository.findById(id).orElseThrow(() -> new EntityNotFoundException());
        return modelMapper.map(respuesta, RespuestaResponseDTO.class);
    }

    public RespuestaResponseDTO guardar(RespuestaRequestDTO nuevoRegistro) {
        Topico topico = topicoRepository.findById(nuevoRegistro.getTopico().getId()).orElseThrow(() -> new EntityNotFoundException());
        Usuario autor = usuarioRepository.findById(nuevoRegistro.getAutor().getId()).orElseThrow(() -> new EntityNotFoundException());
        Respuesta respuesta = modelMapper.map(nuevoRegistro, Respuesta.class);
        respuesta.setAutor(autor);
        respuesta.setTopico(topico);
        respuesta.setFechaCreacion(LocalDateTime.now());
        return modelMapper.map(respuestaRepository.save(respuesta), RespuestaResponseDTO.class);
    }

    public List<RespuestaResponseDTO> listar() {
        List<Respuesta> respuestas = respuestaRepository.findAll();
        List<RespuestaResponseDTO> respuestaResponseDTOs = respuestas.stream().map(r -> modelMapper.map(r, RespuestaResponseDTO.class)).toList();
        return respuestaResponseDTOs;
    }
}

