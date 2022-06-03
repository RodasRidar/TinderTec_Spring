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
public class BuscarAmistadController {
	public static String nombresYedad;
	public static String foto1;
	public static String edad;
	//@Autowired
	//@GetMapping
	//@PostMapping
	@Autowired
	private IUsuarioRepository repoUsua;
	
	
	@GetMapping("/")
	public String cargarBienvenida( Model model) throws ParseException {

	//enviarle el usuario que inicio sesion

		 SimpleDateFormat sdf = new SimpleDateFormat("YYYY-MM-DD", Locale.ENGLISH);
			String fecna= repoUsua.findById(1).get().getFecha_naci();
	        Date fechaNacimiento = sdf.parse(fecna);
	        Date secondDate =  sdf.parse("2022-01-01");

	        long diff = (secondDate.getTime()- fechaNacimiento.getTime())/365;

	        TimeUnit time = TimeUnit.DAYS; 
	        long diffrence = time.convert(diff, TimeUnit.MILLISECONDS);
	        edad= diffrence+"";
	        nombresYedad=repoUsua.findById(1).get().getNombres()+","+edad;
			foto1=repoUsua.findById(1).get().getFoto1();
		model.addAttribute("nombresYedad",nombresYedad);
		model.addAttribute("f1",foto1);

		return "BuscarAmistad/Bienvenida";
		}
	
	
	@GetMapping("/Inicio")
	public String cargarInicio( Model model) throws ParseException {

	//enviarle el usuario que inicio sesion

		model.addAttribute("nombresYedad",nombresYedad);
		model.addAttribute("f1",foto1);
		return "BuscarAmistad/BuscarAmistad";
		}
	
	@GetMapping("/Chat")
	public String cargarChat( Model model) throws ParseException {

	//enviarle el usuario que inicio sesion

		model.addAttribute("nombresYedad",nombresYedad);
		model.addAttribute("f1",foto1);
		return "Chat/Chat";
		}
}
