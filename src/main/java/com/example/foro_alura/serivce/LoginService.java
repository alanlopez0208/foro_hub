package com.example.foro_alura.serivce;

import com.example.foro_alura.domain.dto.auth.DTOAuth;
import com.example.foro_alura.domain.entity.usuario.Usuario;
import com.example.foro_alura.domain.entity.usuario.UsuarioRepository;
import com.example.foro_alura.infra.security.MyUserDetails;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

@Service
public class LoginService {

    private UsuarioRepository usuarioRepository;
    private MyUserDetails myUserDetails;
    private AuthenticationManager authenticationManager;
    private JWTService jwtService;

    @Autowired
    public LoginService(UsuarioRepository usuarioRepository, MyUserDetails myUserDetails, AuthenticationManager authenticationManager, JWTService jwtService){
        this.usuarioRepository = usuarioRepository;
        this.myUserDetails = myUserDetails;
        this.authenticationManager =  authenticationManager;
        this.jwtService = jwtService;
    }


    public String login(DTOAuth dtoAuth){
        UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(
                dtoAuth.correo(),
                dtoAuth.password()
        );
        Authentication authentication = authenticationManager.authenticate(authenticationToken);
        Usuario usuario = (Usuario) authentication.getPrincipal();
        return jwtService.getToken(usuario);
    }
}
