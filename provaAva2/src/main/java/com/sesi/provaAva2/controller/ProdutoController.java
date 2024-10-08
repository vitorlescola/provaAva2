package com.sesi.provaAva2.controller;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.sesi.provaAva2.model.Produto;
import com.sesi.provaAva2.repository.ProdutoRepository;

@Controller
@RequestMapping("/produtos")
public class ProdutoController {
	
	@Autowired
	ProdutoRepository produtoRepository;
	
	@GetMapping("/formularioProduto")
	public String mostrarFormulario(Model modelo) {
		modelo.addAttribute("produto", new Produto());
		return "formularioProdutos";
	}
	
	@PostMapping("/salvarProduto")
	public String salvarProduto(@ModelAttribute Produto produto) {
		produtoRepository.save(produto);
		return "redirect:/produtos/listarProdutos";
	}
	
	@GetMapping("/listarProdutos")
	public String listarProduto(Model modelo) {
		modelo.addAttribute("produtos", produtoRepository.findAll());
		return "listarProdutos";
	}
	
	@GetMapping("/excluirProduto/{id}")
	public String excluirProduto(@PathVariable("id") int id) {
		produtoRepository.deleteById(id);
		return "redirect:/produtos/listarProdutos";
	}
	
	@GetMapping("editarProduto/{id}")
	public String editarProduto(@PathVariable("id") int id, Model modelo) {
		Optional<Produto> produtoOpt = produtoRepository.findById(id);
		
		if(produtoOpt.isPresent()) {
			modelo.addAttribute("produto", produtoOpt.get());
			return "formularioProdutos";
		}else {
			return "redirect:/produtos/listarProdutos";
		}
	}
}