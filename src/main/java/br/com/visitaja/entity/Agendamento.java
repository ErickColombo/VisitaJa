package br.com.visitaja.entity;

import br.com.visitaja.enums.StatusAgendamento;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Entity
@Table(
        name = "tb_agendamento",
        uniqueConstraints = @UniqueConstraint(
                name = "uk_agendamento_visitante_horario",
                columnNames = {"visitante_id", "horario_id"}
        )
)
@Getter
@Setter
@NoArgsConstructor
public class Agendamento {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private LocalDateTime dataRegistro;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false, length = 20)
    private StatusAgendamento status;

    @ManyToOne
    @JoinColumn(name = "horario_id", nullable = false)
    private HorarioVisita horario;

    @ManyToOne
    @JoinColumn(name = "visitante_id", nullable = false)
    private Visitante visitante;
}