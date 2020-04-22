package com.lucciani.cobranca.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import com.lucciani.cobranca.model.StatusTitulo;
import com.lucciani.cobranca.model.Titulo;
import com.lucciani.cobranca.repository.TitulosRepository;
import com.lucciani.cobranca.repository.filter.TituloFilter;

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


	public String receber(Long codigo) {
		Optional<Titulo> titulo = tituloRepository.findById(codigo);
		titulo.get().setStatus(StatusTitulo.RECEBIDO);
		tituloRepository.save(titulo.get());
		
		return StatusTitulo.RECEBIDO.getDescricao();
		
	}
	
	public List<Titulo> filtrar(TituloFilter filtro){
		String nome = filtro.getNome() == null ? " " : filtro.getNome();
		return tituloRepository.findByNomeContaining(nome);
	}

}
