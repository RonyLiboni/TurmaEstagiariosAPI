package br.com.estagio.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.estagio.modelo.Turma;

@Repository
public interface TurmaRepository extends JpaRepository<Turma, Long> {

}
