package com.ganesh.repository;

import com.ganesh.model.Book;
import com.ganesh.model.Lend;
import com.ganesh.model.LendStatus;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.Optional;

public interface LendRepository extends MongoRepository<Lend,String> {

    Optional<Lend> findByBookAndStatus(Book book, LendStatus status);
}
