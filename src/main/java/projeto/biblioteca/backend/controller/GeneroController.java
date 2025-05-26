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
import projeto.biblioteca.backend.dto.GeneroRequestDto;
import projeto.biblioteca.backend.dto.GeneroResponseDto;
import projeto.biblioteca.backend.models.Genero;
import projeto.biblioteca.backend.service.GeneroService;

@RestController
@RequestMapping("/api/generos")
@RequiredArgsConstructor
public class GeneroController {
  
  private final GeneroService generoService;

  @GetMapping
  public ResponseEntity<List<GeneroResponseDto>> listarGeneros() {
    return ResponseEntity.ok(generoService.listarGeneros());
  }

  @GetMapping("{id}")
  public ResponseEntity<GeneroResponseDto> buscarGeneroPorId(@PathVariable Long id) {
    return ResponseEntity.ok(GeneroResponseDto.from(generoService.buscarGeneroPorId(id)));
  }

  @PostMapping
  public ResponseEntity<GeneroResponseDto> criarGenero(@RequestBody @Valid GeneroRequestDto dto) {
    Genero novoGenero = generoService.criarGenero(dto);
    return ResponseEntity.status(HttpStatus.CREATED).body(GeneroResponseDto.from(novoGenero));
  }

  @PutMapping("{id}")
  public ResponseEntity<GeneroResponseDto> atualizarGenero(@PathVariable Long id, @RequestBody @Valid GeneroRequestDto dto) {
    return ResponseEntity.ok(GeneroResponseDto.from(generoService.atualizarGenero(id, dto)));
  }

  @DeleteMapping("{id}")
  public ResponseEntity<Void> deletarAutor (@PathVariable Long id) {
    generoService.deletarGenero(id);
    return ResponseEntity.noContent().build();
  }
}
