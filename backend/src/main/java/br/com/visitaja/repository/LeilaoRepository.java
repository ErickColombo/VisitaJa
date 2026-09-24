package br.com.visitaja.repository;

import br.com.visitaja.entity.Leilao;
import br.com.visitaja.enums.StatusLeilao;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface LeilaoRepository extends JpaRepository<Leilao, Long> {
    // Retorna apenas os leilões que estão com status ABERTO, por exemplo
    List<Leilao> findByStatus(StatusLeilao status);
}