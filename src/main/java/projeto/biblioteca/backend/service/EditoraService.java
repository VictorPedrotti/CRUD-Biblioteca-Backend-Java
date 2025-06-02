package projeto.biblioteca.backend.service;

import java.util.List;

import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;
import projeto.biblioteca.backend.dto.EditoraRequestDto;
import projeto.biblioteca.backend.dto.EditoraResponseDto;
import projeto.biblioteca.backend.exceptions.ValidacaoDeNegocioException;
import projeto.biblioteca.backend.exceptions.RecursoNaoEncontradoException;
import projeto.biblioteca.backend.models.Editora;
import projeto.biblioteca.backend.repository.EditoraRepository;
import projeto.biblioteca.backend.repository.LivroRepository;

@Service
@RequiredArgsConstructor
public class EditoraService {

  private final EditoraRepository editoraRepository;
  private final LivroRepository livroRepository;
  
  public List<EditoraResponseDto> listarEditoras() {
    return editoraRepository.findAll()
            .stream()
            .map(EditoraResponseDto::from)
            .toList();
  }

  public Editora buscarEditoraPorId(Long id) {
    return editoraRepository.findById(id)
            .orElseThrow(() -> new RecursoNaoEncontradoException("Editora não encontrada com ID: " + id));
  }

  public Editora criarEditora(EditoraRequestDto dto) {
    return editoraRepository.save(dto.toEntity());
  }

  public void deletarEditora(Long id) {

    if(!editoraRepository.existsById(id)) {
      throw new RecursoNaoEncontradoException("Editora não encontrada com ID: " + id); 
    }

    if(livroRepository.existsByEditoraId(id)) {
      throw new ValidacaoDeNegocioException("A editora possui livros cadastrados e não pode ser excluída");
    }

    editoraRepository.deleteById(id);
  }

  public Editora atualizarEditora(Long id, EditoraRequestDto dto) {
    return editoraRepository.findById(id)
            .map(editora -> {
              editora.setNome(dto.nome());
              editora.setDataFundacao(dto.dataFundacao());
              return editoraRepository.save(editora);
            })
            .orElseThrow(() -> new RecursoNaoEncontradoException("Editora não encontrada com ID: " + id));
  }
}
