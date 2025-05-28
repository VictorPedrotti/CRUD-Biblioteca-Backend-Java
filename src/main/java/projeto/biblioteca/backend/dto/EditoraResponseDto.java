package projeto.biblioteca.backend.dto;

import java.time.LocalDate;

import projeto.biblioteca.backend.models.Editora;

public record EditoraResponseDto(Long id, String nome, LocalDate dataFundacao) {

  public static EditoraResponseDto from(Editora editora) {
    return new EditoraResponseDto(
      editora.getId(), 
      editora.getNome(), 
      editora.getDataFundacao());
  }
}
