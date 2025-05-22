package projeto.biblioteca.backend.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import projeto.biblioteca.backend.models.Genero;

@Repository
public interface GeneroRepository extends JpaRepository<Genero, Long>{

}
