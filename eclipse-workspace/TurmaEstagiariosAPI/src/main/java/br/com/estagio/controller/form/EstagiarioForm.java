package br.com.estagio.controller.form;

import java.util.Optional;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import br.com.estagio.config.anotacao.CPF;
import br.com.estagio.modelo.Estagiario;
import br.com.estagio.modelo.Turma;
import br.com.estagio.service.TurmaService;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class EstagiarioForm {

	@NotBlank(message = "nome é obrigatório")
	private String nome;
	@CPF() @NotBlank(message = "CPF é obrigatório")
	private String cpf;
	@NotNull @Email (message="Deve colocar um e-mail. Exemplo: rony@gmail.com")
	private String email;
	@NotNull (message="Informe o id da turma que o estagiario pertence")
	private Long turmaId;

	public Estagiario converter(TurmaService turmaService) {
		Turma turma = turmaService.procurarPeloId(this.turmaId).get();
		String cpfPadrao = padronizarCpf(this.cpf);
		Estagiario estagiario = new Estagiario(this.nome, cpfPadrao, this.email, turma);
		return estagiario;
	}

	public Estagiario atualizar(Optional<Estagiario> estagiarioOptional, TurmaService turmaService) {
		Turma turma = turmaService.procurarPeloId(this.turmaId).get();
		Estagiario estagiario = estagiarioOptional.get();
		estagiario.setNome(this.nome);
		estagiario.setTurma(turma);
		String cpfPadrao = padronizarCpf(this.cpf);
		estagiario.setCpf(cpfPadrao);
		estagiario.setEmail(this.email);
		return estagiario;
	}
	
	private String padronizarCpf(String cpf) {
		cpf = cpf.replace(".", "");
		cpf = cpf.replace("-","");
		return cpf;
	}	
	
}
