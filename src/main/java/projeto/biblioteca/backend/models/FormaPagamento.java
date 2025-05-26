package projeto.biblioteca.backend.models;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "formas_pagamento")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class FormaPagamento {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  private String descricao;

  @JsonIgnore
  @OneToMany(mappedBy = "formaPagamento", cascade = CascadeType.ALL, orphanRemoval = true)
  private List<Pedido> pedidos;
}
