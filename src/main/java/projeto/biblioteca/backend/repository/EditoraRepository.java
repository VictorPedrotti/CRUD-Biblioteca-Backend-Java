package projeto.biblioteca.backend.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import projeto.biblioteca.backend.models.Editora;

@Repository
public interface EditoraRepository extends JpaRepository<Editora, Long>{

}
