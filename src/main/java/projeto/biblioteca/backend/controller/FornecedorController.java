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

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import projeto.biblioteca.backend.dto.FornecedorRequestDto;
import projeto.biblioteca.backend.dto.FornecedorResponseDto;
import projeto.biblioteca.backend.models.Fornecedor;
import projeto.biblioteca.backend.service.FornecedorService;

@RestController
@RequestMapping("/api/fornecedores")
@RequiredArgsConstructor
public class FornecedorController {

  private final FornecedorService fornecedorService;

  @GetMapping
  public ResponseEntity<List<FornecedorResponseDto>> listarFornecedores() {
    return ResponseEntity.ok(fornecedorService.listarFornecedores());
  }

  @GetMapping("{id}")
  public ResponseEntity<FornecedorResponseDto> buscarFornecedorPorId(@PathVariable Long id) {
    return ResponseEntity.ok(FornecedorResponseDto.from(fornecedorService.buscarFornecedorPorId(id)));
  }

  @PostMapping
  public ResponseEntity<FornecedorResponseDto> criarFornecedor(@RequestBody @Valid FornecedorRequestDto dto) {
    Fornecedor novoFornecedor = fornecedorService.criarFornecedor(dto);
    return ResponseEntity.status(HttpStatus.CREATED).body(FornecedorResponseDto.from(novoFornecedor));
  }

  @PutMapping("{id}")
  public ResponseEntity<FornecedorResponseDto> atualizarFornecedor(@PathVariable Long id, @RequestBody @Valid FornecedorRequestDto dto) {
    return ResponseEntity.ok(FornecedorResponseDto.from(fornecedorService.atualizarFornecedor(id, dto)));
  }

  @DeleteMapping("{id}")
  public ResponseEntity<Void> deletarFornecedor(@PathVariable Long id) {
    fornecedorService.deletarFornecedor(id);
    return ResponseEntity.noContent().build();
  }
  
}
