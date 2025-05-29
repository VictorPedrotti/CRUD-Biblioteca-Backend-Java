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
import projeto.biblioteca.backend.dto.EditoraRequestDto;
import projeto.biblioteca.backend.dto.EditoraResponseDto;
import projeto.biblioteca.backend.models.Editora;
import projeto.biblioteca.backend.service.EditoraService;

@RestController
@RequestMapping("/api/editoras")
@RequiredArgsConstructor
public class EditoraController {
  
  private final EditoraService editoraService;

  @GetMapping
  public ResponseEntity<List<EditoraResponseDto>> listarEditoras() {
    return ResponseEntity.ok(editoraService.listarEditoras());
  }

  @GetMapping("{id}")
  public ResponseEntity<EditoraResponseDto> buscarEditoraPorId(@PathVariable Long id) {
    return ResponseEntity.ok(EditoraResponseDto.from(editoraService.buscarEditoraPorId(id)));
  }

  @PostMapping
  public ResponseEntity<EditoraResponseDto> criarEditora(@RequestBody @Valid EditoraRequestDto dto) {
    Editora novaEditora = editoraService.criarEditora(dto);
    return ResponseEntity.status(HttpStatus.CREATED).body(EditoraResponseDto.from(novaEditora));
  }

  @PutMapping("{id}")
  public ResponseEntity<EditoraResponseDto> atualizarEditora(@PathVariable Long id, @RequestBody @Valid EditoraRequestDto dto) {
    return ResponseEntity.ok(EditoraResponseDto.from(editoraService.atualizarEditora(id, dto)));
  }

  @DeleteMapping("{id}")
  public ResponseEntity<Void> deletarEditora(@PathVariable Long id) {
    editoraService.deletarEditora(id);
    return ResponseEntity.noContent().build();
  } 
}
