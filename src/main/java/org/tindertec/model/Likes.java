package org.tindertec.model;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.*;
import javax.persistence.Table;
import lombok.Data;

@Entity(name="tb_likes")
@Table(name="tb_likes")
@Data
public class Likes {
	@Id
	private int cod_like; 
	
	private int cod_usu ;
	@OneToOne
	@JoinColumn(name="cod_usu", insertable=false, updatable=false)
	private Usuario usuario; 
	
	private int cod_usu_like;
	@OneToOne
	@JoinColumn(name="cod_usu_like", insertable=false, updatable=false)
	private Usuario usuario_Like; 
	
	private String det_match ;

	
	
}
