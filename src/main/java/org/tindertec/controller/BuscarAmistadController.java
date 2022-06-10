package org.tindertec.controller;
import org.tindertec.model.Chat;
import org.tindertec.model.Match;
import org.tindertec.model.Usuario;
import org.tindertec.repository.*;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.Optional;
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
	@Autowired
	private IMatchRepository repoMatch;
	@Autowired
	private IChatRepository repoChat;
	
	
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
		
	Chat chat = new Chat();
	
	List<Match> match;
	match=repoMatch.USP_LISTAR_MATCH_POR_USUARIO(1);
			
	//enviar Match 	
		model.addAttribute("lstMatch",match);
			
	//enviar chat vacio	
		model.addAttribute("lstMensajes",chat);
		model.addAttribute("msjNULLMensajes",0);
		
		model.addAttribute("MSJdeleteMatchAndMsj","");
		
	
		
		
	//enviarle el usuario que inicio sesion
		model.addAttribute("nombresYedad",nombresYedad);
		model.addAttribute("f1",foto1);
		return "Chat/Chat";
		}
	
	@PostMapping("/BuscarAmistad/Chat")
	public String SeleccionarChat(@RequestParam(name="id", required=false) int id , Model model)  {
		List<Match> match;
		match=repoMatch.USP_LISTAR_MATCH_POR_USUARIO(1);
		Optional<Usuario> usu2 ;
		usu2=repoUsua.findById(id);
		List<Chat> chat;
		
		chat=repoChat.USP_LISTAR_MATCH_POR_USUARIO(1, id);
		//enviar Match 	
		model.addAttribute("lstMatch",match);
		model.addAttribute("foto1",usu2.get().getFoto1());
		model.addAttribute("nombres",usu2.get().getNombres());
			
		//enviar chat 	
		//head
		String ChatconNombre="Chat con "+usu2.get().getNombres();
		model.addAttribute("Chatfoto",usu2.get().getFoto1());
		model.addAttribute("ChatconNombre",ChatconNombre);
		model.addAttribute("ChatUserid",id);
		//body
		model.addAttribute("lstMensajes",chat);
		model.addAttribute("cod_usu_now",1);
		model.addAttribute("cod_usu_1_msj",1);
		model.addAttribute("cod_usu_2_msj",id); 
		model.addAttribute("foto1_msj ",usu2.get().getFoto1()); 
		//footer
		model.addAttribute("cod_usu_enviarmsj",id);
		
		if (chat == null) {
			model.addAttribute("msjNULLMensajes",0);
		}
		else {
			model.addAttribute("msjNULLMensajes",1);
		}
		
		
		model.addAttribute("MSJdeleteMatchAndMsj","");	
		
		
	//enviarle el usuario que inicio sesion
		model.addAttribute("nombresYedad",nombresYedad);
		model.addAttribute("f1",foto1);
		return "Chat/Chat";
	}
	
	
	@PostMapping("/BuscarAmistad/EnviarMensaje")
	public String sendMensaje(@RequestParam(name="msj_enviar", required=false) 	String msj_enviar,@RequestParam(name="cod_usu_enviarmsj", required=false) int cod_usu_enviarmsj , Model model)  {
		
		//enviar msj
		repoChat.USP_REGISTRAR_CHAT(1, cod_usu_enviarmsj, msj_enviar);
		
		List<Match> match;
		match=repoMatch.USP_LISTAR_MATCH_POR_USUARIO(1);
		Optional<Usuario> usu2 ;
		usu2=repoUsua.findById(cod_usu_enviarmsj);
		List<Chat> chat;
		
		chat=repoChat.USP_LISTAR_MATCH_POR_USUARIO(1, cod_usu_enviarmsj);
		//enviar Match 	
		model.addAttribute("lstMatch",match);
		model.addAttribute("foto1",usu2.get().getFoto1());
		model.addAttribute("nombres",usu2.get().getNombres());
			
		//enviar chat 	
		//head
		String ChatconNombre="Chat con "+usu2.get().getNombres();
		model.addAttribute("Chatfoto",usu2.get().getFoto1());
		model.addAttribute("ChatconNombre",ChatconNombre);
		model.addAttribute("ChatUserid",cod_usu_enviarmsj);
		//body
		model.addAttribute("lstMensajes",chat);
		model.addAttribute("cod_usu_now",1);
		model.addAttribute("cod_usu_1_msj",1);
		model.addAttribute("cod_usu_2_msj",cod_usu_enviarmsj); 
		model.addAttribute("foto1_msj ",usu2.get().getFoto1()); 
		//footer
		model.addAttribute("cod_usu_enviarmsj",cod_usu_enviarmsj);
		
		if (chat == null) {
			model.addAttribute("msjNULLMensajes",0);
		}
		else {
			model.addAttribute("msjNULLMensajes",1);
		}
		
		
		model.addAttribute("MSJdeleteMatchAndMsj","");	
		
		
	//enviarle el usuario que inicio sesion
		model.addAttribute("nombresYedad",nombresYedad);
		model.addAttribute("f1",foto1);
		return "Chat/Chat";
	}
	
	
	@PostMapping("/BuscarAmistad/CancelarMatch")
	public String CancelarMatch( @RequestParam(name="cod_usu_menu", required=false) int cod_usu_menu, Model model) throws ParseException {
		
	//cancelar match
		
	repoMatch.USP_ELIMINAR_MATCH_POR_USUARIO(1, cod_usu_menu);
	Chat chat = new Chat();
	
	List<Match> match;
	match=repoMatch.USP_LISTAR_MATCH_POR_USUARIO(1);
			
	//enviar Match 	
		model.addAttribute("lstMatch",match);
			
	//enviar chat vacio	
		model.addAttribute("lstMensajes",chat);
		model.addAttribute("msjNULLMensajes",0);
		
		model.addAttribute("MSJdeleteMatchAndMsj","¡Se ha eliminado el match y los mensajes!");
		
	//enviarle el usuario que inicio sesion
		model.addAttribute("nombresYedad",nombresYedad);
		model.addAttribute("f1",foto1);
		return "Chat/Chat";
		}
	@PostMapping("/BuscarAmistad/VerPerfil")
	public String VerPerfil( @RequestParam(name="cod_usu_menu", required=false) int cod_usu_menu, Model model) throws ParseException {
		
	/////REDIRECCIONAR A PAGINA VERPERFIL
		
	
		
	//enviarle el usuario que inicio sesion
		model.addAttribute("nombresYedad",nombresYedad);
		model.addAttribute("f1",foto1);
		return "Chat/Chat";
		}
}
