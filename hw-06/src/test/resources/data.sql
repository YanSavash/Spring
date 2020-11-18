insert into author (id,first_name,last_name) values (1,'Антуан','Сент-Экзюпери')
insert into author (id,first_name,last_name) values (2,'Айзек','Азимов')
insert into author (id,first_name,last_name) values (3,'Спаркс','Николас')

insert into genre (id,title) values (1,'Приключения')
insert into genre (id,title) values (2,'Фантастика')
insert into genre (id,title) values (3,'Роман')

insert into book (id,title,genre_id,author_id) values (1,'Маленький принц',1,1)
insert into book (id,title,genre_id,author_id) values (2,'Конец вечности',2,2)
insert into book (id,title,genre_id,author_id) values (3,'Спеши любить',3,3)