
use bd_tindertec;

insert into tb_carreras values (null,'Computacion e Informatica');
insert into tb_carreras values (null,'Traduccion e Interpretacion');
insert into tb_carreras values (null,'Comunicacion y arte digital');
insert into tb_carreras values (null,'Gestion de Recursos Humanos');
insert into tb_carreras values (null,'Administracion de Redes');
insert into tb_carreras values (null,'Publicidad y Branding');
insert into tb_carreras values (null,'Diseño de interiores');
insert into tb_carreras values (null,'Mecatronica');
insert into tb_carreras values (null,'Administracion de empresas');
insert into tb_carreras values (null,'Administracion de Negocios Internacionales');
insert into tb_carreras values (null,'Contabilidad');


insert into tb_sedes_usu values (null,'Breña');
insert into tb_sedes_usu values (null,'Bella Vista');
insert into tb_sedes_usu values (null,'Lima Centro');
insert into tb_sedes_usu values (null,'San Juan de Lurigancho');
insert into tb_sedes_usu values (null,'Independencia');

insert into tb_interes_genero values (null,'Masculino');
insert into tb_interes_genero values (null,'Femenino');
insert into tb_interes_genero values (null,'Ambos');

insert into tb_genero_usu values (null,'Masculino');
insert into tb_genero_usu values (null,'Femenino');

insert into tb_suscripcion values (null,'Free',0);
insert into tb_suscripcion values (null,'Premium Mensual',9.9);
insert into tb_suscripcion values (null,'Premium Anual',90.9);

insert into tb_medio_pago values (null,'Credito');
insert into tb_medio_pago values (null,'Debito');

insert into tb_usuario values (null,'Richard Rodas','i201910135@cibertec.edu.pe','123','https://i.pinimg.com/750x/39/66/b3/3966b3b7dacc81af949258810bbbc2c7.jpg','https://i.pinimg.com/750x/0d/21/b1/0d21b19aabf407610486567ad2ebaaa4.jpg','https://i.pinimg.com/750x/d9/90/11/d990110407148975abf62d2816bab5f4.jpg','','','2000-01-21','Hola me dicen Richard y me encanta oir musica',0,null,1,1,2,1,1);

insert into tb_usuario values (null,'Julieta del Rio','i202012345@cibertec.edu.pe','123','https://images.pexels.com/photos/6944690/pexels-photo-6944690.jpeg?cs=srgb&dl=pexels-mikhail-nilov-6944690.jpg&fm=jpg','','','','','2000-01-21','Hola quiero conocer a mi proximo socio',0,null,2,1,1,2,1);
insert into tb_usuario values (null,'Rocio Silva','i202011234@cibertec.edu.pe','123','https://images.pexels.com/photos/2884842/pexels-photo-2884842.jpeg?auto=compress&cs=tinysrgb&w=1260&h=750&dpr=1','','','','','2000-01-21','Hola quiero conocer a mi proximo socio',0,null,3,2,1,2,1);
insert into tb_usuario values (null,'Pierina Lopez','i202010676@cibertec.edu.pe','123','https://i.pinimg.com/750x/97/7a/77/977a77aa2dbc9c5fe13bfc10964465cf.jpg','','','','','2000-01-21','Hola quiero conocer a mi proximo socio',0,null,1,1,1,2,1);
insert into tb_usuario values (null,'Luisa Alvarado','i201909879@cibertec.edu.pe','123','https://images.pexels.com/photos/1382731/pexels-photo-1382731.jpeg?auto=compress&cs=tinysrgb&dpr=1&w=500','','','','','2000-01-21','Hola quiero conocer a mi proximo socio',0,null,6,4,1,2,1);
insert into tb_usuario values (null,'Maraia Valdez','1i201923657@cibertec.edu.pe','123','https://images.pexels.com/photos/2726111/pexels-photo-2726111.jpeg?auto=compress&cs=tinysrgb&dpr=1&w=500','','','','','2000-01-21','Hola quiero conocer a mi proximo socio',0,null,2,2,1,2,1);

insert into tb_usuario values (null,'Flavio Guzman','i202112145@cibertec.edu.pe','123','https://images.pexels.com/photos/220453/pexels-photo-220453.jpeg?auto=compress&cs=tinysrgb&w=1260&h=750&dpr=1','','','','','2000-01-21','Hola quiero conocer a mi proximo socio',0,null,1,1,2,1,1);
insert into tb_usuario values (null,'Mike Jones','i201822234@cibertec.edu.pe','123','https://images.pexels.com/photos/9461775/pexels-photo-9461775.jpeg?auto=compress&cs=tinysrgb&w=1260&h=750&dpr=1','https://images.pexels.com/photos/9461784/pexels-photo-9461784.jpeg?auto=compress&cs=tinysrgb&w=1260&h=750&dpr=1','','','','2000-01-21','Hola quiero conocer a mi proximo socio',0,null,5,2,2,1,1);
insert into tb_usuario values (null,'Pablo Martinez','i201523456@cibertec.edu.pe','123','https://images.pexels.com/photos/10939132/pexels-photo-10939132.jpeg?auto=compress&cs=tinysrgb&w=1260&h=750&dpr=1','','','','','2000-01-21','Hola quiero conocer a mi proximo socio',0,null,5,3,2,1,1);
insert into tb_usuario values (null,'Guillermo Galvez','i201409879@cibertec.edu.pe','123','https://images.pexels.com/photos/9068580/pexels-photo-9068580.jpeg?auto=compress&cs=tinysrgb&w=1260&h=750&dpr=1','','','','','2000-01-21','Hola quiero conocer a mi proximo socio',0,null,8,5,2,1,1);



/*
call USP_LISTAR_CHAT_POR_USUARIO(1,3);
call USP_LISTAR_MATCH_POR_USUARIO(1);
call USP_Listar_Usuarios_Likes(1);
SELECT * FROM tb_match;
SELECT * FROM tb_likes;
SELECT * FROM tb_dislike;
SELECT * FROM tb_usuario;

SELECT * FROM tb_boleta;
SELECT * FROM tb_boleta;
SELECT * FROM tb_suscripcion;
SELECT * FROM tb_medio_pago;

delete  FROM tb_match where cod_usu1>0 ;
delete  FROM tb_dislike where cod_dislike>0;
delete  FROM tb_likes where cod_usu>0 ;
call USP_Listar_Usuarios_Likes(1)

CALL USP_INSERTAR_LIKE(1,3,@mensaje);
CALL USP_INSERTAR_LIKE(1,11,@mensaje);

call USP_USUARIO_LISTAR(1);




call USP_REGISTRAR_CHAT(1,2,'Hola');
call USP_REGISTRAR_CHAT(2,1,'Hola como estas');
call USP_REGISTRAR_CHAT(1,2,'Bien y tu ?');
call USP_REGISTRAR_CHAT(2,1,'Con hambre');


call USP_REGISTRAR_CHAT(1,3,'Hola Rocio');
call USP_REGISTRAR_CHAT(3,1,'Hola Richard');
call USP_REGISTRAR_CHAT(1,3,'Como te va?');
call USP_REGISTRAR_CHAT(3,1,'Bien y tu ?');
call USP_REGISTRAR_CHAT(1,3,'Con sueño');
call USP_USUARIO_LISTAR (3);

*/
