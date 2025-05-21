package projeto.biblioteca.backend.models;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.PrePersist;
import jakarta.persistence.PreUpdate;
import jakarta.persistence.Table;
import jakarta.validation.constraints.PositiveOrZero;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "pedidos")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Pedido {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @Column(name = "data_pedido", nullable = false)
  private LocalDate dataPedido;

  @Column(name = "total", precision = 10, scale = 2)
  @PositiveOrZero(message = "Total deve ser positivo ou zero")
  private BigDecimal total;

  @ManyToOne
  @JoinColumn(name = "cliente_id", nullable = false)
  private Cliente cliente;

  @ManyToOne
  @JoinColumn(name = "forma_pagamento_id", nullable = false)
  private FormaPagamento formaPagamento;

  @JsonIgnore
  @OneToMany(mappedBy = "pedido", cascade = CascadeType.ALL, orphanRemoval = true)
  private List<ItemPedido> itensPedidos;

  @PrePersist
  @PreUpdate
  private void calcularTotal() {
    this.total = itensPedidos.stream()
        .map(ItemPedido::getSubtotal)
        .reduce(BigDecimal.ZERO, BigDecimal::add);
  }
}
