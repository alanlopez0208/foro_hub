package com.example.foro_alura.domain.usuario;


import com.example.foro_alura.domain.topico.Topico;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.*;

import java.util.List;

@Table(name = "usuario")
@Entity(name = "Usuario")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@EqualsAndHashCode(of = "id")
public class Usuario {
    @Id
    private Long id;

    private String nombre;

    @Column(name = "correo_electronico")
    private String correoElectronico;

    private String password;

    @OneToMany(mappedBy = "usuario")
    private List<Topico> topicos;
}
