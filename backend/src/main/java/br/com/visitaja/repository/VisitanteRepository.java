package br.com.visitaja.repository;

import br.com.visitaja.entity.Visitante;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface VisitanteRepository extends JpaRepository<Visitante, Long> {
    // Busca o visitante pelo documento (CPF/CNPJ)
    Optional<Visitante> findByCgc(String cgc);
}