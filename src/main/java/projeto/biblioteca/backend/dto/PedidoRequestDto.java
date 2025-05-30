package projeto.biblioteca.backend.dto;

import java.util.List;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public record PedidoRequestDto(
  @NotNull(message = "ID do cliente é obrigatório") Long clienteId,

  @NotNull(message = "ID da forma de pagamento é obrigatório") Long formaPagamentoId,

  @NotNull(message = "Itens do pedido são obrigatórios") 
  @Size(min = 1, message = "Pedido deve ter pelo menos 1 item") 
  List<ItemPedidoRequestDto> itens
) {}
