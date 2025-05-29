package projeto.biblioteca.backend.dto;

import java.math.BigDecimal;
import java.time.LocalDate;

import projeto.biblioteca.backend.models.Livro;

public record LivroResponseDto(
  Long id,
  String titulo,
  BigDecimal preco,
  int numeroPaginas,
  LocalDate dataPublicacao,
  String nomeAutor,
  String nomeGenero,
  String nomeEditora,
  String nomeFornecedor
) {

  public static LivroResponseDto from(Livro livro) {
    return new LivroResponseDto(
      livro.getId(), 
      livro.getTitulo(),
      livro.getPreco(), 
      livro.getNumeroPaginas(), 
      livro.getDataPublicacao(), 
      livro.getAutor().getNome(), 
      livro.getGenero().getDescricao(), 
      livro.getEditora().getNome(), 
      livro.getFornecedor().getNome());
  }

}
