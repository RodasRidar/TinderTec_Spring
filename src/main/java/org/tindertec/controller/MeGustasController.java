package org.tindertec.controller;

import org.tindertec.model.Usuario;
import org.tindertec.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
public class MeGustasController {

	//@Autowired
	//private IUsuarioRepository repoUsua;
	//@GetMapping
	//@PostMapping
	
	@GetMapping("/MeGustas")
	public String cargarMegustas( Model model) {
		
	//enviarle el usuario que inicio sesion
		
		
		model.addAttribute("nombresYedad",BuscarAmistadController.nombresYedad);
		model.addAttribute("f1",BuscarAmistadController.foto1);

		return "MeGustas/MeGustas";
		}
	
}
