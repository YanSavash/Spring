insert into author (id,firstName,lastName) values (1,'Антуан','Сент-Экзюпери')
insert into author (id,firstName,lastName) values (2,'Айзек','Азимов')
insert into author (id,firstName,lastName) values (3,'Спаркс','Николас')

insert into genre (id,title) values (1,'Приключения')
insert into genre (id,title) values (2,'Фантастика')
insert into genre (id,title) values (3,'Роман')

insert into books (id,title,genreId,authorId) values (1,'Маленький принц',1,1)
insert into books (id,title,genreId,authorId) values (2,'Конец вечности',2,2)
insert into books (id,title,genreId,authorId) values (3,'Спеши любить',3,3)