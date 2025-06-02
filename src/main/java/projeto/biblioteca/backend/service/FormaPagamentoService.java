package projeto.biblioteca.backend.service;

import java.util.List;

import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;
import projeto.biblioteca.backend.dto.FormaPagamentoRequestDto;
import projeto.biblioteca.backend.dto.FormaPagamentoResponseDto;
import projeto.biblioteca.backend.exceptions.RecursoNaoEncontradoException;
import projeto.biblioteca.backend.models.FormaPagamento;
import projeto.biblioteca.backend.repository.FormaPagamentoRepository;

@Service
@RequiredArgsConstructor
public class FormaPagamentoService {

  private final FormaPagamentoRepository formaPagamentoRepository;

  public List<FormaPagamentoResponseDto> listarFormasPagamento() {
    return formaPagamentoRepository.findAll()
            .stream()
            .map(FormaPagamentoResponseDto::from)
            .toList();
  }

  public FormaPagamento buscarFormaPagamentoPorId(Long id) {
    return formaPagamentoRepository.findById(id)
            .orElseThrow(() -> new RecursoNaoEncontradoException("Forma de pagamento não encontrada com ID: " +id));
  }

  public FormaPagamento criarFormaPagamento(FormaPagamentoRequestDto dto) {
    return formaPagamentoRepository.save(dto.toEntity());
  }

  public void deletarFormaPagamento(Long id) {

    if(!formaPagamentoRepository.existsById(id)) {
      throw new RecursoNaoEncontradoException("Forma de pagamento não encontrada com ID: " +id);  
    }

    formaPagamentoRepository.deleteById(id);
  }

  public FormaPagamento atualizarFormaPagamento(Long id, FormaPagamentoRequestDto dto) {
    return formaPagamentoRepository.findById(id)
            .map(formaPagamento -> {
              formaPagamento.setDescricao(dto.descricao());
              return formaPagamentoRepository.save(formaPagamento);
            })
            .orElseThrow(() -> new RecursoNaoEncontradoException("Forma de pagamento não encontrada com ID: " +id));
  }
  
}
