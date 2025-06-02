package projeto.biblioteca.backend.exceptions;

public class RecursoNaoEncontradoException extends RuntimeException{

  public RecursoNaoEncontradoException(String mensagem) {
    super(mensagem);
  }
}
