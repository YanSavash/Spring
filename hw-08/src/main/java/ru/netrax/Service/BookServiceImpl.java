package ru.netrax.Service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.netrax.Model.Author;
import ru.netrax.Model.Book;
import ru.netrax.Model.Comment;
import ru.netrax.Model.Genre;
import ru.netrax.Repository.BookRepository;
import ru.netrax.Repository.CommentRepository;

import java.util.ArrayList;
import java.util.List;

@RequiredArgsConstructor
@Service
public class BookServiceImpl implements BookService {

    private final BookRepository bookMongoRepository;
    private final CommentRepository commentMongoRepository;

    @Override
    public List<Book> getAllBooks() {
        return bookMongoRepository.findAll();
    }

    @Override
    public void insertBook(String title, Author author, Genre genre, List<Comment> commentList) {
        Book book = new Book();
        book.setTitle(title);
        book.setAuthor(author);
        book.setGenre(genre);
        book.setCommentList(commentList);
        bookMongoRepository.save(book);
    }

    @Override
    public void insertBookWithComment(String title, Author author, Genre genre, String commentId, String comment) {
        Book book = new Book();
        book.setTitle(title);
        book.setAuthor(author);
        book.setGenre(genre);
        Book insertBook = bookMongoRepository.save(book);

        Comment comment1 = commentMongoRepository.save(new Comment(commentId, comment));
        ArrayList<Comment> list = new ArrayList<>();
        list.add(comment1);
        insertBook.setCommentList(list);
        bookMongoRepository.save(insertBook);
    }

    @Override
    public Book getBook(String id) {
        return bookMongoRepository.findById(id).orElseThrow();
    }

    @Override
    public void updateBook(String id, String title) {
        Book book = bookMongoRepository.findById(id).orElseThrow();
        book.setTitle(title);
        bookMongoRepository.save(book);
    }

    @Override
    public void deleteBook(String id) {
        Book book = bookMongoRepository.findById(id).orElseThrow();
        book.getCommentList().forEach(commentMongoRepository::delete);
        bookMongoRepository.deleteById(id);
    }

    @Override
    public void addComment(String id, Comment comment) {
        Book book = bookMongoRepository.findById(id).orElseThrow();
        book.getCommentList().add(comment);
        bookMongoRepository.save(book);
    }

    @Override
    public void deleteComment(String id, String bookId) {
        Book book = bookMongoRepository.findById(bookId).orElseThrow();
        book.getCommentList().removeIf(e -> e.getId().equals(id));
        bookMongoRepository.save(book);
    }
}
