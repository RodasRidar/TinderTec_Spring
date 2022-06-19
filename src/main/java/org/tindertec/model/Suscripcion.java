package org.tindertec.model;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import lombok.Data;

@Entity(name="tb_suscripcion")
@Table(name="tb_suscripcion")
@Data
public class Suscripcion {
	@Id
	private int cod_suscrip  ;
	private String detalle ;
	private double costo;
}
