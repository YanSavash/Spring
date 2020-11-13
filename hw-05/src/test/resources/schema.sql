drop table if exists books;
drop table if exists author;
drop table if exists genre;

create table books(id bigint primary key, title varchar(255), authorId bigint, genreId bigint);
create table genre(id bigint primary key, title varchar(255));
create table author(id bigint primary key, firstName varchar(255), lastName varchar(255));

alter table books
add constraint fk_bookGenre
foreign key (genreId) references genre(Id);

alter table books
add constraint fk_bookAuthor
foreign key (authorId) references author(Id);