package br.com.estagio.repository;

import java.util.Optional;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;

import br.com.estagio.modelo.Estagiario;
import br.com.estagio.modelo.Turma;


@DataJpaTest
@AutoConfigureTestDatabase(replace=AutoConfigureTestDatabase.Replace.NONE)
class EstagiarioRepositoryTest {
	
	@Autowired
	private EstagiarioRepository estagiarioRepository;
	@Autowired
	private TestEntityManager em;
	
	@Test
	void deveAcharUmEstagiarioAtravesDeUmEmailExistente() {
		Turma turma = new Turma("Teste","teste");
		em.persist(turma);
		Estagiario estagiario = new Estagiario("Teste","teste.teste@teste.com","123456", turma);
		em.persist(estagiario);
		
		Optional<Estagiario> estagiarioOptional = estagiarioRepository.findByEmail("teste.teste@teste.com");
		Assertions.assertEquals("teste.teste@teste.com", estagiarioOptional.get().getEmail());
	}
	
	@Test
	void deveAcharUmOptionalDeEstagiarioVazioAtravesDeUmEmailQueNaoExiste() {
		Optional<Estagiario> estagiarioOptional = estagiarioRepository.findByEmail("teste.teste@teste.com");
		Assertions.assertTrue(estagiarioOptional.isEmpty());
	}

}
