package br.com.visitaja.repository;

import br.com.visitaja.entity.Patio;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PatioRepository extends JpaRepository<Patio, Long> {
    // Busca todos os pátios vinculados a um ID de leilão
    List<Patio> findByLeilaoId(Long leilaoId);
}