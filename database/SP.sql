USE `bd_tindertec`;
--                                                                SP USP_INSERTAR_LIKE
DROP procedure IF EXISTS `USP_INSERTAR_LIKE`;
DELIMITER $$
USE `bd_tindertec`$$
CREATE PROCEDURE `USP_INSERTAR_LIKE` (
in cod_usu_1 int ,
in cod_usu_2 int,
OUT mensaje varchar(300))
BEGIN
declare idMatch VARCHAR(200);
SET SQL_SAFE_UPDATES = 0;
-- Si el primer usuario es menor que el segundo @idMatch adquiere el valor en ese orden
 if cod_usu_1 < cod_usu_2 then
		set idMatch =CONCAT ((CAST(cod_usu_1 AS CHAR)),"-",(CAST(cod_usu_2 as CHAR)));
 else -- Si el segundo usuario es menor que el primero @idMatch adquiere el valor en ese orden
		set idMatch =CONCAT ((CAST(cod_usu_2 as CHAR)),"-",(CAST(cod_usu_1 as CHAR)));
 end if;
	insert into tb_likes values (null,cod_usu_1,cod_usu_2,idMatch) ;-- inserto valores incluyenfo @idMatch con el formato(1-2);
	-- Si el codigo de match generado existe dos veces devolver un mensaje de match
if ((select det_match from tb_likes group by det_match having det_match=idMatch and count(det_match)=2 ) is not null) THEN
		set mensaje ='MATCH';
		insert into tb_match values(null,idMatch,cod_usu_1,cod_usu_2);
		insert into tb_match values(null,idMatch,cod_usu_2,cod_usu_1);
		delete from tb_likes where  det_match=idMatch;
end if;   
END$$
DELIMITER ;
/*
delimiter ;
CALL USP_INSERTAR_LIKE(2,1,mensaje);
SELECT mensaje;*/


--                                                                SP USP_REGISTRAR_CHAT

USE `bd_tindertec`;
DROP procedure IF EXISTS `USP_REGISTRAR_CHAT`;

DELIMITER $$
USE `bd_tindertec`$$
CREATE PROCEDURE `USP_REGISTRAR_CHAT` (
cod_usu1 int ,
cod_usu2 int ,
 mensaje varchar (500))
begin
insert into tb_chat values (null,cod_usu1,cod_usu2,mensaje ,NOW());
end$$

DELIMITER ;
/* 
call  USP_REGISTRAR_CHAT (1,2,'Hola');*/

--                                                                SP USP_LISTAR_CHAT_POR_USUARIO


USE `bd_tindertec`;
DROP procedure IF EXISTS `USP_LISTAR_CHAT_POR_USUARIO`;

DELIMITER $$
USE `bd_tindertec`$$
CREATE PROCEDURE `USP_LISTAR_CHAT_POR_USUARIO` (
cod_usu_1 int,
cod_usu_2 int) 
begin


select c.*  
from tb_chat c 
where cod_usu1=cod_usu_1   and cod_usu2=cod_usu_2 
union 
select c.* 
from tb_chat c where cod_usu1=cod_usu_2   and cod_usu2=cod_usu_1 order by fecha asc;
end$$

DELIMITER ;

/*
call USP_LISTAR_CHAT_POR_USUARIO(1,2)
*/
--                                                                SP USP_LISTAR_MATCH_POR_USUARIO

USE `bd_tindertec`;
DROP procedure IF EXISTS `USP_LISTAR_MATCH_POR_USUARIO`;

DELIMITER $$
USE `bd_tindertec`$$
CREATE PROCEDURE `USP_LISTAR_MATCH_POR_USUARIO` (cod_usu_1 int) 
begin
/*SELECT cod_usu2,u.nombres ,u.foto1 FROM tb_match m 
join tb_usuario u
on u.cod_usu=m.cod_usu2
where m.cod_usu1=cod_usu1;*/
select * from tb_match where cod_usu1=cod_usu_1;
END$$

DELIMITER ;

-- call USP_LISTAR_MATCH_POR_USUARIO (1)

--                                                                SP USP_ELIMINAR_MATCH_POR_USUARIO


USE `bd_tindertec`;
DROP procedure IF EXISTS `USP_ELIMINAR_MATCH_POR_USUARIO`;

DELIMITER $$
USE `bd_tindertec`$$
CREATE PROCEDURE `USP_ELIMINAR_MATCH_POR_USUARIO` (cod_usu_1 int,
cod_usu_2 int)
begin
declare det_matchs varchar (500);
set det_matchs= (select det_match from tb_match where cod_usu1=cod_usu_1 and  cod_usu2=cod_usu_2 );
SET SQL_SAFE_UPDATES = 0;
SET FOREIGN_KEY_CHECKS=0;
delete from tb_match where det_match=det_matchs;
 
