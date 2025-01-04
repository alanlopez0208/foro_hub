package com.example.foro_alura.serivce;

import com.example.foro_alura.domain.topico.DTORegistrarTopico;
import com.example.foro_alura.domain.topico.Topico;
import com.example.foro_alura.domain.usuario.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UsuarioService {

    private UsuarioRepository usuarioRepository;

    @Autowired
    public UsuarioService(UsuarioRepository usuarioRepository){
        this.usuarioRepository = usuarioRepository;
    }
}
