package org.tindertec.model;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import lombok.Data;

@Entity(name="tb_genero_usu")
@Table(name="tb_genero_usu")
@Data
public class GeneroUsuario {
	@Id
	private int cod_genero  ;
	private String des_genero ;

}
