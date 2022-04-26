package br.com.estagio.controller.form;

import java.util.Optional;

import javax.validation.constraints.NotBlank;

import br.com.estagio.modelo.Turma;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@NoArgsConstructor
public class TurmaForm {
	
	@NotBlank(message="É obrigatório informar qual a tecnologia que o estagiário estudará. Ex: Java, C#, Php...")
	private String tecnologia;
	@NotBlank(message="É obrigatório informar a turma e ano dela. Ex: Turma 1 - 2022, Turma 2 - 2022")
	private String nome;

	public Turma converter() {
		Turma turma = new Turma(this.nome, this.tecnologia);
		return turma;
	}

	public Turma atualizar(Optional<Turma> turmaOptional) {
		Turma turma = turmaOptional.get();
		turma.setNome(this.nome);
		turma.setTecnologia(this.tecnologia);
		return turma;
	}

}
