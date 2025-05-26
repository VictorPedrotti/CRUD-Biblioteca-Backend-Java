package projeto.biblioteca.backend.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import projeto.biblioteca.backend.models.Genero;

public record GeneroRequestDto(
  @NotBlank(message = "Descrição é obrigatória") @Size(min = 2, max = 30, message = "Descrição ter entre 2 e 30 caracteres") String descricao
) {

  public Genero toEntity() {
    Genero genero = new Genero();
    genero.setDescricao(descricao);
    return genero;
  }
}
