package org.tindertec.model;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import lombok.Data;

@Entity(name="tb_usuario")
@Table(name="tb_usuario")
@Data
public class Usuario {
	@Id
	private int cod_usu  ;
	private String nombres ;
	private String email ;
	private String clave ;
	private String foto1 ;
	private String foto2 ;
	private String foto3 ;
	private String foto4 ;
	private String foto5 ;
	private String fecha_naci ;
	private String descripcion ;
	
	private int cod_carrera ;
	@ManyToOne
	@JoinColumn(name="cod_carrera", insertable=false, updatable=false)
	private Carreras carrera; 
	
	
	private int cod_sede ;
	@ManyToOne
	@JoinColumn(name="cod_sede", insertable=false, updatable=false)
	private Sedes sede; 
	
	
	private int cod_interes ;
	@ManyToOne
	@JoinColumn(name="cod_interes", insertable=false, updatable=false)
	private InteresGenero interesGenero; 
	
	
	private int cod_genero ;
	@ManyToOne
	@JoinColumn(name="cod_genero", insertable=false, updatable=false)
	private GeneroUsuario genero; 
	
	
	private int cod_suscrip ;
	@ManyToOne
	@JoinColumn(name="cod_suscrip", insertable=false, updatable=false)
	private Suscripcion suscripcion; 
	
	
}
