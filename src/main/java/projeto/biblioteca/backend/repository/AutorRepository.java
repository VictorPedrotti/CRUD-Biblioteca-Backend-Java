package projeto.biblioteca.backend.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import projeto.biblioteca.backend.models.Autor;

public interface AutorRepository extends JpaRepository<Autor, Long>{

}
