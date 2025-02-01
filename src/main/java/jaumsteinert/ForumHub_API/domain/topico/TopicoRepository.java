package jaumsteinert.ForumHub_API.domain.topico;

import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TopicoRepository extends JpaRepository<Topico, Long> {
    // Verifica se há um tópico repetido (titulo e mensagem iguais)
    Optional<Topico> findByTituloAndMensagem(String titulo, String mensagem);

    // Retorna os tópicos ativos (estado "true")
    Page<Topico> findAllByEstadoTrue(Pageable paginacao);
}
