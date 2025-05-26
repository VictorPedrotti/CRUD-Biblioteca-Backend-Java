package projeto.biblioteca.backend.service;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import lombok.RequiredArgsConstructor;
import projeto.biblioteca.backend.dto.FormaPagamentoRequestDto;
import projeto.biblioteca.backend.dto.FormaPagamentoResponseDto;
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
            .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Forma de pagamento não encontrada com ID: " +id));
  }

  public FormaPagamento criarFormaPagamento(FormaPagamentoRequestDto dto) {
    return formaPagamentoRepository.save(dto.toEntity());
  }

  public void deletarFormaPagamento(Long id) {

    if(!formaPagamentoRepository.existsById(id)) {
      throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Forma de pagamento não encontrada com ID: " +id);  
    }

    formaPagamentoRepository.deleteById(id);
  }

  public FormaPagamento atualizarFormaPagamento(Long id, FormaPagamentoRequestDto dto) {
    return formaPagamentoRepository.findById(id)
            .map(formaPagamento -> {
              formaPagamento.setDescricao(dto.descricao());
              return formaPagamentoRepository.save(formaPagamento);
            })
            .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Forma de pagamento não encontrada com ID: " +id));
  }
  
}
