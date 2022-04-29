package br.com.estagio.controller;

import org.junit.jupiter.api.Test;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import br.com.estagio.config.ApplicationConfigTest;
import br.com.estagio.controller.form.LoginForm;

public class AutenticacaoControllerTest extends ApplicationConfigTest {
	
	@Test
	public void deveRetornarBadRequest_QuandoDadosDeAutenticacaoEstiveremIncorretos() throws Exception {
		String json = usuarioFormComDadosIncorretos();
		mockMvc
		.perform(MockMvcRequestBuilders
				.post("/auth")
				.content(json)
				.contentType(MediaType.APPLICATION_JSON))
		.andExpect(MockMvcResultMatchers
				.status()
				.isBadRequest());
	}
	
	@Test
	public void deveRetornarBadRequest_QuandoDadosDeAutenticacaoEstiveremNulos() throws Exception {
		String json = usuarioFormComDadosNulos();
		mockMvc
		.perform(MockMvcRequestBuilders
				.post("/auth")
				.content(json)
				.contentType(MediaType.APPLICATION_JSON))
		.andExpect(MockMvcResultMatchers
				.status()
				.isBadRequest());
	}
	
	@Test
	public void deveRetornarOk_QuandoDadosDeAutenticacaoEstiveremCorretos() throws Exception {
		String json = usuarioFormComDadosCorretos();
		
		mockMvc
		.perform(MockMvcRequestBuilders
				.post("/auth")
				.content(json)
				.contentType(MediaType.APPLICATION_JSON))
		.andExpect(MockMvcResultMatchers
				.status()
				.isOk());
	}
	
	private String usuarioFormComDadosCorretos() throws Exception {
		LoginForm loginForm = new LoginForm();
		loginForm.setUsuario("admin@gmail.com");
		loginForm.setSenha("123456");
		return objectMapper.writeValueAsString(loginForm);
	}
	
	private String usuarioFormComDadosIncorretos() throws Exception {
		LoginForm loginForm = new LoginForm();
		loginForm.setUsuario("incorreto@gmail.com");
		loginForm.setSenha("123456");
		return objectMapper.writeValueAsString(loginForm);
	}
	
	private String usuarioFormComDadosNulos() throws Exception {
		return objectMapper.writeValueAsString(new LoginForm());
	}
	
}
