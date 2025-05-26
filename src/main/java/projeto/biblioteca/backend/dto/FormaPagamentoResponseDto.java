package projeto.biblioteca.backend.dto;

import projeto.biblioteca.backend.models.FormaPagamento;

public record FormaPagamentoResponseDto(Long id, String descricao) {

  public static FormaPagamentoResponseDto from(FormaPagamento formaPagamento) {
    return new FormaPagamentoResponseDto(
      formaPagamento.getId(), 
      formaPagamento.getDescricao());
  }
}
