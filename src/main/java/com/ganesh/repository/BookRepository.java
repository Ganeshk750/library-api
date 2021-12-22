package com.ganesh.repository;

import ch.qos.logback.core.boolex.EvaluationException;
import com.ganesh.model.Book;
import com.ganesh.model.Lend;
import com.ganesh.model.LendStatus;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.Optional;

public interface BookRepository extends MongoRepository<Book, String> {
    Optional<Lend> findByBookAndStatus(Book book, LendStatus lendStatus);
}
