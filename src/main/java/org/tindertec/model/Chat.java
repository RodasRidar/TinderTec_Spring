package org.tindertec.model;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.*;
import javax.persistence.Table;
import lombok.Data;

@Entity(name="tb_chat")
@Table(name="tb_chat")
@Data
public class Chat {
	@Id
	private int cod_chat;
	private int cod_usu1 ;
	@OneToOne
	@JoinColumn(name="cod_usu1", insertable=false, updatable=false)
	private Usuario usuario1; 
	
	private int cod_usu2 ;
	@OneToOne
	@JoinColumn(name="cod_usu2", insertable=false, updatable=false)
	private Usuario usuario2; 
	
	
	private String mensaje ;
	private String fecha ;
	

	
}
