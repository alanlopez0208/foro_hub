package com.example.foro_alura.serivce;

import com.example.foro_alura.domain.topico.*;
import com.example.foro_alura.domain.usuario.Usuario;
import com.example.foro_alura.domain.usuario.UsuarioRepository;
import com.example.foro_alura.infra.exception.NotFoundException;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.function.Consumer;
import java.util.function.Supplier;

@Service
public class TopicoSerivce {

    private TopicoRepository topicoRepository;
    private UsuarioRepository usuarioRepository;


    @Autowired
    public TopicoSerivce(TopicoRepository topicoRepository,UsuarioRepository usuarioRepository ){
        this.topicoRepository = topicoRepository;
        this.usuarioRepository = usuarioRepository;
    }

    public Topico addTopico(DTORegistrarTopico registrarTopico){
        return usuarioRepository.findById(registrarTopico.id_usuario())
                .map(usuario -> {
                    Topico topico = new Topico(registrarTopico);
                    topico.setUsuario(usuario);
                    return topicoRepository.save(topico);
                })
                .orElseThrow(() -> new EntityNotFoundException("No se encontro al usuario especificado"));
    }

    public Page<DTOTopico> getAllTopicos(Pageable pageable){
        return topicoRepository.findAll(pageable).map(DTOTopico::new);
    }

    public Page<DTOTopico> getTopicosByDate(LocalDate localDate, Pageable pageable){
        LocalDateTime localDateTime = localDate.atTime(LocalTime.MIN);
        System.out.println(localDateTime);
        return topicoRepository.findAllByFechaCreacionIsAfter(localDateTime, pageable).map(DTOTopico::new);
    }

    public DTOTopico getTopico(Long id){
        return topicoRepository.findById(id)
                .map(DTOTopico::new)
                .orElseThrow(()-> new EntityNotFoundException("No se encontro el topico con id = "+ id));
    }


    public DTOTopico actualizarTopico(Long id, DTOActualizarTopico actualizarTopico){
        return topicoRepository.findById(id).map(t -> {
            t.actualizar(actualizarTopico);
            topicoRepository.save(t);
            return new DTOTopico(t);
        }).orElseThrow(()-> new EntityNotFoundException("No se encotro el topico con el id"+ id));
    }

    public void eliminarTopico(Long id){
        topicoRepository.findById(id).ifPresentOrElse(topico -> topicoRepository.delete(topico), () -> {
            throw new EntityNotFoundException("No se encontro el topico con el id" + id);
        });
    }

}
