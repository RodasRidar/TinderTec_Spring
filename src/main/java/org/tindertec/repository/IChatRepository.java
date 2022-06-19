package org.tindertec.repository;
import org.tindertec.model.*;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.query.Procedure;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface IChatRepository extends JpaRepository <Chat, Integer>{
	
	//Usuario findByCorreoAndClave(String correo,String clave);
//docs.spring.io/spring-data/jpa/docs/current/reference/html/#jpa.query-methods
	
	@Query(value="{CALL USP_LISTAR_CHAT_POR_USUARIO(:cod_usu_1,:cod_usu_2)}",nativeQuery = true)
	List<Chat> USP_LISTAR_CHAT_POR_USUARIO(@Param("cod_usu_1")int cod_usu1,
											 @Param("cod_usu_2")int cod_usu2);
	
	/*@Query(value="{CALL USP_REGISTRAR_CHAT(:cod_usu1,:cod_usu2,:mensaje)}",nativeQuery = true)
	void USP_REGISTRAR_CHAT(@Param("cod_usu1")int cod_usu1, 
							@Param("cod_usu2")int cod_usu2,
							@Param("mensaje")String mensaje);*/
	@Procedure(procedureName = "USP_REGISTRAR_CHAT")
	void USP_REGISTRAR_CHAT(@Param("cod_usu1")int cod_usu1, 
			@Param("cod_usu2")int cod_usu2,
			@Param("mensaje")String mensaje);
}
