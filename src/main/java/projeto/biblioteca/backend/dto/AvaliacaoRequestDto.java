package projeto.biblioteca.backend.dto;

import java.time.LocalDate;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import projeto.biblioteca.backend.models.Avaliacao;

public record AvaliacaoRequestDto(
  LocalDate dataAvaliacao,
  @NotBlank(message = "Comentário é obrigatório") @Size(min = 1, max = 200, message = "Descrição ter entre 1 e 200 caracteres") String comentario,
  @Min(1) @Max(5) Integer nota,
  @NotNull(message = "ID do livro é obrigatório") Long livroId,
  @NotNull(message = "ID do cliente é obrigatório") Long clienteId
) {
  public Avaliacao toEntity() {
    Avaliacao avaliacao = new Avaliacao();
    avaliacao.setComentario(comentario);
    avaliacao.setDataAvaliacao(dataAvaliacao);
    avaliacao.setAvaliacao(nota);
    return avaliacao;
  }

}
