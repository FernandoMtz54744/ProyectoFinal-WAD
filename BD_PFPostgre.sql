create table Usuario(
	idUsuario SERIAL primary key not null,
    usuario varchar(50) not null, 
    pass varchar(50) not null,
    correo varchar(50) not null
);

create table Restaurante(
	idRestaurante SERIAL primary key not null,
    nombre varchar(50) not null,
    correo varchar(50) not null, 
    pass varchar(50) not null, 
    descripcion varchar(500) not null, 
    web varchar(50) not null, 
    horario varchar(50) not null,
    telefono varchar(50) not null
);

create table Categoria(
	idCategoria int primary key not null,
    categoria varchar(50) not null
);

create table Platillo(
	idPlatillo SERIAL primary key not null,
    nombre varchar(50) not null, 
    descripcion varchar(500) not null,
    foto bytea,
    nombreFoto varchar(50) not null,
    idRestaurante int not null,
    idCategoria int not null,
    foreign key (idRestaurante) references Restaurante(idRestaurante),
    foreign key (idCategoria) references Categoria(idCategoria)
);

create table Comentario(
	idComentario Serial primary key not null,
    comentario varchar(100) not null,
    calificacion int not null,
    idUsuario int not null,
    idPlatillo int not null,
    foreign key (idUsuario) references Usuario(idUsuario),
    foreign key (idPlatillo) references Platillo(idPlatillo) ON DELETE CASCADE
);

insert into Categoria(idCategoria, categoria) values(1,'Rapida');
insert into Categoria(idCategoria, categoria)  values(2,'Mexicana');
insert into Categoria(idCategoria, categoria)  values(3,'Italiana');
insert into Categoria(idCategoria, categoria)  values(4,'Asiatica');
insert into Categoria(idCategoria, categoria)  values(5,'Postres');
insert into Categoria(idCategoria, categoria)  values(6,'Vegetariana');
insert into Categoria(idCategoria, categoria)  values(7,'Brasileña');
insert into Categoria(idCategoria, categoria)  values(8,'Carnes');
insert into Categoria(idCategoria, categoria)  values(9,'Gourmet');
insert into Categoria(idCategoria, categoria)  values(10,'Mariscos');
insert into Categoria(idCategoria, categoria)  values(11,'Desayunos');

create view PlatilloView as
select P.idPlatillo, P.nombre as nombrePlatillo, P.descripcion, P.foto, P.nombreFoto, 
P.idRestaurante, R.nombre as nombreRestaurante, 
P.idCategoria, C.categoria
from platillo P inner Join Restaurante R on P.idRestaurante = R.idRestaurante inner Join Categoria C on P.idCategoria = C.idCategoria;

Create view ComentarioView as 
Select C.idComentario, C.comentario, C.calificacion, C.idPlatillo,
C.idUsuario, U.usuario from Comentario C inner Join Usuario U on C.idUsuario = U.idUsuario;

select * from PlatilloView;
select * from ComentarioView;