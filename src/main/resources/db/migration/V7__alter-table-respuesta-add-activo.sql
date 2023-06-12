alter table respuestas add column activo tinyint;
update respuestas set activo = 1;
alter table respuestas modify activo tinyint not null;