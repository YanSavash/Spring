package ru.netrax.Dto;

import lombok.Data;
import lombok.NoArgsConstructor;
import ru.netrax.Model.Author;
import ru.netrax.Model.Book;
import ru.netrax.Model.Comment;
import ru.netrax.Model.Genre;

import java.util.ArrayList;
import java.util.List;

@Data
@NoArgsConstructor
public class BookDto {

    private long id;

    private String title;

    private String author;

    private String genre;

    private String commentList;

    public static BookDto toDto(Book book) {
        BookDto bookDto = new BookDto();
        bookDto.setId(book.getId());
        bookDto.setTitle(book.getTitle());
        bookDto.setAuthor(book.getAuthor().toString());
        bookDto.setGenre(book.getGenre().toString());
        bookDto.setCommentList(toCommentDto(book.getCommentList()));
        return bookDto;
    }

    private static String toCommentDto(List<Comment> commentList){
        StringBuilder str = new StringBuilder();
        commentList.forEach(e -> str.append(e.getComment()));
        return str.toString();
    }
}
