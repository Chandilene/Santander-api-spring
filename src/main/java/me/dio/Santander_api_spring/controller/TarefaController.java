package me.dio.Santander_api_spring.controller;

import me.dio.Santander_api_spring.model.Tarefa;
import me.dio.Santander_api_spring.repository.TarefaRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController


public class TarefaController {
    private final TarefaRepository repository;

    public TarefaController(TarefaRepository repository) {
        this.repository = repository;
    }

    @GetMapping
    public List<Tarefa> listar() {
        return repository.findAll();
    }

    @PostMapping
    public Tarefa criar(@RequestBody Tarefa tarefa) {
        return repository.save(tarefa);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Tarefa> atualizar(@PathVariable Long id, @RequestBody Tarefa tarefa) {
        return repository.findById(id)
                .map(existingTarefa -> {
                    existingTarefa.setDescricao(tarefa.getDescricao());
                    existingTarefa.setCompleta(tarefa.isCompleta());
                    repository.save(existingTarefa);
                    return ResponseEntity.ok(existingTarefa);
                })
                .orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Object> deletar(@PathVariable Long id) {
        return repository.findById(id)
                .map(tarefa -> {
                    repository.delete(tarefa);
                    return ResponseEntity.noContent().build();
                })
                .orElse(ResponseEntity.notFound().build());
    }
}
