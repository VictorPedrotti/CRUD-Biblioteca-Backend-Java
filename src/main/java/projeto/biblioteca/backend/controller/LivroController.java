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
import projeto.biblioteca.backend.dto.LivroRequestDto;
import projeto.biblioteca.backend.dto.LivroResponseDto;
import projeto.biblioteca.backend.models.Livro;
import projeto.biblioteca.backend.service.LivroService;

@RestController
@RequestMapping("/api/livros")
@RequiredArgsConstructor
public class LivroController {
  
  private final LivroService livroService;

  @GetMapping
  public ResponseEntity<List<LivroResponseDto>> listarLivros() {
    return ResponseEntity.ok(livroService.listarLivros());
  }
  
  @GetMapping("{id}")
  public ResponseEntity<LivroResponseDto> buscarLivroPorId(@PathVariable Long id) {
    return ResponseEntity.ok(LivroResponseDto.from(livroService.buscarLivroPorId(id)));
  }

  @PostMapping
  public ResponseEntity<LivroResponseDto> criarLivro(@RequestBody @Valid LivroRequestDto dto) {
    Livro novoLivro = livroService.criarLivro(dto);
    return ResponseEntity.status(HttpStatus.CREATED).body(LivroResponseDto.from(novoLivro));
  }

  @PutMapping("{id}")
  public ResponseEntity<LivroResponseDto> atualizarLivro(@PathVariable Long id, @RequestBody @Valid LivroRequestDto dto) {
    return ResponseEntity.ok(LivroResponseDto.from(livroService.atualizarLivro(id, dto)));
  }

  @DeleteMapping("{id}")
  public ResponseEntity<Void> deletarLivro(@PathVariable Long id) {
    livroService.deletarLivro(id);
    return ResponseEntity.noContent().build();
  }
}
