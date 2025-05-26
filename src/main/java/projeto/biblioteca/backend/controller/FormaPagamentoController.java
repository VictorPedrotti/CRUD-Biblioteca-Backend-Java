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
import projeto.biblioteca.backend.dto.FormaPagamentoRequestDto;
import projeto.biblioteca.backend.dto.FormaPagamentoResponseDto;
import projeto.biblioteca.backend.models.FormaPagamento;
import projeto.biblioteca.backend.service.FormaPagamentoService;

@RestController
@RequestMapping("/api/formaspagamento")
@RequiredArgsConstructor
public class FormaPagamentoController {
  
  private final FormaPagamentoService formaPagamentoService;

  @GetMapping
  public ResponseEntity<List<FormaPagamentoResponseDto>> listarFormasPagamento() {
    return ResponseEntity.ok(formaPagamentoService.listarFormasPagamento());
  }

  @GetMapping("{id}")
  public ResponseEntity<FormaPagamentoResponseDto> buscarFormaPagamentoPorId(@PathVariable Long id) {
    return ResponseEntity.ok(FormaPagamentoResponseDto.from(formaPagamentoService.buscarFormaPagamentoPorId(id)));
  }

  @PostMapping
  public ResponseEntity<FormaPagamentoResponseDto> criarFormaPagamento(@RequestBody @Valid FormaPagamentoRequestDto dto) {
    FormaPagamento novaFormaPagamento = formaPagamentoService.criarFormaPagamento(dto);
    return ResponseEntity.status(HttpStatus.CREATED).body(FormaPagamentoResponseDto.from(novaFormaPagamento));
  }

  @PutMapping("{id}")
  public ResponseEntity<FormaPagamentoResponseDto> atualizarFormaPagamento(@PathVariable Long id, @RequestBody @Valid FormaPagamentoRequestDto dto) {
    return ResponseEntity.ok(FormaPagamentoResponseDto.from(formaPagamentoService.atualizarFormaPagamento(id, dto)));  
  }

  @DeleteMapping("{id}")
  public ResponseEntity<Void> deleterFormaPagamento(@PathVariable Long id) {
    formaPagamentoService.deletarFormaPagamento(id);
    return ResponseEntity.noContent().build();
  }

}
