package projeto.biblioteca.backend.models;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

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
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;
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

  @NotBlank(message = "Título é obrigatório")
  @Size(min = 2, max = 100, message = "Título deve ter entre 2 e 50 caracteres")
  private String titulo;

  @Column(name = "data_publicacao", nullable = false)
  private LocalDate dataPublicacao;

  @Column(name = "preco", precision = 10, scale = 2)
  @Positive(message = "O preço deve ser maior que zero")
  private BigDecimal preco;

  @Column(name = "numero_paginas")
  @Min(value = 1, message = "O livro ter no mínimo 1 página")
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

  @OneToMany(mappedBy = "livro", cascade = CascadeType.ALL, orphanRemoval = true)
  private List<Avaliacao> avaliacoes;

  @OneToMany(mappedBy = "livro", cascade = CascadeType.ALL, orphanRemoval = true)
  private List<ItemPedido> itensPedido;
  
}
