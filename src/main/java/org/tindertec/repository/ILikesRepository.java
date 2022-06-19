package org.tindertec.repository;
import org.tindertec.model.*;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.query.Procedure;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface ILikesRepository extends JpaRepository <Likes, Integer>{
	
	//Usuario findByCorreoAndClave(String correo,String clave);
//docs.spring.io/spring-data/jpa/docs/current/reference/html/#jpa.query-methods
	/*
	@Query(value="{CALL USP_INSERTAR_LIKE(:cod_usu_1,:cod_usu_2,:mensaje)}",nativeQuery = true)
	String doLike(
			@Param("cod_usu_1")int cod_usu_1,
			@Param("cod_usu_2")int cod_usu_2,
			@Param("cod_usu_2")String cod_usu_2
			);*/
	
	@Procedure(procedureName = "USP_INSERTAR_LIKE")
	String USP_INSERTAR_LIKE(@Param("cod_usu_1") int cod_usu ,@Param("cod_usu_2") int cod_usu_like );
}
