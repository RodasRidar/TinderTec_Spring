package org.tindertec.repository;
import org.tindertec.model.*;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface IUsuarioRepository extends JpaRepository <Usuario, Integer>{
	
	//Usuario findByCorreoAndClave(String correo,String clave);
//docs.spring.io/spring-data/jpa/docs/current/reference/html/#jpa.query-methods
	
	@Query (value = "{call USP_Listar_Usuarios_Likes(:sp_cod_usu)}",nativeQuery = true)
	List<Usuario> USP_Listar_Usuarios_Likes(@Param("sp_cod_usu")int cod_usu);
	
	@Query(value="{CALL USP_USUARIO_LISTAR(:spa_cod_usu)}",nativeQuery = true)
	Usuario listaBuscarAmistad(@Param("spa_cod_usu")int cod_usu);
}
