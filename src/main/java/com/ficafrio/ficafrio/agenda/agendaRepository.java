package com.ficafrio.ficafrio.agenda;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface agendaRepository extends JpaRepository<agendaEntity, Integer> {

}
