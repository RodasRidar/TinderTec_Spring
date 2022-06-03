package org.tindertec.repository;
import org.tindertec.model.*;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IMatchRepository extends JpaRepository <Match, Integer>{
	
	//Usuario findByCorreoAndClave(String correo,String clave);
//docs.spring.io/spring-data/jpa/docs/current/reference/html/#jpa.query-methods
}
