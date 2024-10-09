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

import com.sesi.provaAva2.model.Usuario;
import com.sesi.provaAva2.repository.UsuarioRepository;

@Controller
@RequestMapping("/usuarios")
public class UsuarioController {
	
	@Autowired
	private UsuarioRepository usuarioRepository;
	
	@GetMapping("/listarUsuario")
	public String listarUsuario(Model modelo) {
		modelo.addAttribute("usuarios", usuarioRepository.findAll());
		return "listarUsuario";
	}
	
	@GetMapping("editarUsuario/{id}")
	public String editarUsuario(@PathVariable("id") int id, Model modelo) {
		Optional<Usuario> usuarioOpt = usuarioRepository.findById(id);
		
		if(usuarioOpt.isPresent()) {
			modelo.addAttribute("usuario", usuarioOpt.get());
			return "formularioUsuario";
		}else {
			return "redirect:/usuarios/listarUsuario";
		}
	}
	
	@GetMapping("/formularioUsuario")
	public String mostrarFormulario(Model modelo) {
		modelo.addAttribute("usuario", new Usuario());
		return "formularioUsuario";
	}
	
	@PostMapping("/salvarUsuario")
	public String salvarUsuario(@ModelAttribute Usuario usuario) {
		usuarioRepository.save(usuario);
		return "redirect:/usuarios/listarUsuario";
	}
	
	@GetMapping("/excluirUsuario/{id}")
	public String excluirUsuario(@PathVariable("id") int id) {
		usuarioRepository.deleteById(id);
		return "redirect:/usuarios/listarUsuario";
	}
}