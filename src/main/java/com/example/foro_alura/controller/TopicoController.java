package com.example.foro_alura.controller;


import com.example.foro_alura.domain.dto.topico.DTOActualizarTopico;
import com.example.foro_alura.domain.dto.topico.DTORegistrarTopico;
import com.example.foro_alura.domain.dto.topico.DTOTopico;
import com.example.foro_alura.domain.entity.topico.Topico;
import com.example.foro_alura.domain.entity.usuario.Usuario;
import com.example.foro_alura.serivce.TopicoSerivce;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
import java.time.LocalDate;

@RestController
@RequestMapping("/topico")
@SecurityRequirement(name = "bearer-key")
public class TopicoController {
    private TopicoSerivce topicoSerivce;

    @Autowired
    public TopicoController(TopicoSerivce topicoSerivce){
        this.topicoSerivce = topicoSerivce;
    }

    @PostMapping()
    public ResponseEntity<Topico> saveTopico(
            @RequestBody
            @Valid
            DTORegistrarTopico registrarTopico,
            UriComponentsBuilder uriComponentsBuilder
    ){
        Topico topico = topicoSerivce.addTopico(registrarTopico);
        URI uri = uriComponentsBuilder
                .path("/topico/{id}")
                .buildAndExpand(topico.getId())
                .toUri();


        return ResponseEntity
                .created(uri)
                .build();
    }

    @GetMapping()
    public ResponseEntity<Page<DTOTopico>> getTopicos(
            @PageableDefault(size = 10, sort = "fechaCreacion",direction = Sort.Direction.DESC)
            Pageable pageable
    ){
        var page =  topicoSerivce.getAllTopicos(pageable);
        return ResponseEntity.ok(page);
    }

    @GetMapping(params = "fecha")
    public ResponseEntity<Page<DTOTopico>> getTopicosBy(
            @RequestParam
            @DateTimeFormat(pattern = "dd/MM/yyyy", iso = DateTimeFormat.ISO.DATE)
            LocalDate fecha,
            @PageableDefault(size = 10, sort = "fechaCreacion",direction = Sort.Direction.DESC)
            Pageable pageable
    ){
        var page =  topicoSerivce.getTopicosByDate(fecha, pageable);
        return ResponseEntity.ok(page);
    }

    @GetMapping("/{id}")
    public ResponseEntity<DTOTopico> getTopico(
            @PathVariable
            Long id
    ){
        DTOTopico topico = topicoSerivce.getTopico(id);
        return ResponseEntity.ok(topico);
    }

    @PutMapping("/{id}")
    public ResponseEntity<DTOTopico> updateTopico(
            @PathVariable
            Long id,
            @RequestBody
            @Valid
            DTOActualizarTopico actualizarTopico
    ){
        DTOTopico topico = topicoSerivce.actualizarTopico(id,actualizarTopico);
        return ResponseEntity.ok(topico);
    }


    @DeleteMapping("/{id}")
    public ResponseEntity<HttpStatus> deleteTopico(
            @PathVariable
            Long id
    ){
        topicoSerivce.eliminarTopico(id);
        return ResponseEntity.noContent().build();
    }
}
