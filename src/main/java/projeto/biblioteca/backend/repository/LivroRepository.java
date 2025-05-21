package projeto.biblioteca.backend.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import projeto.biblioteca.backend.models.Livro;

public interface LivroRepository extends JpaRepository<Livro, Long>{

}
