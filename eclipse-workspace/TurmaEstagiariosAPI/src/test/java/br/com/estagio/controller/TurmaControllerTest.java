package br.com.estagio.controller;

import org.junit.jupiter.api.Test;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import br.com.estagio.config.ApplicationConfigTest;
 
@ActiveProfiles("testes")
class TurmaControllerTest extends ApplicationConfigTest {
	
	private final String uri="/turma";
	private final String uriId="/turma/{id}";
	
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
	public void deveRetornarCreated_QuandoForCadastrarUmaTurma() throws Exception {
		String json = "{\"tecnologia\":\"Teste\",\"nome\":\"Turma Teste\"}";
		
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
	public void deveRetornarBadRequest_QuandoForCadastrarUmaTurmaEUmOuMaisDadosEstiveremInvalidos() throws Exception {
		String json = "{\"tecnologia\":\"\",\"nome\":\"\"}";
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
	public void deveRetornarBadRequest_QuandoForAtualizarUmaTurmaEUmOuMaisDadosEstiveremInvalidos() throws Exception {
		String json = "{\"tecnologia\":\"\",\"nome\":\"\"}";
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
	public void deveRetornarOK_QuandoForAtualizarUmaTurma() throws Exception {
		String json = "{\"tecnologia\":\"Teste Atualizado\",\"nome\":\"Turma Teste\"}";
				
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
	public void deveRetornarNotFound_QuandoForAtualizarUmaTurmaComIdQueNaoExiste() throws Exception {
		String json = "{\"tecnologia\":\"Teste Atualizado\",\"nome\":\"Turma Teste\"}";
		
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
	
	
	

}
