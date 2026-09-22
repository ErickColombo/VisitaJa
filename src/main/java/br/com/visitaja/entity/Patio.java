package br.com.visitaja.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "tb_patio")
@Getter
@Setter
@NoArgsConstructor
public class Patio {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, length = 100)
    private String nome;

    @Column(nullable = false, length = 255)
    private String endereco;

    @ManyToOne
    @JoinColumn(name = "leilao_id", nullable = false)
    private Leilao leilao;
}