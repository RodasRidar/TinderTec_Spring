package org.tindertec.model;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import lombok.Data;

@Entity(name="tb_medio_pago")
@Table(name="tb_medio_pago")
@Data
public class MedioPago {
	@Id
	private int cod_medio_pago  ;
	private String des_medio_pago ;
}
