package projeto.biblioteca.backend.dto;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

import projeto.biblioteca.backend.models.Pedido;

public record PedidoResponseDto(
  Long id,
  LocalDate dataPedido,
  BigDecimal total,
  String nomeCliente,
  String formaPagamento,
  List<ItemPedidoResponseDto> itens
) {

  public static PedidoResponseDto from(Pedido pedido) {
    return new PedidoResponseDto(
      pedido.getId(), 
      pedido.getDataPedido(), 
      pedido.getTotal(), 
      pedido.getCliente().getNome(), 
      pedido.getFormaPagamento().getDescricao(), 
      pedido.getItensPedidos().stream()
              .map(ItemPedidoResponseDto::from)
              .toList()
    );
  }

}
