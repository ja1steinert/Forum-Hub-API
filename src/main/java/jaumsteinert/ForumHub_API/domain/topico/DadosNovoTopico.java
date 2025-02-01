package jaumsteinert.ForumHub_API.domain.topico;

import jakarta.validation.constraints.NotBlank;

public record DadosNovoTopico(
        @NotBlank String titulo,
        @NotBlank String mensagem,
        @NotBlank String autor,
        @NotBlank String curso
) {}
