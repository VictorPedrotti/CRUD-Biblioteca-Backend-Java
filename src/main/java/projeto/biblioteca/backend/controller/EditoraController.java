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
import projeto.biblioteca.backend.dto.EditoraRequestDto;
import projeto.biblioteca.backend.dto.EditoraResponseDto;
import projeto.biblioteca.backend.models.Editora;
import projeto.biblioteca.backend.service.EditoraService;

@RestController
@RequestMapping("/api/editoras")
@Tag(name = "Editoras", description = "Gerencia as editoras")
@RequiredArgsConstructor
public class EditoraController {
  
  private final EditoraService editoraService;

  @Operation(summary = "Listar todas as editoras", method = "GET")
  @GetMapping
  public ResponseEntity<List<EditoraResponseDto>> listarEditoras() {
    return ResponseEntity.ok(editoraService.listarEditoras());
  }

  @Operation(summary = "Listar editora por ID", method = "GET")
  @GetMapping("{id}")
  public ResponseEntity<EditoraResponseDto> buscarEditoraPorId(@PathVariable Long id) {
    return ResponseEntity.ok(EditoraResponseDto.from(editoraService.buscarEditoraPorId(id)));
  }

  @Operation(summary = "Criar editora", method = "POST")
  @PostMapping
  public ResponseEntity<EditoraResponseDto> criarEditora(@RequestBody @Valid EditoraRequestDto dto) {
    Editora novaEditora = editoraService.criarEditora(dto);
    return ResponseEntity.status(HttpStatus.CREATED).body(EditoraResponseDto.from(novaEditora));
  }

  @Operation(summary = "Atualizar editora", method = "PUT")
  @PutMapping("{id}")
  public ResponseEntity<EditoraResponseDto> atualizarEditora(@PathVariable Long id, @RequestBody @Valid EditoraRequestDto dto) {
    return ResponseEntity.ok(EditoraResponseDto.from(editoraService.atualizarEditora(id, dto)));
  }

  @Operation(summary = "Deletar editora", method = "DELETE")
  @DeleteMapping("{id}")
  public ResponseEntity<Void> deletarEditora(@PathVariable Long id) {
    editoraService.deletarEditora(id);
    return ResponseEntity.noContent().build();
  } 
}
