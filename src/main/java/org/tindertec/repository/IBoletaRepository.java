package org.tindertec.repository;
import org.tindertec.model.*;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.query.Procedure;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface IBoletaRepository extends JpaRepository <Boleta, Integer>{
	
	@Procedure(procedureName = "USP_Registrar_Boleta")
	void USP_Registrar_Boleta(
			@Param("cod_usu_sp")int cod_usu,
			@Param("cod_sub_sp")int cod_sub,
			@Param("cod_med_pago_sp")int cod_pago
			);

}
