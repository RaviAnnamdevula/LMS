package com.project.minor1.request;

import com.project.minor1.model.Author;
import com.project.minor1.model.Book;
import com.project.minor1.model.BookType;
import lombok.*;

@Setter
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class BookCreateRequest {
    private String name;
    private String bookNo;
    private Integer cost;
    private BookType type;
    private String authorName;
    private String authorEmail;

    public Author toAuthor() {
        return Author.builder().name(this.authorName).email(this.authorEmail).build();
    }

    public Book toBook() {
        return Book.builder().name(this.name).bookNo(this.bookNo).cost(this.cost).type(this.type).build();
    }
}
