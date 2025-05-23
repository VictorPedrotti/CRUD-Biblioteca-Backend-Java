package projeto.biblioteca.backend.service;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import lombok.RequiredArgsConstructor;
import projeto.biblioteca.backend.dto.ClienteRequestDto;
import projeto.biblioteca.backend.dto.ClienteResponseDto;
import projeto.biblioteca.backend.models.Cliente;
import projeto.biblioteca.backend.repository.ClienteRepository;

@Service
@RequiredArgsConstructor
public class ClienteService {
  
  private final ClienteRepository clienteRepository;

  public List<ClienteResponseDto> listarClientes() {
    return clienteRepository.findAll()
            .stream()
            .map(ClienteResponseDto::from)
            .toList();
  }
  
  public Cliente buscarClientePorId(Long id) {
    return clienteRepository.findById(id)
            .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Cliente não encontrado com ID: " +id));   
  }

  public Cliente criarCliente(ClienteRequestDto dto) {
    return clienteRepository.save(dto.toEntity());
  }

  public void deletarCliente(Long id) {

    if(!clienteRepository.existsById(id)) {
      throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Cliente não encontrado com ID: " +id);
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
            .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Autor não encontrado com ID: " + id));
  }
  
}
