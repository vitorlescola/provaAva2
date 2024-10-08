package com.provaAva2.provaAva2.controller;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import model.Produto;
import repository.ProdutoRepository;

@Controller
public class ProdutoController {
	
	@Autowired
	ProdutoRepository produtoRepository;
	
	@GetMapping("/listarProdutos")
	public String listarProdutos(Model modelo) {
		modelo.addAttribute("produto", produtoRepository.findAll());
		return "listarProdutos";
	}
	
	@GetMapping("editarProduto/{id}")
	public String editarProduto(@PathVariable("id") int id, Model modelo) {
		Optional<Produto> usuarioOpt = produtoRepository.findById(id);
		
		if(usuarioOpt.isPresent()) {
			modelo.addAttribute("produto", usuarioOpt.get());
			return "cadastrarProduto";
		}else {
			return "redirect:/listarProdutos";
		}
	}
	
	@GetMapping("/cadastrarProduto")
	public String cadastrarProduto(Model modelo) {
		modelo.addAttribute("usuario", new Produto());
		return "cadastrarProduto";
	}
	
	@PostMapping("/salvarUsuario")
	public String salvarUsuario(@ModelAttribute Produto produto) {
		produtoRepository.save(produto);
		return "redirect:/usuarios/listarUsuarios";
	}
	
	@GetMapping("/excluirUsuario/{id}")
	public String excluirUsuario(@PathVariable("id") int id) {
		produtoRepository.deleteById(id);
		return "redirect:/usuarios/listarUsuarios";
	}
}