delete from tb_chat where cod_usu1=cod_usu_1 and cod_usu2=cod_usu_2;
delete from tb_chat where cod_usu1=cod_usu_2 and cod_usu2=cod_usu_1;

insert into tb_dislike values (null,cod_usu_1,cod_usu_2);
insert into tb_dislike values (null,cod_usu_2,cod_usu_1);
end$$

DELIMITER ;

-- call USP_ELIMINAR_MATCH_POR_USUARIO (1,3)

--                                                                SP USP_USUARIO_LISTAR


USE `bd_tindertec`;
DROP procedure IF EXISTS `USP_USUARIO_LISTAR`;

DELIMITER $$
USE `bd_tindertec`$$
CREATE PROCEDURE `USP_USUARIO_LISTAR` (sp_cod_usu int)
BEGIN
if (cast((select cod_interes from tb_usuario where cod_usu=sp_cod_usu) as UNSIGNED) =3 ) then 
		select  u.* , c.des_carrera,s.des_sede from tb_usuario u join tb_carreras c on u.cod_carrera=c.cod_carrera join tb_sedes_usu s on u.cod_sede=s.cod_sede 
		where u.cod_usu<>sp_cod_usu  
		and u.cod_usu not in (select cod_usu_dislike from tb_dislike where cod_usu=sp_cod_usu )
		and u.cod_usu not in (select cod_usu_like from tb_likes where cod_usu=sp_cod_usu)
		and u.cod_usu not in (select cod_usu2 from tb_match where cod_usu1=sp_cod_usu) limit 1;
       --  and u.cod_usu not in (select cod_usu2 from tb_match where cod_usu1=sp_cod_usu);

else  
		select u.* , c.des_carrera,s.des_sede from tb_usuario u join tb_carreras c on u.cod_carrera=c.cod_carrera join tb_sedes_usu s on u.cod_sede=s.cod_sede 
		where u.cod_usu<>sp_cod_usu 
		and u.cod_usu not in (select cod_usu_dislike from tb_dislike where cod_usu=sp_cod_usu )
		and u.cod_usu not in (select cod_usu_like from tb_likes where cod_usu=sp_cod_usu)
		and u.cod_genero in (select cod_interes from tb_usuario where cod_usu=sp_cod_usu)
		 and u.cod_usu not in (select cod_usu2 from tb_match where cod_usu1=sp_cod_usu) limit 1;
        -- and u.cod_usu not in (select cod_usu2 from tb_match where cod_usu1=sp_cod_usu);
 end if;
END$$

DELIMITER ;

-- call USP_USUARIO_LISTAR (2);


--                                                                SP USP_USUARIO_LISTAR


USE `bd_tindertec`;
DROP procedure IF EXISTS `USP_Listar_Usuarios_Likes`;

DELIMITER $$
USE `bd_tindertec`$$
CREATE PROCEDURE `USP_Listar_Usuarios_Likes` (sp_cod_usu int)
BEGIN
SELECT * from tb_usuario where cod_usu in (select cod_usu_like from tb_likes where cod_usu=sp_cod_usu )  ;

END$$

DELIMITER ;

-- call USP_Listar_Usuarios_Likes (1);


--                                                                SP USP_USUARIO_LISTAR


USE `bd_tindertec`;
DROP procedure IF EXISTS `USP_INSERTAR_DISLIKE`;

DELIMITER $$
USE `bd_tindertec`$$
CREATE PROCEDURE `USP_INSERTAR_DISLIKE` (cod_usu_sp int,
cod_usu_2_sp int)
BEGIN

insert into tb_dislike values (null,cod_usu_sp,cod_usu_2_sp);
insert into tb_dislike values (null,cod_usu_2_sp,cod_usu_sp);
END$$

DELIMITER ;

--                                                                SP USP_ELIMINAR_LIKE //cuando se elimina un like ya no se puede encontrar a esa persona en la busqueda

USE `bd_tindertec`;
DROP procedure IF EXISTS `USP_ELIMINAR_LIKE`;

DELIMITER $$
USE `bd_tindertec`$$
CREATE PROCEDURE `USP_ELIMINAR_LIKE` (cod_usu_sp int,
cod_usu_2_sp int)
BEGIN

insert into tb_dislike values (null,cod_usu_sp,cod_usu_2_sp);
insert into tb_dislike values (null,cod_usu_2_sp,cod_usu_sp);
delete from tb_likes where cod_usu=cod_usu_sp and cod_usu_like=cod_usu_2_sp ;
END$$

