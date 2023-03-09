package com.usuario.service.entity;

import lombok.Data;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name = "usuarios")
@Data
public class Usuario {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "usuario_id")
    private Long usuarioId;
    @Column(name = "nombre")
    private String nombre;
    @Column(name = "apellido")
    private String apellido;
    @Column(name = "email")
    private String email;
    @Column(name = "telefono")
    private String telefono;
    @Column(name = "fecha_Nac")
    private LocalDate fechaNacimiento;
    @Column(name = "edad")
    private Integer edad;

    @PrePersist
    private void obtenerEdad(){
        edad=LocalDate.now().getYear()-fechaNacimiento.getYear();
    }
}
