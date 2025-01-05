package com.example.foro_alura.domain.entity.topico;


import com.example.foro_alura.domain.dto.topico.DTOActualizarTopico;
import com.example.foro_alura.domain.dto.topico.DTORegistrarTopico;
import com.example.foro_alura.domain.entity.usuario.Usuario;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.*;

import java.time.LocalDateTime;

@Table(name = "topico")
@Entity(name = "Topico")
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(of = "id")
public class Topico {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;


    @Column(unique = true)
    @NotBlank(message = "Debe de ingresar un titulo")
    private String titulo;

    @Column(unique = true)
    @NotBlank(message = "Debe de ingresar un mensaje")
    private String mensaje;

    @Column(name = "fecha_creacion")
    private LocalDateTime fechaCreacion;

    private boolean status;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "id_usuario")
    private Usuario usuario;


    public Topico(DTORegistrarTopico registrarTopico){
        this.titulo = registrarTopico.titulo();
        this.mensaje = registrarTopico.mensaje();
        this.fechaCreacion = LocalDateTime.now();
        this.status = false;
    }

    public void actualizar(DTOActualizarTopico topico){
        this.titulo = topico.titulo();
        this.mensaje = topico.mensaje();
        this.status = topico.status();
        this.fechaCreacion = topico.fechaCreacion();

    }

}
