package projeto.biblioteca.backend.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import projeto.biblioteca.backend.models.Pedido;

public interface PedidoRepository extends JpaRepository<Pedido, Long>{

}