DELIMITER ;
-- 

--                                                                SP usp_usuario_intentos 

USE `bd_tindertec`;
DROP procedure IF EXISTS `usp_usuario_intentos`;

DELIMITER $$
USE `bd_tindertec`$$
CREATE PROCEDURE `usp_usuario_intentos` (
correo varchar(50),
pass varchar(15)
)
BEGIN

	Declare contrasena varchar(15);
    declare vintentos int;
		set contrasena=(Select clave From tb_usuario Where email=correo);
		if(contrasena <> pass) then
                set vintentos=cast((select intentos from tb_usuario where email=correo) as unsigned )+1;
					if(vintentos<=3) then 
						update tb_usuario set intentos=vintentos where email=correo;
					else 
						update tb_usuario set fecha_bloqueo = (date_add(now(),interval 5 minute)) where email=correo;
						end if;
		else
				update tb_usuario set intentos=0 where email=correo;
				update tb_usuario set fecha_bloqueo = null where email=correo;
		end if;
END$$
DELIMITER ;
-- 


--                                                                SP usp_usuario_acceso 

USE `bd_tindertec`;
DROP procedure IF EXISTS `usp_usuario_acceso`;

DELIMITER $$
USE `bd_tindertec`$$
CREATE PROCEDURE `usp_usuario_acceso` (
correo varchar(50),
pass varchar(15),
out mensaje varchar(100) 
)
begin

Declare clave_usu varchar(10);
START TRANSACTION;
if (not exists(select * from tb_usuario where email=correo)) then 
	set mensaje = 'No existe el usuario';
else
	if((select fecha_bloqueo from tb_usuario where email=correo)<= now() or (select fecha_bloqueo from tb_usuario where email=correo) is null) then
					set clave_usu=(Select clave From tb_usuario Where email=correo);
					if(clave_usu != pass) then
							call  usp_usuario_intentos (correo,pass);
							if((select intentos from tb_usuario where email=correo)=3) then
									call  usp_usuario_intentos (correo,pass);
									Set mensaje='Se bloqueo usuario, vuelve a intentarlo en 5 minutos';
							else
									Set mensaje='No coincide la clave, vuelva a ingresar';
							 end if;  
					else
							call  usp_usuario_intentos (correo,pass);
							Set mensaje='OK';
					 end if;  
		else 
			set mensaje='Bloqueo temporal de 5 minutos';
		 end if;  
end if;  
COMMIT;
END$$
DELIMITER ;

/*
delimiter ;
CALL usp_usuario_acceso('user1@user1','1231',@out_mensaje );
SELECT @out_mensaje;
select * from tb_usuario;
select * from tb_usuario;

call  usp_usuario_intentos ('user1@user1','123');

delimiter ;
CALL USP_INSERTAR_LIKE(2,1,mensaje);
SELECT mensaje;*/




--                                                                SP USP_SEDE_LISTAR 

USE `bd_tindertec`;
DROP procedure IF EXISTS `USP_SEDE_LISTAR`;

DELIMITER $$
USE `bd_tindertec`$$
CREATE PROCEDURE `USP_SEDE_LISTAR` (
)
begin
select * from tb_sedes_usu;

END$$
DELIMITER ;
-- 

--                                                                SP USP_GENERO_LISTAR 

USE `bd_tindertec`;
DROP procedure IF EXISTS `USP_GENERO_LISTAR`;

DELIMITER $$
USE `bd_tindertec`$$
CREATE PROCEDURE `USP_GENERO_LISTAR` (
)
begin
select * from tb_sedes_usu;

END$$
DELIMITER ;
-- 

--                                                                SP USP_INTERES_LISTAR 

USE `bd_tindertec`;
DROP procedure IF EXISTS `USP_INTERES_LISTAR`;

DELIMITER $$
USE `bd_tindertec`$$
CREATE PROCEDURE `USP_INTERES_LISTAR` (
)
begin
select * from tb_interes_genero	;

END$$
DELIMITER ;
-- 

--                                                                SP USP_CARRERAS_LISTAR 

USE `bd_tindertec`;
DROP procedure IF EXISTS `USP_CARRERAS_LISTAR`;

DELIMITER $$
USE `bd_tindertec`$$
CREATE PROCEDURE `USP_CARRERAS_LISTAR` (
)
begin
select * from tb_carreras	;

END$$
DELIMITER ;
-- 




--                                                                SP USP_USUARIO_REGISTRAR 

USE `bd_tindertec`;
DROP procedure IF EXISTS `USP_USUARIO_REGISTRAR`;

