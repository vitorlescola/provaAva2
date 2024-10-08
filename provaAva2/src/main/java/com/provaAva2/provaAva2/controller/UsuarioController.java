package com.provaAva2.provaAva2.controller;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import model.Usuario;
import repository.UsuarioRepository;

@Controller
@RequestMapping("/usuarios")
public class UsuarioController {
	@Autowired
	private UsuarioRepository usuarioRepository;
	
	@GetMapping("/listarUsuarios")
	public String listarUsuario(Model modelo) {
		modelo.addAttribute("usuarios", usuarioRepository.findAll());
		return "listarUsuarios";
	}
	
	@GetMapping("/editarUsuario/{id}")
	public String editarUsuario(@PathVariable("id") int id, Model modelo) {
		Optional<Usuario> usuarioOpt = usuarioRepository.findById(id);
		
		if(usuarioOpt.isPresent()) {
			modelo.addAttribute("usuario", usuarioOpt.get());
			return "cadastrarUsuario";
		}else {
			return "redirect:/usuarios/listarUsuarios";
		}
	}
	
	@GetMapping("/cadastrarUsuario")
	public String cadastrarUsuario(Model modelo) {
	    modelo.addAttribute("usuario", new Usuario());
	    return "cadastrarUsuario";
	}
	
	
	@PostMapping("/salvarUsuario")
	public String salvarUsuario(@ModelAttribute Usuario usuario) {
		usuarioRepository.save(usuario);
		return "redirect:/usuarios/listarUsuarios";
	}
	
	@GetMapping("/excluirUsuario/{id}")
	public String excluirUsuario(@PathVariable("id") int id) {
		usuarioRepository.deleteById(id);
		return "redirect:/usuarios/listarUsuarios";
	}
}