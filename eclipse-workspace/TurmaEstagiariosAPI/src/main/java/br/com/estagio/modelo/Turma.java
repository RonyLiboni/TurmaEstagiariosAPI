package br.com.estagio.modelo;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@Entity
@NoArgsConstructor
public class Turma {
	
	@Id @GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private String nome;
	private String tecnologia;
	
	public Turma(String nome, String tecnologia) {
		this.nome = nome;
		this.tecnologia = tecnologia;
	}
		
	
	
	
	
}
