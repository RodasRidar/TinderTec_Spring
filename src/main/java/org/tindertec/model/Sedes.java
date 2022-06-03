package org.tindertec.model;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import lombok.Data;

@Entity(name="tb_sedes_usu")
@Table(name="tb_sedes_usu")
@Data
public class Sedes {
	@Id
	private int cod_sede;
	private String des_sede ;
}
