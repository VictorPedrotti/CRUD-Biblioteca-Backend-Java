package projeto.biblioteca.backend.dto;

import java.time.LocalDate;

import projeto.biblioteca.backend.models.Avaliacao;

public record AvaliacaoResponseDto(
  Long id,
  LocalDate dataAvaliacao,
  String comentario,
  Integer nota,
  String nomeLivro,
  String nomeCliente
) {

  public static AvaliacaoResponseDto from(Avaliacao avaliacao) {
    return new AvaliacaoResponseDto(
      avaliacao.getId(), 
      avaliacao.getDataAvaliacao(), 
      avaliacao.getComentario(), 
      avaliacao.getAvaliacao(),
      avaliacao.getLivro().getTitulo(),
      avaliacao.getCliente().getNome()); 
  }
}
