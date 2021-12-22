package com.ganesh.repository;

import com.ganesh.model.Book;
import com.ganesh.model.Lend;
import com.ganesh.model.LendStatus;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface LendRepository extends MongoRepository<Lend,String> {

    Optional<Lend> findByBookAndStatus(Book book, LendStatus status);
}
