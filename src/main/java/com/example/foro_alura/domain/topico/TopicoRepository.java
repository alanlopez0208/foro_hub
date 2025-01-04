package com.example.foro_alura.domain.topico;

import com.example.foro_alura.domain.usuario.Usuario;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;
import java.time.LocalDateTime;

public interface TopicoRepository extends JpaRepository<Topico, Long> {

    Page<Topico> findAllByUsuarioOrderByFechaCreacionAsc(Usuario usuario, Pageable pageable);


    Page<Topico> findAllByFechaCreacionIsAfter(LocalDateTime localDateTime, Pageable pageable);
}
