package projeto.biblioteca.backend.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import projeto.biblioteca.backend.models.Autor;

@Repository
public interface AutorRepository extends JpaRepository<Autor, Long>{

}
