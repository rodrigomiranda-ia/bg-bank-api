package br.com.bankapi.repository;

import br.com.bankapi.model.EstoqueNota;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface EstoqueNotaRepository extends JpaRepository<EstoqueNota, Short> {

    @Query("FROM EstoqueNota e where e.quantidade > 0 order by e.valorNota desc")
    public List<EstoqueNota> obterNotasDisponiveisParaSaque();
}
