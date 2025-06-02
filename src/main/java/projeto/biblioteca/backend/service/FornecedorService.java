package projeto.biblioteca.backend.service;

import java.util.List;

import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;
import projeto.biblioteca.backend.dto.FornecedorRequestDto;
import projeto.biblioteca.backend.dto.FornecedorResponseDto;
import projeto.biblioteca.backend.exceptions.RecursoNaoEncontradoException;
import projeto.biblioteca.backend.exceptions.ValidacaoDeNegocioException;
import projeto.biblioteca.backend.models.Fornecedor;
import projeto.biblioteca.backend.repository.FornecedorRepository;
import projeto.biblioteca.backend.repository.LivroRepository;

@Service
@RequiredArgsConstructor
public class FornecedorService {
  
  private final FornecedorRepository fornecedorRepository;
  private final LivroRepository livroRepository;

  public List<FornecedorResponseDto> listarFornecedores() {
    return fornecedorRepository.findAll()
            .stream()
            .map(FornecedorResponseDto::from)
            .toList();
  }

  public Fornecedor buscarFornecedorPorId(Long id) {
    return fornecedorRepository.findById(id)
            .orElseThrow(() -> new RecursoNaoEncontradoException("Fornecedor não encontrado com ID: " + id));
  }

  public Fornecedor criarFornecedor(FornecedorRequestDto dto) {
    return fornecedorRepository.save(dto.toEntity());
  }

  public void deletarFornecedor(Long id) {

    if(!fornecedorRepository.existsById(id)) {
      throw new RecursoNaoEncontradoException("Fornecedor não encontrado com ID: " + id);  
    }

    if(livroRepository.existsByFornecedorId(id)) {
      throw new ValidacaoDeNegocioException("O fornecedor possui livros cadastrados e não pode ser excluído");
    }

    fornecedorRepository.deleteById(id);
  }

  public Fornecedor atualizarFornecedor(Long id, FornecedorRequestDto dto) {
    return fornecedorRepository.findById(id)
            .map(fornecedor -> {
              fornecedor.setNome(dto.nome());
              fornecedor.setTelefone(dto.telefone());
              fornecedor.setEmail(dto.email());
              fornecedor.setCnpj(dto.cnpj());
              return fornecedorRepository.save(fornecedor);
            })
            .orElseThrow(() -> new RecursoNaoEncontradoException("Fornecedor não encontrado com ID: " + id));
  }
}
