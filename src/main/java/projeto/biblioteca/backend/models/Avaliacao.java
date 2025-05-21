package projeto.biblioteca.backend.models;

import java.time.LocalDate;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "avaliacoes")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Avaliacao {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @Column(name = "data_avaliacao", nullable = false)
  private LocalDate dataAvaliacao;

  @NotBlank(message = "Comentário é obrigatório")
  @Size(min = 1, max = 200, message = "Descrição ter entre 1 e 200 caracteres")
  private String comentario;

  @Min(1)
  @Max(5)
  private Integer avaliacao;

  @ManyToOne
  @JoinColumn(name = "livro_id")
  private Livro livro;

  @ManyToOne
  @JoinColumn(name = "cliente_id")
  private Cliente cliente;

}
