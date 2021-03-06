package com.ganesh.repository;

import ch.qos.logback.core.boolex.EvaluationException;
import com.ganesh.model.Book;
import com.ganesh.model.Lend;
import com.ganesh.model.LendStatus;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface BookRepository extends MongoRepository<Book, String> {
   // Optional<Lend> findByBookAndStatus(Book book, LendStatus lendStatus);
    Optional<Book> findByIsbn(String isbn);
}
