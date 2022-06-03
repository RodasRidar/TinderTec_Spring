package org.tindertec.model;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import lombok.Data;

@Entity(name="tb_carreras")
@Table(name="tb_carreras")
@Data
public class Carreras {
	@Id
	private int cod_carrera ;
	private String des_carrera ;
}
