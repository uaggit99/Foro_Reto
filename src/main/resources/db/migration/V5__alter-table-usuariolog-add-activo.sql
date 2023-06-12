alter table usuarioslog add column activo tinyint;
update usuarioslog set activo = 1;
alter table usuarioslog modify activo tinyint not null;
