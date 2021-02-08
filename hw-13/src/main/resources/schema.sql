drop table if exists book;
drop table if exists author;
drop table if exists genre;
drop table if exists comment;
drop table if exists users;
drop table if exists user_role;

create table book(id bigint identity NOT NULL primary key, title varchar(255), author_id bigint, genre_id bigint);
create table genre(id bigint identity NOT NULL primary key, title varchar(255));
create table author(id bigint identity NOT NULL primary key, first_name varchar(255), last_name varchar(255));
create table comment(id bigint identity NOT NULL primary key, comment varchar(255), book_id bigint references book (id) on delete cascade);
create table users(id bigint identity NOT NULL primary key, username varchar(255) unique, password varchar(255));
create table user_role(user_id bigint, role_set varchar(255));

alter table book
add constraint fk_bookGenre
foreign key (genre_id) references genre(Id);

alter table book
add constraint fk_bookAuthor
foreign key (author_id) references author(Id);

alter table user_role
add constraint fk_user_role_user
foreign key (user_id) references users(Id);