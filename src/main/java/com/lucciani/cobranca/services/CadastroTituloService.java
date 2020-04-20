package com.lucciani.cobranca.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import com.lucciani.cobranca.model.Titulo;
import com.lucciani.cobranca.repository.TitulosRepository;

@Service
public class CadastroTituloService {
	
	@Autowired
	private TitulosRepository tituloRepository;
	
	public void salvar(Titulo titulo) {
		try {
		tituloRepository.save(titulo);
		}catch(DataIntegrityViolationException e){
			throw new IllegalArgumentException("Formato de data inválido.");
		}
	}
	
	
	public void excluir(Long codigo) {
		tituloRepository.deleteById(codigo);
	}

}
