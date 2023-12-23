package com.ficafrio.ficafrio.tarefa;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface tarefaRepository extends JpaRepository<tarefaEntity, Integer>{

}
