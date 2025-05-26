package projeto.biblioteca.backend.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import projeto.biblioteca.backend.models.FormaPagamento;

public record FormaPagamentoRequestDto(
    @NotBlank(message = "Descrição é obrigatória") @Size(min = 2, max = 30, message = "Descrição ter entre 2 e 30 caracteres") String descricao
) {

  public FormaPagamento toEntity() {
    FormaPagamento formaPagamento = new FormaPagamento();
    formaPagamento.setDescricao(descricao);
    return formaPagamento;
  }
}
