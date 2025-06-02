package projeto.biblioteca.backend.service;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import lombok.RequiredArgsConstructor;
import projeto.biblioteca.backend.dto.GeneroRequestDto;
import projeto.biblioteca.backend.dto.GeneroResponseDto;
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
            .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Gênero não encontrado com ID:" +id));
  }

  public Genero criarGenero(GeneroRequestDto dto) {
    return generoRepository.save(dto.toEntity());
  }

  public void deletarGenero(Long id) {

    if (!generoRepository.existsById(id)) {
      throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Gênero não encontrado com ID:" +id);  
    }

    if(livroRepository.existsByGeneroId(id)) {
      throw new ResponseStatusException(HttpStatus.CONFLICT, "O gênero possui livros cadastrados e não pode ser excluído");
    }
    
    generoRepository.deleteById(id);
  }

  public Genero atualizarGenero(Long id, GeneroRequestDto dto) {
    return generoRepository.findById(id)
            .map(genero -> {
              genero.setDescricao(dto.descricao());
              return generoRepository.save(genero);
            })
            .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Gênero não encontrado com ID:" +id));
  }
  
}
