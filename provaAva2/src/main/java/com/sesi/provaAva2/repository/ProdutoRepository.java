package com.sesi.provaAva2.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.sesi.provaAva2.model.Produto;

public interface ProdutoRepository extends JpaRepository<Produto, Integer>{
	
}