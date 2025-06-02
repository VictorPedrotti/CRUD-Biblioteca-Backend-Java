package projeto.biblioteca.backend.exceptions;

public class ValidacaoDeNegocioException extends RuntimeException {
  
  public ValidacaoDeNegocioException(String mensagem) {
    super(mensagem);  
  }
}
