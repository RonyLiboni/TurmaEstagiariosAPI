package br.com.estagio.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.estagio.modelo.Estagiario;
@Repository
public interface EstagiarioRepository extends JpaRepository<Estagiario, Long> {
	Optional<Estagiario> findByEmail(String email);
}
