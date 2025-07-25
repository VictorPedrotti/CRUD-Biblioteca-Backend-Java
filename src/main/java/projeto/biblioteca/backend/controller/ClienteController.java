package projeto.biblioteca.backend.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import projeto.biblioteca.backend.dto.ClienteRequestDto;
import projeto.biblioteca.backend.dto.ClienteResponseDto;
import projeto.biblioteca.backend.models.Cliente;
import projeto.biblioteca.backend.service.ClienteService;

@RestController
@RequestMapping("/api/clientes")
@Tag(name = "Clientes", description = "Gerencia os clientes")
@RequiredArgsConstructor
public class ClienteController {
  
  private final ClienteService clienteService;

  @Operation(summary = "Listar todos os clientes", method = "GET")
  @GetMapping
  public ResponseEntity<List<ClienteResponseDto>> listarClientes() {
    return ResponseEntity.ok(clienteService.listarClientes());
  }

  @Operation(summary = "Listar cliente por ID", method = "GET")
  @GetMapping("{id}")
  public ResponseEntity<ClienteResponseDto> buscarClientePorId(@PathVariable Long id) {
    return ResponseEntity.ok(ClienteResponseDto.from(clienteService.buscarClientePorId(id)));
  }

  @Operation(summary = "Criar cliente", method = "POST")
  @PostMapping
  public ResponseEntity<ClienteResponseDto> criarAutor(@RequestBody @Valid ClienteRequestDto dto) {
    Cliente novoCliente = clienteService.criarCliente(dto);
    return ResponseEntity.status(HttpStatus.CREATED).body(ClienteResponseDto.from(novoCliente));
  }

  @Operation(summary = "Atualizar cliente", method = "PUT")
  @PutMapping("{id}")
  public ResponseEntity<ClienteResponseDto> atualizarCliente(@PathVariable Long id, @RequestBody @Valid ClienteRequestDto dto) {
    return ResponseEntity.ok(ClienteResponseDto.from(clienteService.atualizarCliente(id, dto)));
  }

  @Operation(summary = "Deletar cliente", method = "DELETE")
  @DeleteMapping("{id}")
  public ResponseEntity<Void> deletarCliente(@PathVariable Long id) {
    clienteService.deletarCliente(id);
    return ResponseEntity.noContent().build();
  }
}
