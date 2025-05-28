package projeto.biblioteca.backend.dto;

import projeto.biblioteca.backend.models.Fornecedor;

public record FornecedorResponseDto(Long id, String nome, String telefone, String email, String cnpj) {

  public static FornecedorResponseDto from(Fornecedor fornecedor) {
    return new FornecedorResponseDto(
      fornecedor.getId(), 
      fornecedor.getNome(), 
      fornecedor.getTelefone(), 
      fornecedor.getEmail(), 
      fornecedor.getCnpj());
  }
}
