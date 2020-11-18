package ru.netrax.Model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;


@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
@Table(name = "book")
public class Book {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name = "title")
    private String title;


    @ManyToOne(targetEntity = Author.class, cascade = {CascadeType.MERGE,CascadeType.REFRESH})
    @JoinColumn(name = "author_id", referencedColumnName = "id")
    private Author author;

    @ManyToOne(targetEntity = Genre.class, cascade = {CascadeType.MERGE,CascadeType.REFRESH})
    @JoinColumn(name = "genre_id", referencedColumnName = "id")
    private Genre genre;
}
