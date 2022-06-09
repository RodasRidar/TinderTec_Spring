package org.tindertec.repository;
import org.tindertec.model.*;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.query.Procedure;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface IDislikesRepository extends JpaRepository <Dislikes, Integer>{
	
	//Usuario findByCorreoAndClave(String correo,String clave);
//docs.spring.io/spring-data/jpa/docs/current/reference/html/#jpa.query-methods

	@Procedure(procedureName = "USP_INSERTAR_DISLIKE")
	void USP_INSERTAR_DISLIKE(@Param("cod_usu_sp") int cod_usu ,@Param("cod_usu_2_sp") int cod_usu_dislike );


	@Procedure(procedureName = "USP_ELIMINAR_LIKE")
	void USP_ELIMINAR_LIKE(@Param("cod_usu_sp") int cod_usu ,@Param("cod_usu_2_sp") int cod_usu_dislike );
}
