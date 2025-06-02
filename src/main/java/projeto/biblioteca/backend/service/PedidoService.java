package projeto.biblioteca.backend.service;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;
import projeto.biblioteca.backend.dto.ItemPedidoRequestDto;
import projeto.biblioteca.backend.dto.PedidoRequestDto;
import projeto.biblioteca.backend.dto.PedidoResponseDto;
import projeto.biblioteca.backend.exceptions.RecursoNaoEncontradoException;
import projeto.biblioteca.backend.models.ItemPedido;
import projeto.biblioteca.backend.models.Pedido;
import projeto.biblioteca.backend.repository.PedidoRepository;

@Service
@RequiredArgsConstructor
public class PedidoService {

  private final PedidoRepository pedidoRepository;
  private final ClienteService clienteService;
  private final FormaPagamentoService formaPagamentoService;
  private final LivroService livroService;

  public List<PedidoResponseDto> listarPedidos() {
    return pedidoRepository.findAll()
        .stream()
        .map(PedidoResponseDto::from)
        .toList();
  }

  public Pedido buscarPedidoPorId(Long id) {
    return pedidoRepository.findById(id)
        .orElseThrow(() -> new RecursoNaoEncontradoException("Pedido não encontrado com ID: " + id));
  }

  public Pedido criarPedido(PedidoRequestDto dto) {
    Pedido pedido = new Pedido();

    pedido.setDataPedido(LocalDate.now());
    pedido.setCliente(clienteService.buscarClientePorId(dto.clienteId()));
    pedido.setFormaPagamento(formaPagamentoService.buscarFormaPagamentoPorId(dto.clienteId()));

    List<ItemPedido> itens = dto.itens().stream()
        .map(itemDto -> {
          ItemPedido item = itemDto.toEntity();
          item.setLivro(livroService.buscarLivroPorId(itemDto.livroId()));
          item.setPedido(pedido);

          if (item.getPrecoUnitario() != null && item.getQuantidade() != null) {
            item.setSubtotal(item.getPrecoUnitario().multiply(BigDecimal.valueOf(item.getQuantidade())));
          } else {
            item.setSubtotal(BigDecimal.ZERO);
          }

          return item;
        })
        .toList();

    pedido.setItensPedidos(itens);

    BigDecimal total = itens.stream()
        .map(ItemPedido::getSubtotal)
        .filter(sub -> sub != null)
        .reduce(BigDecimal.ZERO, BigDecimal::add);

    pedido.setTotal(total);

    return pedidoRepository.save(pedido);
  }

  public void deletarPedido(Long id) {

    if (!pedidoRepository.existsById(id)) {
      throw new RecursoNaoEncontradoException("Pedido não encontrado com ID: " + id);
    }

    pedidoRepository.deleteById(id);
  }

  // Atualiza o pedido por completo - método PUT
  public Pedido atualizarPedido(Long id, PedidoRequestDto dto) {
    Pedido pedido = buscarPedidoPorId(id);

    pedido.setCliente(clienteService.buscarClientePorId(dto.clienteId()));
    pedido.setFormaPagamento(formaPagamentoService.buscarFormaPagamentoPorId(dto.formaPagamentoId()));
    pedido.getItensPedidos().clear();

    List<ItemPedido> novosItens = dto.itens().stream()
        .map(itemDto -> {
          ItemPedido item = itemDto.toEntity();
          item.setLivro(livroService.buscarLivroPorId(itemDto.livroId()));
          item.setPedido(pedido);

          if (item.getPrecoUnitario() != null && item.getQuantidade() != null) {
            item.setSubtotal(item.getPrecoUnitario().multiply(BigDecimal.valueOf(item.getQuantidade())));
          } else {
            item.setSubtotal(BigDecimal.ZERO);
          }

          return item;
        })
        .toList();

    pedido.getItensPedidos().addAll(novosItens);

    BigDecimal total = novosItens.stream()
        .map(ItemPedido::getSubtotal)
        .filter(sub -> sub != null)
        .reduce(BigDecimal.ZERO, BigDecimal::add);

    pedido.setTotal(total);

    return pedidoRepository.save(pedido);
  }

  // Atualiza apenas os itens do pedido - método PATCH
  public Pedido atualizarItensPedido(Long id, List<ItemPedidoRequestDto> novosItensDto) {
    Pedido pedido = buscarPedidoPorId(id);

    pedido.getItensPedidos().clear();

    List<ItemPedido> novosItens = novosItensDto.stream()
        .map(itemDto -> {
          ItemPedido item = itemDto.toEntity();
          item.setLivro(livroService.buscarLivroPorId(itemDto.livroId()));
          item.setPedido(pedido);

          if (item.getPrecoUnitario() != null && item.getQuantidade() != null) {
            item.setSubtotal(item.getPrecoUnitario().multiply(BigDecimal.valueOf(item.getQuantidade())));
          } else {
              item.setSubtotal(BigDecimal.ZERO);
          }

          return item;
        })
        .toList();

    pedido.getItensPedidos().addAll(novosItens);

    BigDecimal total = novosItens.stream()
        .map(ItemPedido::getSubtotal)
        .filter(sub -> sub != null)
        .reduce(BigDecimal.ZERO, BigDecimal::add);

    pedido.setTotal(total);

    return pedidoRepository.save(pedido);
  }

}
