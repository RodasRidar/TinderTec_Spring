package org.tindertec.controller;

import org.tindertec.model.Usuario;
import org.tindertec.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.tindertec.model.*;

import java.util.List;

@Controller
public class MeGustasController {

	@Autowired
	IUsuarioRepository usuRepo;
	@Autowired
	ILikesRepository likesRepo;
	@Autowired
	IDislikesRepository disLikesRepo;
	// private IUsuarioRepository repoUsua;
	// @GetMapping
	// @PostMapping

	@GetMapping("/MeGustas")
	public String cargarMegustas(Model model) {

		// enviarle el usuario que inicio sesion
		String nombresYedad = SeguridadController.nombresYedad;
		String foto1 = SeguridadController.foto1;
		int CodUsuInSession = SeguridadController.CodUsuInSession;

		List<Usuario> lstusu = usuRepo.USP_Listar_Usuarios_Likes(CodUsuInSession);

		if (lstusu.isEmpty()) {
			model.addAttribute("msjEliminarLike", "!No has dado ningun ❤, ve busca amistades!");
		} else {
			
			model.addAttribute("listaUsuarios", lstusu);
		}
		model.addAttribute("nombresYedad", nombresYedad);
		model.addAttribute("f1", foto1);
		model.addAttribute("listaUsuarios", lstusu);
		return "/MeGustas/MeGustas";
	}

	@PostMapping("/MeGustas/Eliminar")
	public String ProcesarMegustas(@ModelAttribute Usuario usuario, Model model) {
		String nombresYedad = SeguridadController.nombresYedad;
		String foto1 = SeguridadController.foto1;
		int CodUsuInSession = SeguridadController.CodUsuInSession;
		System.out.println(usuario);
		// enviarle el usuario que inicio sesion

		disLikesRepo.USP_ELIMINAR_LIKE(CodUsuInSession, usuario.getCod_usu());
		// model.addAttribute("mensajeSucess", "Eliminado");

		if (usuRepo.USP_Listar_Usuarios_Likes(CodUsuInSession).isEmpty()) {

			model.addAttribute("msjEliminarLike", " 📌 !Ya no hay mas me gustas que ver!");
		} else {
			// model.addAttribute("msjEliminarLike","hay usuarios");
			model.addAttribute("listaUsuarios", usuRepo.USP_Listar_Usuarios_Likes(CodUsuInSession));
		}

		model.addAttribute("nombresYedad", nombresYedad);
		model.addAttribute("f1", foto1);

		return "/MeGustas/MeGustas";
	}

}
