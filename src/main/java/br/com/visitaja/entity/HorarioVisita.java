package br.com.visitaja.entity;

import jakarta.persistence.*;
import lombok.*;
import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table(name = "tb_horario_visita")
@Getter
@Setter
@NoArgsConstructor
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

    @OneToMany(mappedBy = "horario", cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    private List<Agendamento> agendamentos;;
}