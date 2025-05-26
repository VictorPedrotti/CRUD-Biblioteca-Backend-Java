package projeto.biblioteca.backend.dto;

import projeto.biblioteca.backend.models.Genero;

public record GeneroResponseDto(Long id, String descricao) {

  public static GeneroResponseDto from (Genero genero) {
    return new GeneroResponseDto(
      genero.getId(), 
      genero.getDescricao());
  }

}