DELIMITER $$
USE `bd_tindertec`$$
CREATE PROCEDURE `USP_USUARIO_REGISTRAR` (
nombre  varchar(50),
correo varchar(50),
fecnac date,
clave varchar(15),
idsede  int,
idcarrera int,
idgenero int,
idinteres int,
descripcion varchar(250),
f1 varchar(800)
)
begin
insert into tb_usuario values (null,nombre ,correo,clave,f1,'','','','',fecnac,descripcion,0,null,idcarrera,idsede ,idinteres,idgenero,1);

END$$
DELIMITER ;
-- 

--                                                                SP USP_EDITAR_PERFIL
USE `bd_tindertec`;
DROP procedure IF EXISTS `USP_EDITAR_PERFIL`;

DELIMITER $$
USE `bd_tindertec`$$
CREATE PROCEDURE `USP_EDITAR_PERFIL` (
cod_usu_sp int,
nombres_sp varchar(60),
descripcion_sp varchar(350),
cod_interes_sp int,
cod_carrera_sp int, 
cod_sede_sp int
)
BEGIN
update tb_usuario
set nombres = nombres_sp,
descripcion = descripcion_sp,
cod_interes = cod_interes_sp,
cod_carrera = cod_carrera_sp,
cod_sede = cod_sede_sp
where cod_usu = cod_usu_sp;
END$$
DELIMITER ;
 /*
 CALL USP_EDITAR_PERFIL (5, 'Pierina', 'Me gustan los deportes y jugar videojuegos.', 3, 1, 1);
SELECT * FROM tb_usuario;
 */

--                                                                SP USP_USUARIO_INSERTAR_FOTO
USE `bd_tindertec`;
DROP procedure IF EXISTS `USP_USUARIO_INSERTAR_FOTO`;

DELIMITER $$
USE `bd_tindertec`$$
CREATE PROCEDURE `USP_USUARIO_INSERTAR_FOTO` (
codigo_usuario int,
posicion int,
url_foto varchar(800)
)
BEGIN
	if (posicion=2) then
		update tb_usuario set foto2=url_foto where cod_usu=codigo_usuario;
	end if;
	if (posicion=3) then
		update tb_usuario set foto3=url_foto where cod_usu=codigo_usuario;
	end if;
	if (posicion=4) then
		update tb_usuario set foto4=url_foto where cod_usu=codigo_usuario;
	end if;
	if (posicion=5) then
		update tb_usuario set foto5=url_foto where cod_usu=codigo_usuario;
	end if;
END$$
DELIMITER ;
--                                                                SP USP_USUARIO_ELIMINAR
USE `bd_tindertec`;
DROP procedure IF EXISTS `USP_USUARIO_ELIMINAR`;

DELIMITER $$
USE `bd_tindertec`$$
CREATE PROCEDURE `USP_USUARIO_ELIMINAR` (
codigo_usuario int
)
BEGIN
delete from tb_match where cod_usu1=codigo_usuario;
delete from tb_match where cod_usu2=codigo_usuario;

delete from tb_chat where cod_usu1=codigo_usuario;
delete from tb_chat where cod_usu2=codigo_usuario;

delete from tb_likes where cod_usu=codigo_usuario;
delete from tb_likes where cod_usu_like=codigo_usuario;

delete from tb_dislike where cod_usu=codigo_usuario;
delete from tb_dislike where cod_usu_dislike=codigo_usuario;

delete from tb_usuario where cod_usu=codigo_usuario;

END$$
DELIMITER ;

--                                                                SP USP_Registrar_Boleta


USE `bd_tindertec`;
DROP procedure IF EXISTS `USP_Registrar_Boleta`;

DELIMITER $$
USE `bd_tindertec`$$
CREATE PROCEDURE `USP_Registrar_Boleta` (cod_usu_sp int,
cod_sub_sp int,cod_med_pago_sp int )
BEGIN
insert into tb_boleta values (null,cod_usu_sp,cod_sub_sp,cod_med_pago_sp,current_date());
END$$

DELIMITER ;
/*
SELECT * FROM tb_boleta;
SELECT * from tb_chat;
SELECT * from tb_match;
delete from tb_likes where cod_like>0 ;
delete from tb_match where id_match >0 ;
delete from tb_dislike where cod_dislike>0 ;
SELECT * from tb_likes;
SELECT * from tb_carreras;
select * from tb_usuario;
SELECT * from tb_dislike;
SELECT * from tb_suscripcion;

call USP_USUARIO_ELIMINAR(6);

call USP_Listar_Usuarios_Likes(1)*/

     