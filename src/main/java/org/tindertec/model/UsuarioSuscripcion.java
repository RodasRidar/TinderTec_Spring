package org.tindertec.model;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;

import javax.persistence.OneToOne;
import javax.persistence.Table;
import lombok.Data;

@Entity(name="tb_usuario_suscripcion")
@Table(name="tb_usuario_suscripcion")
@Data
public class UsuarioSuscripcion {
	@Id
	private int cod_usu ;
	private int cod_suscrip ; 
	@OneToOne
	@JoinColumn(name="cod_suscrip", insertable=false, updatable=false)
	private Suscripcion suscripcion; 
	
	private String fecha_pago;
	private String fecha_renova ;

}
