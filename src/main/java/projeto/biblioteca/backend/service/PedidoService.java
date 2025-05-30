package projeto.biblioteca.backend.service;

import java.time.LocalDate;
import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import lombok.RequiredArgsConstructor;
import projeto.biblioteca.backend.dto.ItemPedidoRequestDto;
import projeto.biblioteca.backend.dto.PedidoRequestDto;
import projeto.biblioteca.backend.dto.PedidoResponseDto;
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
            .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Pedido não encontrado com ID: " +id));
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
          return item;
        })
        .toList();
    
    pedido.setItensPedidos(itens);

    return pedidoRepository.save(pedido);
  }

  public void deletarPedido(Long id) {
    
    if(!pedidoRepository.existsById(id)) {
      throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Pedido não encontrado com ID: " + id); 
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
              return item;
            })
            .toList();

    pedido.getItensPedidos().addAll(novosItens);

    return pedidoRepository.save(pedido);
  }
  
  //Atualiza apenas os itens do pedido - método PATCH
  public Pedido atualizarItensPedido(Long id, List<ItemPedidoRequestDto> novosItensDto) {
    Pedido pedido = buscarPedidoPorId(id);
    
    pedido.getItensPedidos().clear();

    List<ItemPedido> novosItens = novosItensDto.stream()
        .map(itemDto -> {
          ItemPedido item = itemDto.toEntity();
          item.setLivro(livroService.buscarLivroPorId(itemDto.livroId()));
          item.setPedido(pedido);
          return item;
        })
        .toList();

    pedido.getItensPedidos().addAll(novosItens);

    return pedidoRepository.save(pedido);
  }

}
