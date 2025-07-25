package projeto.biblioteca.backend.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import projeto.biblioteca.backend.models.Pedido;

@Repository
public interface PedidoRepository extends JpaRepository<Pedido, Long>{
  boolean existsByClienteId(Long clienteId);
}
