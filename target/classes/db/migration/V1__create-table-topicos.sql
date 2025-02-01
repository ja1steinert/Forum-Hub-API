create table topicos (
   id bigint not null auto_increment,
   titulo varchar(100),
   mensagem varchar(255),
   data_criacao timestamp,
   estado boolean,
   autor varchar(100),
   curso varchar(100),

   primary key (id)

);