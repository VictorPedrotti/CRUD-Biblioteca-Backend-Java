package projeto.biblioteca.backend.dto;

import java.time.LocalDate;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Past;
import jakarta.validation.constraints.Size;
import projeto.biblioteca.backend.models.Autor;

public record AutorRequestDto(
    @NotBlank(message = "Nome é obrigatório") @Size(min = 2, max = 100, message = "Nome deve ter entre 2 e 100 caracteres") String nome,

    @NotBlank(message = "Nacionalidade é obrigatória") String nacionalidade,

    @Past(message = "Data de nascimento deve ser no passado") @NotNull(message = "Data de nascimento é obrigatória") LocalDate nascimento) {
      
  public Autor toEntity() {
    Autor autor = new Autor();
    autor.setNome(nome);
    autor.setNacionalidade(nacionalidade);
    autor.setNascimento(nascimento);
    return autor;
  }
}
