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

import br.com.estagio.controller.dto.EstagiarioDto;
import br.com.estagio.controller.form.EstagiarioForm;
import br.com.estagio.modelo.Estagiario;
import br.com.estagio.service.EstagiarioService;
import br.com.estagio.service.TurmaService;

@RestController
@RequestMapping("/estagiario")
public class EstagiarioController {

	@Autowired
	private EstagiarioService estagiarioService;
	@Autowired
	private TurmaService turmaService;

	@GetMapping
	public ResponseEntity<Page<EstagiarioDto>> listar(@PageableDefault(sort="nome") Pageable paginacao) {
		Page<Estagiario> estagiarios = estagiarioService.listar(paginacao);
		return ResponseEntity.ok(EstagiarioDto.converter(estagiarios));
	}

	@PostMapping
	public ResponseEntity<EstagiarioDto> cadastrar(@RequestBody @Valid EstagiarioForm form,
			UriComponentsBuilder uriBuilder) {
		Estagiario estagiario = form.converter(turmaService);		
		
		estagiarioService.salvar(estagiario);
		URI uri = uriBuilder.path("/topicos/{id}").buildAndExpand(estagiario.getId()).toUri();
		return ResponseEntity.created(uri).body(new EstagiarioDto(estagiario));
	}

	@GetMapping("/{id}")
	public ResponseEntity<EstagiarioDto> procurar(@PathVariable Long id) {
		Optional<Estagiario> estagiarioOptional = estagiarioService.procurarPeloId(id);
		if (estagiarioOptional.isPresent())
			return ResponseEntity.ok(new EstagiarioDto(estagiarioOptional.get()));

		return ResponseEntity.notFound().build();
	}
	
	@PutMapping("/{id}")
	public ResponseEntity<EstagiarioDto> atualizar(@PathVariable Long id, @RequestBody @Valid EstagiarioForm form){
		Optional<Estagiario> estagiarioOptional = estagiarioService.procurarPeloId(id);
		if (estagiarioOptional.isPresent()) {
			Estagiario estagiario = form.atualizar(estagiarioOptional, turmaService);
			estagiarioService.salvar(estagiario);
			return ResponseEntity.ok(new EstagiarioDto(estagiario));
		}
		
		return ResponseEntity.notFound().build();
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<?> deletar(@PathVariable Long id){
		Optional<Estagiario> estagiarioOptional = estagiarioService.procurarPeloId(id);
		if (estagiarioOptional.isPresent()) {
			estagiarioService.deletarPeloId(id);
			return ResponseEntity.ok().build();
		}
		return ResponseEntity.notFound().build();
	}
		
		
}



