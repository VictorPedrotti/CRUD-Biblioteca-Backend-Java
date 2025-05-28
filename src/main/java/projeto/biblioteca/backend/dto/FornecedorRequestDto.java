package projeto.biblioteca.backend.dto;

import org.hibernate.validator.constraints.br.CNPJ;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import projeto.biblioteca.backend.models.Fornecedor;

public record FornecedorRequestDto(
  @NotBlank(message = "Nome é obrigatório") @Size(min = 2, max = 100, message = "Nome deve ter entre 2 e 100 caracteres") String nome,
  @NotBlank(message = "Telefone é obrigatório") @Pattern(regexp = "^\\+?[0-9]{10,15}$", message = "Telefone inválido") String telefone,
  @NotBlank(message = "Email é obrigatório") @Email(message = "Email inválido") String email,
  @NotBlank(message = "CNPJ é obrigatório") @CNPJ(message = "CNPJ inválido") String cnpj
) {

  public Fornecedor toEntity() {
    Fornecedor fornecedor = new Fornecedor();
    fornecedor.setNome(nome);
    fornecedor.setEmail(email);
    fornecedor.setTelefone(telefone);
    fornecedor.setCnpj(cnpj);
    return fornecedor;
  }

}
