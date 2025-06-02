package projeto.biblioteca.backend.service;

import java.util.List;

import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;
import projeto.biblioteca.backend.dto.AvaliacaoRequestDto;
import projeto.biblioteca.backend.dto.AvaliacaoResponseDto;
import projeto.biblioteca.backend.exceptions.RecursoNaoEncontradoException;
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
            .orElseThrow(() -> new RecursoNaoEncontradoException("Avaliação não encontrada com ID: " +id));  
  }

  public Avaliacao criarAvaliacao(AvaliacaoRequestDto dto) {
    Avaliacao avaliacao = dto.toEntity();

    avaliacao.setCliente(clienteService.buscarClientePorId(dto.clienteId()));
    avaliacao.setLivro(livroService.buscarLivroPorId(dto.livroId()));

    return avaliacaoRepository.save(avaliacao);
  }

  public void deletarAvaliacao(Long id) {

    if(!avaliacaoRepository.existsById(id)) {
      throw new RecursoNaoEncontradoException("Avaliação não encontrada com ID: " +id);  
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
            .orElseThrow(() -> new RecursoNaoEncontradoException("Avaliação não encontrada com ID: " +id));
  }
}
