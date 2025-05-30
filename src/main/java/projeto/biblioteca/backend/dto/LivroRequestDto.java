package projeto.biblioteca.backend.dto;

import java.math.BigDecimal;
import java.time.LocalDate;

import jakarta.validation.constraints.Digits;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;
import projeto.biblioteca.backend.models.Livro;


public record LivroRequestDto(
  @NotBlank(message = "Título é obrigatório") @Size(min = 2, max = 100, message = "Título deve ter entre 2 e 50 caracteres") String titulo,
  @Positive(message = "O preço deve ser maior que zero") @Digits(integer = 10, fraction = 2, message = "O preço deve ter no máximo 2 casas decimais") BigDecimal preco,
  @Min(value = 1, message = "O livro ter no mínimo 1 página") int numeroPaginas,
  LocalDate dataPublicacao,
  @NotNull(message = "ID do autor é obrigatório") Long autorId,
  @NotNull(message = "ID do gênero é obrigatório") Long generoId,
  @NotNull(message = "ID da editora é obrigatório") Long editoraId,
  @NotNull(message = "ID do fornecedor é obrigatório") Long fornecedorId
) {
  public Livro toEntity() {
    Livro livro = new Livro();
    livro.setTitulo(titulo);
    livro.setPreco(preco);
    livro.setNumeroPaginas(numeroPaginas);
    livro.setDataPublicacao(dataPublicacao);
    return livro;
  }
}
