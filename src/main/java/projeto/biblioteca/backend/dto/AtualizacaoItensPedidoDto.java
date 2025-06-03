package projeto.biblioteca.backend.dto;

import java.util.List;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public record AtualizacaoItensPedidoDto(
  @NotNull(message = "Itens do pedido são obrigatórios")
  @Size(min = 1, message = "É necessário ao menos um item")
  List<@Valid ItemPedidoRequestDto> itens
) {}
