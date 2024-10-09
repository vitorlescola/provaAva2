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

import com.sesi.provaAva2.model.Pedido;
import com.sesi.provaAva2.repository.PedidoRepository;
import com.sesi.provaAva2.repository.ProdutoRepository;
import com.sesi.provaAva2.repository.UsuarioRepository;

@Controller
@RequestMapping("/pedidos")
public class PedidoController {
	
	@Autowired
	private PedidoRepository pedidoRepository;
	
	@Autowired
	private UsuarioRepository usuarioRepository;
	
	@Autowired
	ProdutoRepository produtoRepository;
	
	@GetMapping("/formularioPedido")
	public String mostrarFormulario(Model modelo) {
		modelo.addAttribute("pedido", new Pedido());
		modelo.addAttribute("usuarios", usuarioRepository.findAll());
		modelo.addAttribute("produtos", produtoRepository.findAll());
		return "formularioPedido";
	}
	
	@GetMapping("/listarPedidos")
	public String listarPedido(Model modelo) {
		modelo.addAttribute("pedidos", pedidoRepository.findAll());
		return "listarPedidos";
	}
	
	@PostMapping("/salvarPedido")
	public String salvarPedido(@ModelAttribute Pedido pedido) {
		pedidoRepository.save(pedido);
		return "redirect:/pedidos/listarPedidos";
	}
	
	@GetMapping("editarPedido/{id}")
	public String editarPedido(@PathVariable("id") int id, Model modelo) {
		Optional<Pedido> pedidoOpt = pedidoRepository.findById(id);
		
		modelo.addAttribute("usuarios",usuarioRepository.findAll());
		modelo.addAttribute("produtos",produtoRepository.findAll());
		
		if(pedidoOpt.isPresent()) {
			modelo.addAttribute("pedido", pedidoOpt.get());
			return "formularioPedido";
		}else {
			return "redirect:/pedidos/listarPedidos";
		}
	}
	
	@GetMapping("/excluirPedido/{id}")
	public String excluirPedido(@PathVariable("id") int id) {
		pedidoRepository.deleteById(id);
		return "redirect:/pedidos/listarPedido";
	}
}