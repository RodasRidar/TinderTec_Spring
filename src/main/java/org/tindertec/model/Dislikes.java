package org.tindertec.model;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import lombok.Data;

@Entity(name="tb_dislike")
@Table(name="tb_dislike")
@Data
public class Dislikes {
	@Id
	private int cod_dislike ;
	@OneToOne
	@JoinColumn(name="cod_usu", insertable=false, updatable=false)
	private Usuario usuario; 
	
	private int cod_usu_dislike;
	@OneToOne
	@JoinColumn(name="cod_usu_dislike", insertable=false, updatable=false)
	private Usuario usuario_Like; 

}
