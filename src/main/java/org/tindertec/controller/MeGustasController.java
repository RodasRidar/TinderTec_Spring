package org.tindertec.controller;

import org.tindertec.model.Usuario;
import org.tindertec.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.tindertec.model.*;

import java.util.List;


///

import net.sf.jasperreports.engine.*;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import net.sf.jasperreports.engine.util.JRLoader;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.ResourceLoader;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.tindertec.model.Usuario;
import org.tindertec.repository.IUsuarioRepository;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.sql.DataSource;
import java.io.File;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.URL;
import java.sql.Connection;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
///
@Controller
public class MeGustasController {

	@Autowired
	IUsuarioRepository usuRepo;
	@Autowired
	ILikesRepository likesRepo;
	@Autowired
	IDislikesRepository disLikesRepo;
	@Autowired
	private DataSource dataSource;
	@Autowired
	private ResourceLoader resourceLoader;
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
		return "MeGustas/MeGustas";
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

		return "MeGustas/MeGustas";
	}
	
	@PostMapping("/MeGustas/Reporte")
	public void reporteMisLikes(HttpServletResponse response) {
		int CodUsuInSession=SeguridadController.CodUsuInSession;
		response.setHeader("Content-Disposition", "inline;");
		response.setContentType("application/pdf");

		try {
			String ru = resourceLoader.getResource("classpath:reportes/Blank_A4.jasper").getURI().getPath();
			HashMap parametros = new HashMap();
			parametros.put("CodUsuInSession", CodUsuInSession);
			JasperPrint jasperPrint = JasperFillManager.fillReport(ru, parametros, dataSource.getConnection());
			OutputStream outStream = response.getOutputStream();
			JasperExportManager.exportReportToPdfStream(jasperPrint, outStream);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}
}
