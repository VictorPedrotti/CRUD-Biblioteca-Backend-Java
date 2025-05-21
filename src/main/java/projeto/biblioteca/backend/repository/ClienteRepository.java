package projeto.biblioteca.backend.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import projeto.biblioteca.backend.models.Cliente;

public interface ClienteRepository extends JpaRepository<Cliente, Long>{

}
