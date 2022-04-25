package br.com.estagio.controller.dto;

import org.springframework.data.domain.Page;

import br.com.estagio.modelo.Estagiario;
import br.com.estagio.modelo.Turma;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class EstagiarioDto {

	private Long id;
	private String nome;
	private String cpf;
	private String quatroLetras;
	private String email;
	private Turma categoria;


	public EstagiarioDto(Estagiario estagiario) {
		this.id = estagiario.getId();
		this.nome = estagiario.getNome();
		this.cpf = estagiario.getCpf();
		this.email = estagiario.getEmail();
		this.categoria = estagiario.getTurma();
	}
	
	public static Page<EstagiarioDto> converter(Page<Estagiario> estagiarios) {
		return estagiarios.map(EstagiarioDto::new);
	}

	
}
