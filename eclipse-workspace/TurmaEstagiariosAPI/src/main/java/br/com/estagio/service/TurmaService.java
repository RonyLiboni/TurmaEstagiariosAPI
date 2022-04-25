package br.com.estagio.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import br.com.estagio.modelo.Turma;
import br.com.estagio.repository.TurmaRepository;

@Service
public class TurmaService {

	@Autowired
	private TurmaRepository turmaRepository;

	public Page<Turma> listar(Pageable paginacao) {
		return turmaRepository.findAll(paginacao);
	}

	public void salvar(Turma turma) {
		turmaRepository.save(turma);
	}

	public Optional<Turma> procurarPeloId(Long id) {
		return turmaRepository.findById(id);
	}

	public void deletarPeloId(Long id) {
		turmaRepository.deleteById(id);
	}

}
