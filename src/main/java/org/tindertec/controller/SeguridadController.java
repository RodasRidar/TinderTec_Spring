package org.tindertec.controller;
import org.tindertec.model.Usuario;
import org.tindertec.repository.*;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import java.util.concurrent.TimeUnit;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
public class SeguridadController {
	public static String nombresYedad;
	public static String foto1;
	public static String edad;
	public static int CodUsuInSession;
	
	@Autowired	
	private IUsuarioRepository repoUsua;
	
	
	@GetMapping("/Login")
	public String login(Model model) {
		model.addAttribute("usuario",new Usuario());
		return "/Login/login";
	}
	
	@PostMapping("/Login/Bienvenida")
	public String validarUsuario(@ModelAttribute Usuario usuario, Model model) throws ParseException {
	System.out.println(usuario);
	
	Usuario u =repoUsua.findByEmailAndClave(usuario.getEmail(), usuario.getClave());
	
	if (u == null) {
		//model.addAttribute("usuario", new Usuario());
		model.addAttribute("mensajeError","Usuario Clave Incorrecto");
		return "/Login/login";
		} 
	else{
		model.addAttribute("usuario",u);
		
			CodUsuInSession=u.getCod_usu();
			edad=obtenerEdad(repoUsua.findById(u.getCod_usu()).get().getFecha_naci());
		    nombresYedad=repoUsua.findById(u.getCod_usu()).get().getNombres()+","+edad;
			foto1=repoUsua.findById(u.getCod_usu()).get().getFoto1();
			
			model.addAttribute("nombresYedad",nombresYedad);
			model.addAttribute("f1",foto1);

		
		
		return "BuscarAmistad/Bienvenida";
		}
	}
	@GetMapping("/LogOut")
	public String logOut( Model model) {
		model.addAttribute("usuario",new Usuario());
		/*
		nombresYedad="";
		foto1="";
		edad="";
		CodUsuInSession=0;*/
		
		return "Login/login";
		}
	
	public String obtenerEdad(String fecna) throws ParseException {
		
		 SimpleDateFormat sdf = new SimpleDateFormat("YYYY-MM-DD", Locale.ENGLISH);
			//fecna= repoUsua.findById(1).get().getFecha_naci();
	        Date fechaNacimiento = sdf.parse(fecna);
	        Date secondDate =  sdf.parse("2022-01-01");

	        long diff = (secondDate.getTime()- fechaNacimiento.getTime())/365;

	        TimeUnit time = TimeUnit.DAYS; 
	        long diffrence = time.convert(diff, TimeUnit.MILLISECONDS);
	        String age;
	        age= diffrence+"";
	        
		return age;
	}
}
