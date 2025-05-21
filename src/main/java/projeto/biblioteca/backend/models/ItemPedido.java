package projeto.biblioteca.backend.models;

import java.math.BigDecimal;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.PrePersist;
import jakarta.persistence.PreUpdate;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.Positive;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "itens_pedido")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ItemPedido {
  
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @Min(value = 1, message = "A quantidade deve ser no mínimo 1")
  private Integer quantidade;

  @Column(name = "preco_unitario", precision = 10, scale = 2)
  @Positive(message = "O valor deve ser maior que zero")
  private BigDecimal precoUnitario;

  @Column(name = "subtotal", precision = 10, scale = 2, nullable = false)
  private BigDecimal subtotal;

  @ManyToOne
  @JoinColumn(name = "pedido_id", nullable = false)
  private Pedido pedido;

  @ManyToOne
  @JoinColumn(name = "livro_id", nullable = false)
  private Livro livro;

  @PrePersist
  @PreUpdate
  private void calcularSubtotal() {
    this.subtotal = this.precoUnitario.multiply(BigDecimal.valueOf(quantidade));
  }
}
