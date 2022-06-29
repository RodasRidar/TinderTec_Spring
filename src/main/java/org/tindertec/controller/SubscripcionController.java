package org.tindertec.controller;

import org.tindertec.model.Boleta;
import org.tindertec.model.Usuario;
import org.tindertec.repository.*;

import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;

import java.io.OutputStream;
import java.util.HashMap;

import javax.servlet.http.HttpServletResponse;
import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ResourceLoader;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
@Controller
public class SubscripcionController {
	
	@Autowired
	private DataSource dataSource; 
	@Autowired
	private ResourceLoader resourceLoader; 
	@Autowired
	private ISuscripcionRepository repoSub;
	@Autowired
	private IBoletaRepository repoBol;


	@GetMapping("/Suscripcion")
	public String cargarMegustas( Model model) {

		 String nombresYedad =SeguridadController.nombresYedad;
		 String foto1=SeguridadController.foto1;
		 int CodUsuInSession=SeguridadController.CodUsuInSession;	 
		 
		 model.addAttribute("lstPlanes",repoSub.findAll());
	//enviarle el usuario que inicio sesion
		
		
		model.addAttribute("nombresYedad",nombresYedad);
		model.addAttribute("f1",foto1);

		return "Suscripcion/Suscripcion";
		}
	
	
	@PostMapping("/Suscripcion/Boleta1")
	public void reportes(HttpServletResponse response,Model model) {
		response.setHeader("Content-Disposition", "inline;"); 
		response.setContentType("application/pdf");
		int CodUsuInSession = SeguridadController.CodUsuInSession;
		
		try {

			repoBol.USP_Registrar_Boleta(CodUsuInSession, 2, 1);
			
			
			String ru = resourceLoader.getResource("classpath:Reportes/SuscripcionReporte.jasper").getURI().getPath();
			HashMap parametro = new HashMap<>();
			parametro.put("codigo", CodUsuInSession);
			JasperPrint jasperPrint = JasperFillManager.fillReport(ru, parametro, dataSource.getConnection());
			OutputStream outStream = response.getOutputStream();
			JasperExportManager.exportReportToPdfStream(jasperPrint, outStream);
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	
	@PostMapping("/Suscripcion/Boleta2")
	public void reportes2(HttpServletResponse response) {
		response.setHeader("Content-Disposition", "inline;"); 

		response.setContentType("application/pdf");
		int CodUsuInSession = SeguridadController.CodUsuInSession;
		
		try {
			repoBol.USP_Registrar_Boleta(CodUsuInSession, 3, 1);
			String ru = resourceLoader.getResource("classpath:Reportes/SuscripcionReporte.jasper").getURI().getPath();
			HashMap parametro = new HashMap<>();
			parametro.put("codigo", CodUsuInSession);
			JasperPrint jasperPrint = JasperFillManager.fillReport(ru, parametro, dataSource.getConnection());
			OutputStream outStream = response.getOutputStream();
			JasperExportManager.exportReportToPdfStream(jasperPrint, outStream);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
