package projeto.biblioteca.backend.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import projeto.biblioteca.backend.models.FormaPagamento;

public interface FormaPagamentoRepository extends JpaRepository<FormaPagamento, Long> {

}
