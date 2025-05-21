package projeto.biblioteca.backend.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import projeto.biblioteca.backend.models.Avaliacao;

public interface AvaliacaoRepository extends JpaRepository<Avaliacao, Long>{

}
