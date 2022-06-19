package org.tindertec.controller;
import org.tindertec.model.Usuario;
import org.tindertec.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
public class SubscripcionController {
	//@Autowired
	//@GetMapping
	//@PostMapping

	@GetMapping("/Suscripcion")
	public String cargarMegustas( Model model) {

		 String nombresYedad =SeguridadController.nombresYedad;
		 String foto1=SeguridadController.foto1;
		 int CodUsuInSession=SeguridadController.CodUsuInSession;	 
	//enviarle el usuario que inicio sesion
		
		
		model.addAttribute("nombresYedad",nombresYedad);
		model.addAttribute("f1",foto1);

		return "/Suscripcion/Suscripcion";
		}
}
