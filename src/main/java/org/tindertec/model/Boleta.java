package org.tindertec.model;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import lombok.Data;

@Entity(name="tb_boleta")
@Table(name="tb_boleta")
@Data
public class Boleta {
	@Id
	private String cod_boleta ;
	private int cod_usu ; 
	@ManyToOne
	@JoinColumn(name="cod_usu", insertable=false, updatable=false)
	private Usuario usuario; 
	
	private int cod_sub; 
	@ManyToOne
	@JoinColumn(name="cod_sub", insertable=false, updatable=false)
	private Suscripcion subscripcion; 
	
	private int cod_medio_pago ; 
	@ManyToOne
	@JoinColumn(name="cod_medio_pago", insertable=false, updatable=false)
	private MedioPago medioPago; 
	private String fecha_pago ;
}
