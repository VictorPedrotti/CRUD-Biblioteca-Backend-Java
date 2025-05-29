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
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "livros")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Livro {
  
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  private String titulo;

  @Column(name = "data_publicacao", nullable = false)
  private LocalDate dataPublicacao;

  @Column(name = "preco", precision = 10, scale = 2)
  private BigDecimal preco;

  @Column(name = "numero_paginas")  
  private Integer numeroPaginas;

  @ManyToOne
  @JoinColumn(name = "genero_id")
  private Genero genero;

  @ManyToOne
  @JoinColumn(name = "editora_id")
  private Editora editora;

  @ManyToOne
  @JoinColumn(name = "autor_id")
  private Autor autor;

  @ManyToOne
  @JoinColumn(name = "fornecedor_id")
  private Fornecedor fornecedor;

  @JsonIgnore
  @OneToMany(mappedBy = "livro", cascade = CascadeType.ALL, orphanRemoval = true)
  private List<Avaliacao> avaliacoes;

  @JsonIgnore
  @OneToMany(mappedBy = "livro", cascade = CascadeType.ALL, orphanRemoval = true)
  private List<ItemPedido> itensPedido;
  
}
