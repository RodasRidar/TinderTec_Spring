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


	@Autowired
	private IUsuarioRepository repoUsua;
	@Autowired
	private ILikesRepository repoLike;
	@Autowired
	private IDislikesRepository repoDislike;
	
	
	@GetMapping("/")
	public String cargarBienvenida( Model model) throws ParseException {

	//enviarle el usuario que inicio sesion

	        edad=obtenerEdad(repoUsua.findById(1).get().getFecha_naci());
	        nombresYedad=repoUsua.findById(1).get().getNombres()+","+edad;
			foto1=repoUsua.findById(1).get().getFoto1();
		model.addAttribute("nombresYedad",nombresYedad);
		model.addAttribute("f1",foto1);

		return "BuscarAmistad/Bienvenida";
		}
	
	
	@GetMapping("/Inicio")
	public String cargarInicio( Model model) throws ParseException {
		
	//enviarle el usuario que inicio sesion
		int codigoValidacion=-1;
		Usuario u=repoUsua.listaBuscarAmistad(1);
		model.addAttribute("nombresYedad",nombresYedad);
		model.addAttribute("f1",foto1);
		if (u == null) {
			
			model.addAttribute("codigoValidacion",codigoValidacion);
		}
		else {
			codigoValidacion=1;
			model.addAttribute("codigoValidacion",codigoValidacion);
			model.addAttribute("nombreYedadBusAmi",u.getNombres()+" ,"+obtenerEdad(u.getFecha_naci()));
			model.addAttribute("fotoBusAmi",u.getFoto1());
			model.addAttribute("codigoBusAmi",u.getCod_usu());
			model.addAttribute("sedeBusAmi",u.getSede().getDes_sede());
			model.addAttribute("carreraBusAmi",u.getCarrera().getDes_carrera());
		}

		
		return "BuscarAmistad/BuscarAmistad";
		}
	
	@PostMapping("BuscarAmistad/Like")
	public String like(@ModelAttribute Usuario usu, Model model) throws ParseException {
// hacer con @RequestParam , recuperar el parametro enviado por el formulario post
		int codigoValidacion=-1;
		String mensaje=repoLike.USP_INSERTAR_LIKE(1,usu.getCod_usu());
		if(mensaje == "MATCH") {
			model.addAttribute("mensajeBuscarAmistad",mensaje);
		}
		//CARD
		Usuario u=repoUsua.listaBuscarAmistad(1);
		if (u == null) {
			
			model.addAttribute("codigoValidacion",codigoValidacion);
		}
		else {
			codigoValidacion=1;
			model.addAttribute("codigoValidacion",codigoValidacion);
			model.addAttribute("nombreYedadBusAmi",u.getNombres()+" ,"+obtenerEdad(u.getFecha_naci()));
			model.addAttribute("fotoBusAmi",u.getFoto1());
			model.addAttribute("codigoBusAmi",u.getCod_usu());
			model.addAttribute("sedeBusAmi",u.getSede().getDes_sede());
			model.addAttribute("carreraBusAmi",u.getCarrera().getDes_carrera());
		}

	//enviarle el usuario que inicio sesion

		model.addAttribute("nombresYedad",nombresYedad);
		model.addAttribute("f1",foto1);
		return "BuscarAmistad/BuscarAmistad";
		}
	
	@PostMapping("/BuscarAmistad/disLike")
	public String dislike(@ModelAttribute Usuario usu, Model model) throws ParseException {
// hacer con @RequestParam , recuperar el parametro enviado por el formulario post
		int codigoValidacion=-1;
		repoDislike.USP_INSERTAR_DISLIKE(1,usu.getCod_usu());

		//CARD
		Usuario u=repoUsua.listaBuscarAmistad(1);
		if (u == null) {
			
			model.addAttribute("codigoValidacion",codigoValidacion);
		}
		else {
			codigoValidacion=1;
			model.addAttribute("codigoValidacion",codigoValidacion);
			model.addAttribute("nombreYedadBusAmi",u.getNombres()+" ,"+obtenerEdad(u.getFecha_naci()));
			model.addAttribute("fotoBusAmi",u.getFoto1());
			model.addAttribute("codigoBusAmi",u.getCod_usu());
			model.addAttribute("sedeBusAmi",u.getSede().getDes_sede());
			model.addAttribute("carreraBusAmi",u.getCarrera().getDes_carrera());
		}

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
	
	public String obtenerEdad(String fecna) throws ParseException {
		
		 SimpleDateFormat sdf = new SimpleDateFormat("YYYY-MM-DD", Locale.ENGLISH);
			//fecna= repoUsua.findById(1).get().getFecha_naci();
	        Date fechaNacimiento = sdf.parse(fecna);
	        Date secondDate =  sdf.parse("2022-01-01");

	        long diff = (secondDate.getTime()- fechaNacimiento.getTime())/365;

	        TimeUnit time = TimeUnit.DAYS; 
	        long diffrence = time.convert(diff, TimeUnit.MILLISECONDS);
	        edad= diffrence+"";
	        
		return edad;
	}
}
