package org.tindertec.controller;

import org.tindertec.model.Usuario;
import org.tindertec.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
public class MeGustasController {

	@Autowired
	IUsuarioRepository usuRepo;
	@Autowired
	ILikesRepository likesRepo;
	@Autowired
	IDislikesRepository disLikesRepo;
	//private IUsuarioRepository repoUsua;
	//@GetMapping
	//@PostMapping
	
	@GetMapping("/MeGustas")
	public String cargarMegustas( Model model) {
		
	//enviarle el usuario que inicio sesion
		
		
		model.addAttribute("nombresYedad",BuscarAmistadController.nombresYedad);
		model.addAttribute("f1",BuscarAmistadController.foto1);
		model.addAttribute("listaUsuarios",usuRepo.USP_Listar_Usuarios_Likes(1));

		return "MeGustas/MeGustas";
		}
	@PostMapping("/MeGustas/Eliminar")
	public String ProcesarMegustas(@ModelAttribute Usuario usuario ,Model model) {
		System.out.println(usuario);
	//enviarle el usuario que inicio sesion
		
			disLikesRepo.USP_ELIMINAR_LIKE(1,usuario.getCod_usu());
			//model.addAttribute("mensajeSucess", "Eliminado");

	
		if(usuRepo.USP_Listar_Usuarios_Likes(1).isEmpty()) {
			
			model.addAttribute("msjEliminarLike","No hay likes +");
		}
		else {
			model.addAttribute("msjEliminarLike","hay usuarios");
			model.addAttribute("listaUsuarios",usuRepo.USP_Listar_Usuarios_Likes(1));
			}
		
		model.addAttribute("nombresYedad",BuscarAmistadController.nombresYedad);
		model.addAttribute("f1",BuscarAmistadController.foto1);
		

		return "MeGustas/MeGustas";
		}
	
}
