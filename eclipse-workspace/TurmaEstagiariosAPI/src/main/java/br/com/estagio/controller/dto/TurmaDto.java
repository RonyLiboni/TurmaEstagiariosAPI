package br.com.estagio.controller.dto;

import org.springframework.data.domain.Page;

import br.com.estagio.modelo.Turma;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class TurmaDto {

	private Long id;
	private String tecnologia;
	private String nome;
		
	public TurmaDto(Turma turma) {
		this.id = turma.getId();
		this.tecnologia = turma.getTecnologia();
		this.nome = turma.getNome();
	}
	
	public static Page<TurmaDto> converter(Page<Turma> turmas) {
		return turmas.map(TurmaDto::new);
	}
}
