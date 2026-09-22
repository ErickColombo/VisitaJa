package br.com.visitaja.entity;

import br.com.visitaja.enums.StatusLeilao;
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

    @Enumerated(EnumType.STRING)
    @Column(nullable = false, length = 20)
    private StatusLeilao status;

    @OneToMany(mappedBy = "leilao", cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    private List<Patio> patios;
}