package com.example.foro_alura.infra.security;

import com.example.foro_alura.domain.entity.usuario.UsuarioRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class MyUserDetails implements UserDetailsService {

    private UsuarioRepository usuarioRepository;

    MyUserDetails(UsuarioRepository usuarioRepository){
        this.usuarioRepository = usuarioRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return usuarioRepository.findByCorreoElectronico(username).orElseThrow(()-> new EntityNotFoundException("El usuario con ese correo no existe"));
    }
}
