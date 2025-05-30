package projeto.biblioteca.backend.dto;

import java.math.BigDecimal;

import jakarta.validation.constraints.Digits;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import projeto.biblioteca.backend.models.ItemPedido;

public record ItemPedidoRequestDto(
  @Min(value = 1, message = "A quantidade mínima é 1") 
  Integer quantidade,

  @Positive(message = "Preço unitário deve ser positivo") 
  @Digits(integer = 10, fraction = 2, message = "Preço unitário deve ter no máximo 2 casas decimais")
  BigDecimal precoUnitario,

  @NotNull(message = "ID do livro é obrigatório") 
  Long livroId
) {

  public ItemPedido toEntity() {
    ItemPedido item = new ItemPedido();
    item.setQuantidade(quantidade);
    item.setPrecoUnitario(precoUnitario);
    return item;
  }

}
