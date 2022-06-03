package org.tindertec.model;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import lombok.Data;

@Entity(name="tb_interes_genero")
@Table(name="tb_interes_genero")
@Data
public class InteresGenero {
	
	@Id
	private int cod_interes  ;
	private String des_interes ;
	
}
