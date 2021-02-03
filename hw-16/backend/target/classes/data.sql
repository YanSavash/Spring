insert into author (id,first_name,last_name) values (10,'Антуан','Сент-Экзюпери')
insert into author (id,first_name,last_name) values (20,'Айзек','Азимов')
insert into author (id,first_name,last_name) values (30,'Спаркс','Николас')

insert into genre (id,title) values (10,'Приключения')
insert into genre (id,title) values (20,'Фантастика')
insert into genre (id,title) values (30,'Роман')

insert into book (id,title,genre_id,author_id) values (10,'Маленький принц',10,10)
insert into book (id,title,genre_id,author_id) values (20,'Конец вечности',20,20)
insert into book (id,title,genre_id,author_id) values (30,'Спеши любить',30,30)

insert into comment (id,comment,book_id) values (10,'Первый комментарий',10)
insert into comment (id,comment,book_id) values (20,'Второй комментарий',20)
insert into comment (id,comment,book_id) values (30,'Третий комментарий',30)