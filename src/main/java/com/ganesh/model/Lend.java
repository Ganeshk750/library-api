package com.ganesh.model;

import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.Instant;

@Document
@Setter
@Getter
public class Lend {

    @Id
    private String id;
    private LendStatus status;
    private Instant startOn;
    private Instant dueOne;

    @DBRef
    private Book book;

    @DBRef
    private Member member;

}
