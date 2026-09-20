package br.com.visitaja.entity;

import jakarta.persistence.*;
import lombok.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "tb_horario_visita")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class HorarioVisita {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private LocalDateTime dataHora;

    @Column(nullable = false)
    private Integer vagasTotais;

    @Column(nullable = false)
    private Integer vagasDisponiveis;

    @ManyToOne
    @JoinColumn(name = "patio_id", nullable = false)
    private Patio patio;
}