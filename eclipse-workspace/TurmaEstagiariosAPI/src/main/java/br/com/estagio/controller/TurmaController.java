package br.com.estagio.controller;

import java.net.URI;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import br.com.estagio.modelo.Turma;
import br.com.estagio.service.TurmaService;

@RestController
@RequestMapping("/turmas")
public class TurmaController {

	@Autowired
	private TurmaService turmaService;

	@GetMapping
	public ResponseEntity<Page<TurmaDto>> listar(@PageableDefault() Pageable paginacao) {
		Page<Turma> turmas = turmaService.listar(paginacao);
		return ResponseEntity.ok(TurmaDto.converter(turmas));
	}

	@PostMapping
	public ResponseEntity<TurmaDTO> cadastrar(@RequestBody @Valid TurmaForm form,
			UriComponentsBuilder uriBuilder) {
		Turma turma = form.converter();
		turmaService.salvar(turma);
		URI uri = uriBuilder.path("/topicos/{id}").buildAndExpand(turma.getId()).toUri();
		return ResponseEntity.created(uri).body(new TurmaDto(turma));
	}

	@GetMapping("/{id}")
	public ResponseEntity<TurmaDto> procurar(@PathVariable Long id) {
		Optional<Turma> turmaOptional = turmaService.procurarPeloId(id);
		if (turmaOptional.isPresent())
			return ResponseEntity.ok(new TurmaDto(turmaOptional.get()));

		return ResponseEntity.notFound().build();
	}
	
	@PutMapping("/{id}")
	public ResponseEntity<TurmaDto> atualizar(@PathVariable Long id, @RequestBody @Valid TurmaForm form){
		Optional<Turma> turmaOptional = turmaService.procurarPeloId(id);
		if (turmaOptional.isPresent()) {
			Turma turma = form.atualizar(turmaOptional);
			turmaService.salvar(turma);
			return ResponseEntity.ok(new TurmaDto(turma));
		}
		
		return ResponseEntity.notFound().build();
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<?> deletar(@PathVariable Long id){
		Optional<Turma> turmaOptional = turmaService.procurarPeloId(id);
		if (turmaOptional.isPresent()) {
			turmaService.deletarPeloId(id);
			return ResponseEntity.ok().build();
		}
		return ResponseEntity.notFound().build();
	}
}



