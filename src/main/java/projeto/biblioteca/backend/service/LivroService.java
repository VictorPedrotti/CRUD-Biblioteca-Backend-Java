package projeto.biblioteca.backend.service;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import lombok.RequiredArgsConstructor;
import projeto.biblioteca.backend.dto.LivroRequestDto;
import projeto.biblioteca.backend.dto.LivroResponseDto;
import projeto.biblioteca.backend.models.Livro;
import projeto.biblioteca.backend.repository.LivroRepository;

@Service
@RequiredArgsConstructor
public class LivroService {
  
  private final LivroRepository livroRepository;
  private final AutorService autorService;
  private final GeneroService generoService;
  private final EditoraService editoraService;
  private final FornecedorService fornecedorService;

  public List<LivroResponseDto> listarLivros() {
    return livroRepository.findAll()
            .stream()
            .map(LivroResponseDto::from)
            .toList();
  }

  public Livro buscarLivroPorId(Long id) {
    return livroRepository.findById(id)
            .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Livro não encontrado com ID: " + id));
  }

  public Livro criarLivro(LivroRequestDto dto) {
    Livro livro = dto.toEntity();

    livro.setAutor(autorService.buscarAutorPorId(dto.autorId()));
    livro.setEditora(editoraService.buscarEditoraPorId(dto.editoraId()));
    livro.setGenero(generoService.buscarGeneroPorId(dto.generoId()));
    livro.setFornecedor(fornecedorService.buscarFornecedorPorId(dto.fornecedorId()));

    return livroRepository.save(livro);
  }

  public void deletarLivro(Long id) {

    if(!livroRepository.existsById(id)) {
      throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Livro não encontrado com ID: " + id);  
    }

    livroRepository.deleteById(id);
  }

  public Livro atualizarLivro(Long id, LivroRequestDto dto) {
    return livroRepository.findById(id)
            .map(livro -> {
              livro.setTitulo(dto.titulo());
              livro.setPreco(dto.preco());
              livro.setDataPublicacao(dto.dataPublicacao());
              livro.setNumeroPaginas(dto.numeroPaginas());
              livro.setAutor(autorService.buscarAutorPorId(dto.autorId()));
              livro.setEditora(editoraService.buscarEditoraPorId(dto.editoraId()));
              livro.setGenero(generoService.buscarGeneroPorId(dto.generoId()));
              livro.setFornecedor(fornecedorService.buscarFornecedorPorId(dto.fornecedorId()));
              return livroRepository.save(livro);
            })
            .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Livro não encontrado com ID: " + id));
  }
}
