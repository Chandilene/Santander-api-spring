package me.dio.Santander_api_spring.repository;
import me.dio.Santander_api_spring.model.Tarefa;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TarefaRepository extends JpaRepository<Tarefa, Long> {
}
