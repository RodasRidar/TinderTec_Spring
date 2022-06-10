package org.tindertec.repository;
import org.tindertec.model.*;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.query.Procedure;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface IMatchRepository extends JpaRepository <Match, Integer>{
	
	//Usuario findByCorreoAndClave(String correo,String clave);
//docs.spring.io/spring-data/jpa/docs/current/reference/html/#jpa.query-methods
	
	//@Procedure(procedureName = "USP_LISTAR_MATCH_POR_USUARIO")
	//List<Usuario> USP_LISTAR_MATCH_POR_USUARIO(@Param("cod_usu1") int cod_usu );
	
	@Query(value="{CALL USP_LISTAR_MATCH_POR_USUARIO(:cod_usu_1)}",nativeQuery = true)
	List<Match> USP_LISTAR_MATCH_POR_USUARIO(@Param("cod_usu_1")int cod_usu1);
	
	@Procedure(procedureName = "USP_ELIMINAR_MATCH_POR_USUARIO")
	void USP_ELIMINAR_MATCH_POR_USUARIO(@Param("cod_usu_1") int cod_usu1,@Param("cod_usu_2") int cod_usu2 );
	
	
}
