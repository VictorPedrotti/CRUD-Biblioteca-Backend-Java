package projeto.biblioteca.backend.service;

import java.util.List;

import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;
import projeto.biblioteca.backend.dto.GeneroRequestDto;
import projeto.biblioteca.backend.dto.GeneroResponseDto;
import projeto.biblioteca.backend.exceptions.RecursoNaoEncontradoException;
import projeto.biblioteca.backend.exceptions.ValidacaoDeNegocioException;
import projeto.biblioteca.backend.models.Genero;
import projeto.biblioteca.backend.repository.GeneroRepository;
import projeto.biblioteca.backend.repository.LivroRepository;

@Service
@RequiredArgsConstructor
public class GeneroService {

  private final GeneroRepository generoRepository;
  private final LivroRepository livroRepository;

  public List<GeneroResponseDto> listarGeneros() {
    return generoRepository.findAll()
            .stream()
            .map(GeneroResponseDto::from)
            .toList();  
  }

  public Genero buscarGeneroPorId(Long id) {
    return generoRepository.findById(id)
            .orElseThrow(() -> new RecursoNaoEncontradoException("Gênero não encontrado com ID:" +id));
  }

  public Genero criarGenero(GeneroRequestDto dto) {
    return generoRepository.save(dto.toEntity());
  }

  public void deletarGenero(Long id) {

    if (!generoRepository.existsById(id)) {
      throw new RecursoNaoEncontradoException("Gênero não encontrado com ID:" +id);  
    }

    if(livroRepository.existsByGeneroId(id)) {
      throw new ValidacaoDeNegocioException("O gênero possui livros cadastrados e não pode ser excluído");
    }
    
    generoRepository.deleteById(id);
  }

  public Genero atualizarGenero(Long id, GeneroRequestDto dto) {
    return generoRepository.findById(id)
            .map(genero -> {
              genero.setDescricao(dto.descricao());
              return generoRepository.save(genero);
            })
            .orElseThrow(() -> new RecursoNaoEncontradoException("Gênero não encontrado com ID:" +id));
  }
  
}
