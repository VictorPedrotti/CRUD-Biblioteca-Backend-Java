package projeto.biblioteca.backend.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import projeto.biblioteca.backend.models.Livro;

@Repository
public interface LivroRepository extends JpaRepository<Livro, Long>{
  boolean existsByAutorId(Long autorId);
  boolean existsByEditoraId(Long editoraId);
  boolean existsByFornecedorId(Long fornecedorId);
  boolean existsByGeneroId(Long generoId);
}
