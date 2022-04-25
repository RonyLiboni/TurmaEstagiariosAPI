package br.com.estagio.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import br.com.estagio.modelo.Estagiario;
import br.com.estagio.repository.EstagiarioRepository;

@Service
public class EstagiarioService {
	
	@Autowired
	private EstagiarioRepository estagiarioRepository;

	public Page<Estagiario> listar(Pageable paginacao) {
		return estagiarioRepository.findAll(paginacao);
	}

	public void salvar(Estagiario estagiario) {
		estagiarioRepository.save(estagiario);		
	}

	public Optional<Estagiario> procurarPeloId(Long id) {
		return estagiarioRepository.findById(id);
	}

	public void deletarPeloId(Long id) {
		estagiarioRepository.deleteById(id);
		
	}

	public Optional<Estagiario> procurarPeloEmail(String email) {
		return estagiarioRepository.findByEmail(email);
	}
	
	
	
	
	
}
