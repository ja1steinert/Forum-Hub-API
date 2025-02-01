package jaumsteinert.ForumHub_API.controller;

import jakarta.persistence.EntityNotFoundException;
import jaumsteinert.ForumHub_API.domain.topico.*;

import jakarta.validation.Valid;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.util.UriComponentsBuilder;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.data.web.PageableDefault;

import java.util.Optional;

@RestController
@RequestMapping("topicos")
public class ForumController {

    @Autowired
    private TopicoRepository repository;

    @GetMapping
    public ResponseEntity<Page<DadosListagemTopico>> listarTopicos(@PageableDefault(size = 10, sort = {"titulo"}) Pageable paginacao) {
        var page = repository.findAllByEstadoTrue(paginacao).map(DadosListagemTopico::new);
        return ResponseEntity.ok(page); // Status 200
    }

    @GetMapping("/{id}")
    public ResponseEntity listaPorId(@PathVariable Long id) {
        var topico = repository.getReferenceById(id);
        return ResponseEntity.ok(new DadosListagemTopico(topico)); // Status 200
    }

    @Transactional
    @PostMapping
    public ResponseEntity criarTopico(@RequestBody @Valid DadosNovoTopico dados, UriComponentsBuilder uriBuilder) {
        // Verifica se há um tópico de mesmo titulo e mensagem e previne a criacao de topicos repetidos
        Optional<Topico> topicoExistente = repository.findByTituloAndMensagem(dados.titulo(), dados.mensagem());
        if (topicoExistente.isPresent()) {
            return ResponseEntity.status(409).body(new DadosListagemTopico(topicoExistente.get())); // Status 409
        }

        var novoTopico = new Topico(dados);
        repository.save(novoTopico);

        // Constrói a URI do topico
        var uri = uriBuilder.path("/topicos/{id}").buildAndExpand(novoTopico.getId()).toUri();
        return ResponseEntity.created(uri).body(new DadosListagemTopico(novoTopico)); // Status 201
    }

    @Transactional
    @PutMapping("/{id}")
    public ResponseEntity<DadosListagemTopico> atualizarTopico(@PathVariable Long id, @RequestBody @Valid DadosAtualizacaoTopico dados) {
        // Verifica se o tópico existe
        Topico topico = repository.findById(id).orElseThrow(() -> new EntityNotFoundException("Tópico não encontrado")); // Status 404

        // Verifica se há outro tópico de ID diferente com mesmo conteúdo
        Optional<Topico> topicoExistente = repository.findByTituloAndMensagem(dados.titulo(), dados.mensagem());
        if (topicoExistente.isPresent() && !topicoExistente.get().getId().equals(id)) {
            throw new IllegalArgumentException("Já existe um tópico com o mesmo título e mensagem."); // Status 400
        }

        topico.setTitulo(dados.titulo());
        topico.setMensagem(dados.mensagem());

        return ResponseEntity.ok(new DadosListagemTopico(topico)); // Status 200
    }

    @Transactional
    @DeleteMapping("/{id}")
    public ResponseEntity deletarTopico(@PathVariable Long id) {
        // Verifica se o tópico existe
        Topico topico = repository.findById(id).orElseThrow(() -> new EntityNotFoundException("Tópico não encontrado"));

        repository.delete(topico);
        return ResponseEntity.noContent().build(); // Status 204 No Content
    }

}
