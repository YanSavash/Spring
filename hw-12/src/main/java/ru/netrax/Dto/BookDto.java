package ru.netrax.Dto;

import lombok.Data;
import lombok.NoArgsConstructor;
import ru.netrax.Model.Author;
import ru.netrax.Model.Book;
import ru.netrax.Model.Comment;
import ru.netrax.Model.Genre;

import java.util.List;

@Data
@NoArgsConstructor
public class BookDto {

    private String title;

    private Author author;

    private Genre genre;

    private List<Comment> commentList;

    public static BookDto toDto(Book book) {
        BookDto bookDto = new BookDto();
        bookDto.setTitle(book.getTitle());
        bookDto.setAuthor(book.getAuthor());
        bookDto.setGenre(book.getGenre());
        bookDto.setCommentList(book.getCommentList());
        return bookDto;
    }

    public static Book toEntity(BookDto bookDto){
        Book book = new Book();
        book.setTitle(bookDto.getTitle());
        book.setAuthor(bookDto.getAuthor());
        book.setGenre(bookDto.getGenre());
        book.setCommentList(bookDto.getCommentList());
        return book;
    }
}
