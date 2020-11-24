drop table if exists book;
drop table if exists author;
drop table if exists genre;
drop table if exists comment;

create table book(id bigint identity NOT NULL primary key, title varchar(255), author_id bigint, genre_id bigint);
create table genre(id bigint identity NOT NULL primary key, title varchar(255));
create table author(id bigint identity NOT NULL primary key, first_name varchar(255), last_name varchar(255));
create table comment(id bigint identity NOT NULL primary key, comment varchar(255), book_id bigint references book (id) on delete cascade);

alter table book
add constraint fk_bookGenre
foreign key (genre_id) references genre(Id);

alter table book
add constraint fk_bookAuthor
foreign key (author_id) references author(Id);