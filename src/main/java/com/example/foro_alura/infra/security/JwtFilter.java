package com.example.foro_alura.infra.security;

import com.example.foro_alura.domain.entity.usuario.Usuario;
import com.example.foro_alura.domain.entity.usuario.UsuarioRepository;
import com.example.foro_alura.serivce.JWTService;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.hibernate.annotations.Filter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.authorization.AuthorizationDeniedException;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.Optional;


@Component
public class JwtFilter extends OncePerRequestFilter {


    private final String HEADER_AUTHORIZATION = "Authorization";
    private final String HEADER_BEARER = "Bearer ";
    private JWTService jwtService;

    private UsuarioRepository usuarioRepository;


    @Autowired
    public JwtFilter(JWTService jwtService, UsuarioRepository usuarioRepository){
        this.jwtService = jwtService;
        this.usuarioRepository = usuarioRepository;
    }




    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        String header = request.getHeader(HEADER_AUTHORIZATION);
        if(header != null){
            String token = header.replace(HEADER_BEARER, "");
            String username = jwtService.getUserName(token);
            if (username != null){
                System.out.println(username);
                var optional = usuarioRepository.findByCorreoElectronico(username);
                if (optional.isPresent()){
                    var usuario =  optional.get();
                    UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(
                            usuario.getUsername(),
                            usuario.getPassword(),
                            usuario.getAuthorities()
                    );
                    SecurityContextHolder.getContext().setAuthentication(authenticationToken);
                }
            }
        }
        filterChain.doFilter(request, response);
    }
}
