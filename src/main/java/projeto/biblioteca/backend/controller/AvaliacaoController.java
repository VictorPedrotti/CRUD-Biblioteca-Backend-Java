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
import projeto.biblioteca.backend.dto.AvaliacaoRequestDto;
import projeto.biblioteca.backend.dto.AvaliacaoResponseDto;
import projeto.biblioteca.backend.models.Avaliacao;
import projeto.biblioteca.backend.service.AvaliacaoService;

@RestController
@RequestMapping("/api/avaliacoes")
@Tag(name = "Avaliações", description = "Gerencia as avaliações")
@RequiredArgsConstructor
public class AvaliacaoController {
  
  private final AvaliacaoService avaliacaoService;

  @Operation(summary = "Listar todas as avaliações", method = "GET")
  @GetMapping
  public ResponseEntity<List<AvaliacaoResponseDto>> listarAvaliacoes() {
    return ResponseEntity.ok(avaliacaoService.listarAvaliacoes());
  }

  @Operation(summary = "Listar avaliação por ID", method = "GET")
  @GetMapping("{id}")
  public ResponseEntity<AvaliacaoResponseDto> buscarAvaliacaoPorId(@PathVariable Long id) {
    return ResponseEntity.ok(AvaliacaoResponseDto.from(avaliacaoService.buscarAvaliacaoPorId(id)));
  }

  @Operation(summary = "Criar avaliação", method = "POST")
  @PostMapping
  public ResponseEntity<AvaliacaoResponseDto> criarAvaliacao(@RequestBody @Valid AvaliacaoRequestDto dto) {
    Avaliacao novaAvaliacao = avaliacaoService.criarAvaliacao(dto);
    return ResponseEntity.status(HttpStatus.CREATED).body(AvaliacaoResponseDto.from(novaAvaliacao));
  }

  @Operation(summary = "Atualizar avaliação", method = "PUT")
  @PutMapping("{id}")
  public ResponseEntity<AvaliacaoResponseDto> atualizarAvaliacao(@PathVariable Long id, @RequestBody @Valid AvaliacaoRequestDto dto) {
    return ResponseEntity.ok(AvaliacaoResponseDto.from(avaliacaoService.atualizarAvaliacao(id, dto)));
  }

  @Operation(summary = "Deletar avaliação", method = "DELETE")
  @DeleteMapping("{id}")
  public ResponseEntity<Void> deletarAvaliacao(@PathVariable Long id) {
    avaliacaoService.deletarAvaliacao(id);
    return ResponseEntity.noContent().build();
  }


}
