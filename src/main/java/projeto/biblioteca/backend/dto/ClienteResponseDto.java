package projeto.biblioteca.backend.dto;

import projeto.biblioteca.backend.models.Cliente;

public record ClienteResponseDto(Long id, String nome, String cpf, String email, String telefone) {

  public static ClienteResponseDto from (Cliente cliente) {
    return new ClienteResponseDto(
      cliente.getId(), 
      cliente.getNome(), 
      cliente.getCpf(), 
      cliente.getEmail(), 
      cliente.getTelefone());
  }
}
