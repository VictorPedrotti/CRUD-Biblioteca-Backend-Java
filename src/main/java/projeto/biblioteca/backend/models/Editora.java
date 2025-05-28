package projeto.biblioteca.backend.models;

import java.time.LocalDate;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
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
@Table(name = "editoras")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Editora {
  
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  private String nome;

  @Column(name = "data_fundacao")
  private LocalDate dataFundacao;

  @JsonIgnore
  @OneToMany(mappedBy = "editora", cascade = CascadeType.ALL, orphanRemoval = true)
  private List<Livro> livros;
}
