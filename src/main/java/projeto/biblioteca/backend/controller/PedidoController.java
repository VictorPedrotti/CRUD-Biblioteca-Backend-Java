package projeto.biblioteca.backend.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import projeto.biblioteca.backend.dto.AtualizacaoItensPedidoDto;
import projeto.biblioteca.backend.dto.PedidoRequestDto;
import projeto.biblioteca.backend.dto.PedidoResponseDto;
import projeto.biblioteca.backend.models.Pedido;
import projeto.biblioteca.backend.service.PedidoService;

@RestController
@RequestMapping("/api/pedidos")
@RequiredArgsConstructor
public class PedidoController {
  
  private final PedidoService pedidoService;

  @GetMapping
  public ResponseEntity<List<PedidoResponseDto>> listarPedidos() {
    return ResponseEntity.ok(pedidoService.listarPedidos());
  }

  @GetMapping("{id}")
  public ResponseEntity<PedidoResponseDto> buscarPedidoPorId(@PathVariable Long id) {
    return ResponseEntity.ok(PedidoResponseDto.from(pedidoService.buscarPedidoPorId(id)));
  }

  @PostMapping
  public ResponseEntity<PedidoResponseDto> criarPedido(@RequestBody @Valid PedidoRequestDto dto) {
    Pedido novoPedido = pedidoService.criarPedido(dto);
    return ResponseEntity.status(HttpStatus.CREATED).body(PedidoResponseDto.from(novoPedido));
  }

  @PutMapping("{id}")
  public ResponseEntity<PedidoResponseDto> atualizarPedido(@PathVariable Long id, @RequestBody @Valid PedidoRequestDto dto) {
    return ResponseEntity.ok(PedidoResponseDto.from(pedidoService.atualizarPedido(id, dto)));
  }

  @PatchMapping("{id}")
  public ResponseEntity<PedidoResponseDto> atualizarItensPedido(@PathVariable Long id, @RequestBody @Valid AtualizacaoItensPedidoDto dto) {
    return ResponseEntity.ok(PedidoResponseDto.from(pedidoService.atualizarItensPedido(id, dto.itens())));
  }

  @DeleteMapping("{id}")
  public ResponseEntity<Void> deletarAvaliacao(@PathVariable Long id) {
    pedidoService.deletarPedido(id);
    return ResponseEntity.noContent().build();
  }
}
