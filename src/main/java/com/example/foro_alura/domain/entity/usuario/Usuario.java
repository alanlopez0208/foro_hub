package com.example.foro_alura.domain.entity.usuario;


import com.example.foro_alura.domain.entity.topico.Topico;
import jakarta.persistence.*;
import lombok.*;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.List;

@Table(name = "usuario")
@Entity(name = "Usuario")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@EqualsAndHashCode(of = "id")
public class Usuario implements UserDetails {
    @Id
    private Long id;

    private String nombre;

    @Column(name = "correo_electronico")
    private String correoElectronico;

    private String password;

    @OneToMany(mappedBy = "usuario")
    private List<Topico> topicos;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return List.of(new SimpleGrantedAuthority("ROLE_USER"));
    }

    @Override
    public String getUsername() {
        return this.correoElectronico;
    }

}
