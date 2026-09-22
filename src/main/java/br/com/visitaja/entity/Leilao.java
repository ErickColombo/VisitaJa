package br.com.visitaja.entity;

import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Entity
@Table(name = "tb_leilao")
@Getter
@Setter
@NoArgsConstructor
public class Leilao {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, length = 150)
    private String titulo;

    @Column(length = 500)
    private String descricao;

    @Column(nullable = false, length = 20)
    private String status; // Ex: ATIVO, FINALIZADO

    @OneToMany(mappedBy = "leilao", cascade = CascadeType.ALL)
    private List<Patio> patios;
}