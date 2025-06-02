package projeto.biblioteca.backend.service;

import java.util.List;

import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;
import projeto.biblioteca.backend.dto.ClienteRequestDto;
import projeto.biblioteca.backend.dto.ClienteResponseDto;
import projeto.biblioteca.backend.exceptions.ValidacaoDeNegocioException;
import projeto.biblioteca.backend.exceptions.RecursoNaoEncontradoException;
import projeto.biblioteca.backend.models.Cliente;
import projeto.biblioteca.backend.repository.AvaliacaoRepository;
import projeto.biblioteca.backend.repository.ClienteRepository;
import projeto.biblioteca.backend.repository.PedidoRepository;

@Service
@RequiredArgsConstructor
public class ClienteService {
  
  private final ClienteRepository clienteRepository;
  private final PedidoRepository pedidoRepository;
  private final AvaliacaoRepository avaliacaoRepository;

  public List<ClienteResponseDto> listarClientes() {
    return clienteRepository.findAll()
            .stream()
            .map(ClienteResponseDto::from)
            .toList();
  }
  
  public Cliente buscarClientePorId(Long id) {
    return clienteRepository.findById(id)
            .orElseThrow(() -> new RecursoNaoEncontradoException("Cliente não encontrado com ID: " +id));   
  }

  public Cliente criarCliente(ClienteRequestDto dto) {
    return clienteRepository.save(dto.toEntity());
  }

  public void deletarCliente(Long id) {

    if(!clienteRepository.existsById(id)) {
      throw new RecursoNaoEncontradoException("Cliente não encontrado com ID: " +id);
    }

    if(avaliacaoRepository.existsByClienteId(id) || pedidoRepository.existsByClienteId(id)) {
      throw new ValidacaoDeNegocioException("O cliente possui pedidos ou avaliações cadastrados e não pode ser excluído");
    }

    clienteRepository.deleteById(id);
  }

  public Cliente atualizarCliente(Long id, ClienteRequestDto dto) {
    return clienteRepository.findById(id)
            .map(cliente -> {
              cliente.setNome(dto.nome());
              cliente.setCpf(dto.cpf());
              cliente.setEmail(dto.email());
              cliente.setTelefone(dto.telefone());
              return clienteRepository.save(cliente);
            })
            .orElseThrow(() -> new RecursoNaoEncontradoException("Cliente não encontrado com ID: " +id));
  }
  
}
