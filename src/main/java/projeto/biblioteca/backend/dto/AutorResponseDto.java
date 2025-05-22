package projeto.biblioteca.backend.dto;

import java.time.LocalDate;

import projeto.biblioteca.backend.models.Autor;

public record AutorResponseDto(Long id, String nome, String nacionalidade, LocalDate dataNascimento) {

  public static AutorResponseDto from(Autor autor) {
    return new AutorResponseDto(
        autor.getId(),
        autor.getNome(),
        autor.getNacionalidade(),
        autor.getNascimento());
  }
}
