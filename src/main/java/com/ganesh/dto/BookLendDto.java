package com.ganesh.dto;

import lombok.Data;

import java.util.List;

@Data
public class BookLendDto {

    private List<String> booksIds;
    private String memberId;

}
