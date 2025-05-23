package projeto.biblioteca.backend.dto;

import org.hibernate.validator.constraints.br.CPF;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import projeto.biblioteca.backend.models.Cliente;

public record ClienteRequestDto(
  
  @NotBlank(message = "Nome é obrigatório") @Size(min = 2, max = 100, message = "Nome deve ter entre 2 e 100 caracteres") String nome,

  @NotBlank(message = "CPF é obrigatório") @CPF(message = "CPF inválido") String cpf,

  @NotBlank(message = "Email é obrigatório") @Email(message = "Email inválido") String email,

  @NotBlank(message = "Telefone é obrigatório") @Pattern(regexp = "^\\+?[0-9]{10,15}$", message = "Telefone inválido") String telefone) {

  public Cliente toEntity() {
    Cliente cliente = new Cliente();
    cliente.setNome(nome);
    cliente.setCpf(cpf);
    cliente.setEmail(email);
    cliente.setTelefone(telefone);
    return cliente;
  }

}
