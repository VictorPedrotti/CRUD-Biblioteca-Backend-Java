package projeto.biblioteca.backend.dto;

import java.time.LocalDate;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Past;
import jakarta.validation.constraints.Size;
import projeto.biblioteca.backend.models.Editora;

public record EditoraRequestDto(
  @NotBlank(message = "Nome é obrigatório") @Size(min = 2, max = 50, message = "Nome deve ter entre 2 e 50 caracteres") String nome,
  @Past(message = "Data de fundação deve ser uma data no passado") LocalDate dataFundacao
) {

  public Editora toEntity() {
    Editora editora = new Editora();
    editora.setNome(nome);
    editora.setDataFundacao(dataFundacao);
    return editora;
  }
}
