package com.lucciani.cobranca.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.lucciani.cobranca.model.Titulo;

public interface TitulosRepository extends JpaRepository<Titulo, Long>{
	
	public List<Titulo> findByNomeContaining(String nome);

}
