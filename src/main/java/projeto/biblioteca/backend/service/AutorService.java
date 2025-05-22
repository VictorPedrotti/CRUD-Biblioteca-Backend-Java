package projeto.biblioteca.backend.service;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import lombok.RequiredArgsConstructor;
import projeto.biblioteca.backend.dto.AutorRequestDto;
import projeto.biblioteca.backend.dto.AutorResponseDto;
import projeto.biblioteca.backend.models.Autor;
import projeto.biblioteca.backend.repository.AutorRepository;

@Service
@RequiredArgsConstructor
public class AutorService {
  
  private final AutorRepository autorRepository;

  public List<AutorResponseDto> listarAutores() {
    return autorRepository.findAll()
            .stream()
            .map(AutorResponseDto::from)
            .toList();
  }

  public Autor buscarAutorPorId(Long id) {
    return autorRepository.findById(id)
            .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Autor não encontrado com ID: " + id));
  }

  public Autor criarAutor(AutorRequestDto dto) {
    return autorRepository.save(dto.toEntity());
  }

  public void deletarAutor(Long id) {

    if(!autorRepository.existsById(id)) {
      throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Autor não encontrado com ID: " + id);
    }

    autorRepository.deleteById(id);
  }

  public Autor atualizarAutor(Long id, AutorRequestDto dto) {
    return autorRepository.findById(id)
            .map(autor -> {
              autor.setNome(dto.nome());
              autor.setNacionalidade(dto.nacionalidade());
              autor.setNascimento(dto.nascimento());
              return autorRepository.save(autor);
            })
            .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Autor não encontrado com ID: " + id));
  }
}
