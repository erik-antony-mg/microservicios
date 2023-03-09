package com.carro.service.entity;


import lombok.Data;
import javax.persistence.*;


@Entity
@Data
@Table(name = "carros")
public class Carro {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "carro_id")
    private Long id;
    @Column(name = "marca")
    private String marca;
    @Column(name = "modelo")
    private String modelo;
    @Column(name = "usuario_id")
    private Long usuarioId;
}
