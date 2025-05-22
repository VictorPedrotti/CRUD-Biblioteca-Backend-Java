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
import projeto.biblioteca.backend.dto.AutorRequestDto;
import projeto.biblioteca.backend.dto.AutorResponseDto;
import projeto.biblioteca.backend.models.Autor;
import projeto.biblioteca.backend.service.AutorService;

@RestController
@RequestMapping("/api/autores")
@RequiredArgsConstructor
public class AutorController {
  
  private final AutorService autorService;

  @GetMapping
  public ResponseEntity<List<AutorResponseDto>> listarAutores() {
    return ResponseEntity.ok(autorService.listarAutores());  
  }

  @GetMapping("{id}")
  public ResponseEntity<AutorResponseDto> buscarAutorPorId(@PathVariable Long id) {
    return ResponseEntity.ok(AutorResponseDto.from(autorService.buscarAutorPorId(id)));
  }


  @PostMapping
  public ResponseEntity<AutorResponseDto> criarAutor(@RequestBody @Valid AutorRequestDto dto) {
    Autor novoAutor = autorService.criarAutor(dto);
    return ResponseEntity.status(HttpStatus.CREATED).body(AutorResponseDto.from(novoAutor));
  }

  @PutMapping("{id}")
  public ResponseEntity<AutorResponseDto> atualizarAutor(@PathVariable Long id, @RequestBody @Valid AutorRequestDto dto) {
    return ResponseEntity.ok(AutorResponseDto.from(autorService.atualizarAutor(id, dto)));
  }

  @DeleteMapping("{id}")
  public ResponseEntity<Void> deletarAutor(@PathVariable Long id) {
    autorService.deletarAutor(id);
    return ResponseEntity.noContent().build();
  }

}
