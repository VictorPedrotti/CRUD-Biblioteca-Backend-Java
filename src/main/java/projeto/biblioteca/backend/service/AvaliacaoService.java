package projeto.biblioteca.backend.service;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import lombok.RequiredArgsConstructor;
import projeto.biblioteca.backend.dto.AvaliacaoRequestDto;
import projeto.biblioteca.backend.dto.AvaliacaoResponseDto;
import projeto.biblioteca.backend.models.Avaliacao;
import projeto.biblioteca.backend.repository.AvaliacaoRepository;

@Service
@RequiredArgsConstructor
public class AvaliacaoService {
  
  private final AvaliacaoRepository avaliacaoRepository;
  private final ClienteService clienteService;
  private final LivroService livroService;

  public List<AvaliacaoResponseDto> listarAvaliacoes() {
    return avaliacaoRepository.findAll()
            .stream()
            .map(AvaliacaoResponseDto::from)
            .toList();
  }

  public Avaliacao buscarAvaliacaoPorId(Long id) {
    return avaliacaoRepository.findById(id)
            .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Avaliação não encontrada com ID: " +id));  
  }

  public Avaliacao criarAvaliacao(AvaliacaoRequestDto dto) {
    Avaliacao avaliacao = dto.toEntity();

    avaliacao.setCliente(clienteService.buscarClientePorId(dto.clienteId()));
    avaliacao.setLivro(livroService.buscarLivroPorId(dto.livroId()));

    return avaliacaoRepository.save(avaliacao);
  }

  public void deletarAvaliacao(Long id) {

    if(!avaliacaoRepository.existsById(id)) {
      throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Avaliação não encontrada com ID: " + id);  
    }
    
    avaliacaoRepository.deleteById(id);
  }

  public Avaliacao atualizarAvaliacao(Long id, AvaliacaoRequestDto dto) {
    return avaliacaoRepository.findById(id)
            .map(avaliacao -> {
              avaliacao.setComentario(dto.comentario());
              avaliacao.setDataAvaliacao(dto.dataAvaliacao());
              avaliacao.setAvaliacao(dto.nota());
              avaliacao.setCliente(clienteService.buscarClientePorId(dto.clienteId()));
              avaliacao.setLivro(livroService.buscarLivroPorId(dto.livroId()));
              return avaliacaoRepository.save(avaliacao);
            })
            .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Avaliação não encontrada com ID: " + id));
  }
}
