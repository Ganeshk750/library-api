package com.ganesh.service;

import com.ganesh.dto.AuthorCreationDto;
import com.ganesh.dto.BookCreationDto;
import com.ganesh.dto.BookLendDto;
import com.ganesh.dto.MemberCreationDto;
import com.ganesh.model.Author;
import com.ganesh.model.Book;
import com.ganesh.model.Member;

import java.util.List;

public interface LibraryService {
    Book readBookById(String id);
    List<Book> readBooks();
    Book readBook(String isbn);
    Book createBook(BookCreationDto bookDto);
    void deleteBook(String id);
    Member createMember(MemberCreationDto memberDto);
    Member updateMember(String id,MemberCreationDto memberDto);
    Author createAutoAuthor(AuthorCreationDto authorDto);
    List<String> lendBook(BookLendDto bookLendDto);


}
