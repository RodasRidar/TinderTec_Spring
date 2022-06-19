package org.tindertec.model;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import lombok.Data;

@Entity(name="tb_match")
@Table(name="tb_match")
@Data

public class Match {
	//
	
	@Id
	private int id_match ;
	//private int cod_usu2;
	//private String nombres;	
	//private String foto1;	
	
	

	private String det_match;
	private int cod_usu1 ;
	@ManyToOne
	@JoinColumn(name="cod_usu1", insertable=false, updatable=false)
	private Usuario usuario1; 
	
	private int cod_usu2 ;
	@ManyToOne
	@JoinColumn(name="cod_usu2", insertable=false, updatable=false)
	private Usuario usuario2;  
	

}
