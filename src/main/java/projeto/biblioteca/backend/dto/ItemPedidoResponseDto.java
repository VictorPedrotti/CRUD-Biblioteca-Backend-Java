package projeto.biblioteca.backend.dto;

import java.math.BigDecimal;

import projeto.biblioteca.backend.models.ItemPedido;

public record ItemPedidoResponseDto(
  Long id,
  String tituloLivro,
  Integer quantidade,
  BigDecimal precoUnitario,
  BigDecimal subtotal
) {

  public static ItemPedidoResponseDto from(ItemPedido item) {
    return new ItemPedidoResponseDto(
      item.getId(), 
      item.getLivro().getTitulo(), 
      item.getQuantidade(), 
      item.getPrecoUnitario(), 
      item.getSubtotal());
  }

}
