package com.api.clientes.entity;

import lombok.*;

import javax.persistence.*;

@Table(name = "clientes")
@Entity(name = "Cliente")

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
public class Cliente {

    @Id
    @GeneratedValue (strategy = GenerationType.IDENTITY)
    private Long id;
    private String nome;
    private String email;
    private String telefone;
    private String endereco;





    }




