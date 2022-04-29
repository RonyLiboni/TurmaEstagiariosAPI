package br.com.estagio.config;

import org.flywaydb.core.Flyway;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.TestInstance.Lifecycle;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;

import com.fasterxml.jackson.databind.ObjectMapper;

@SpringBootTest
@AutoConfigureMockMvc
@TestInstance(Lifecycle.PER_CLASS)
public abstract class ApplicationConfigTest {
	
	@Autowired
	protected MockMvc mockMvc;
	@Autowired
	protected Flyway flyway;
	@Autowired
	protected ObjectMapper objectMapper;
	
	@BeforeAll
	public void reinicializarBancoDeDados(){
	   flyway.clean();
	   flyway.migrate();
	}
	
}