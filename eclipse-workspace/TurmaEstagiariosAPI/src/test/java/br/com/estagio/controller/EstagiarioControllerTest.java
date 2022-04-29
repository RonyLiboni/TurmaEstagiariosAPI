package br.com.estagio.controller;

import org.junit.jupiter.api.Test;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import br.com.estagio.config.ApplicationConfigTest;
import br.com.estagio.controller.form.EstagiarioForm;

@ActiveProfiles("testes")
class EstagiarioControllerTest extends ApplicationConfigTest {

	private final String uri="/estagiario";
	private final String uriId="/estagiario/{id}";
	
	@Test
	public void deveRetornarOK_QuandoPedirParaListar() throws Exception {	
		mockMvc
		.perform(MockMvcRequestBuilders
				.get(uri))
		.andExpect(MockMvcResultMatchers
				.status()
				.isOk());
	}
	
	@Test
	public void deveRetornarOK_QuandoBuscarPorIdQueExiste() throws Exception {	
		mockMvc
		.perform(MockMvcRequestBuilders
				.get(uriId, 1))
		.andExpect(MockMvcResultMatchers
				.status()
				.isOk());
	}
	
	@Test
	public void deveRetornarNotFound_QuandoBuscarPorIdQueNaoExiste() throws Exception {	
		mockMvc
		.perform(MockMvcRequestBuilders
				.get(uriId, -1))
		.andExpect(MockMvcResultMatchers
				.status()
				.isNotFound());
	}
		
	@Test
	public void deveRetornarCreated_QuandoForCadastrarUmEstagiario() throws Exception {
		String json = estagiarioFormComDadosCorretos();
		
		mockMvc
		.perform(MockMvcRequestBuilders
				.post(uri)
				.content(json)
				.contentType(MediaType.APPLICATION_JSON))
		.andExpect(MockMvcResultMatchers
				.status()
				.isCreated());
	}
	
	@Test
	public void deveRetornarBadRequest_QuandoForCadastrarUmEstagiarioEUmOuMaisDadosEstiveremInvalidos() throws Exception {
		String json = estagiarioFormComDadosNulos();
		mockMvc
		.perform(MockMvcRequestBuilders
				.post(uri)
				.content(json)
				.contentType(MediaType.APPLICATION_JSON))
		.andExpect(MockMvcResultMatchers
				.status()
				.isBadRequest());
	}
	
	@Test
	public void deveRetornarBadRequest_QuandoForAtualizarUmEstagiarioEUmOuMaisDadosEstiveremInvalidos() throws Exception {
		String json = estagiarioFormComDadosNulos();
		mockMvc
		.perform(MockMvcRequestBuilders
				.put(uriId, 1)
				.content(json)
				.contentType(MediaType.APPLICATION_JSON))
		.andExpect(MockMvcResultMatchers
				.status()
				.isBadRequest());
	}
		
	@Test
	public void deveRetornarOK_QuandoForAtualizarUmEstagiario() throws Exception {
		String json = estagiarioFormComDadosCorretos();
				
		mockMvc
		.perform(MockMvcRequestBuilders
				.put(uriId,1)
				.content(json)
				.contentType(MediaType.APPLICATION_JSON))
		.andExpect(MockMvcResultMatchers
				.status()
				.isOk());
	}
	
	@Test
	public void deveRetornarNotFound_QuandoForAtualizarUmEstagiarioComIdQueNaoExiste() throws Exception {
		String json = estagiarioFormComDadosCorretos();
		
		mockMvc
		.perform(MockMvcRequestBuilders
				.put(uriId,-1)
				.content(json)
				.contentType(MediaType.APPLICATION_JSON))
		.andExpect(MockMvcResultMatchers
				.status()
				.isNotFound());
	}
	
	@Test
	public void deveRetornarOK_QuandoTentarDeletarPorId() throws Exception {	
		mockMvc
		.perform(MockMvcRequestBuilders
				.delete(uriId, 4))
		.andExpect(MockMvcResultMatchers
				.status()
				.isOk());
	}
	
	@Test
	public void deveRetornarNotFound_QuandoTentarDeletarPorIdQueNaoExiste() throws Exception {	
		mockMvc
		.perform(MockMvcRequestBuilders
				.delete(uriId, -1))
		.andExpect(MockMvcResultMatchers
				.status()
				.isNotFound());
	}
	
	private String estagiarioFormComDadosCorretos() throws Exception {
		EstagiarioForm estagiarioForm = new EstagiarioForm();
		estagiarioForm.setNome("Naruto na Akatsuki");
		estagiarioForm.setCpf("089.378.990-94");
		estagiarioForm.setEmail("teste@teste.com");
		estagiarioForm.setTurmaId(1L);
		return objectMapper.writeValueAsString(estagiarioForm);
	}
	
	private String estagiarioFormComDadosNulos() throws Exception {
		return objectMapper.writeValueAsString(new EstagiarioForm());
	}
	

}
