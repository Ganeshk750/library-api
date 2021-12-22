package com.ganesh.service.impl;

import com.ganesh.dto.AuthorCreationDto;
import com.ganesh.dto.BookCreationDto;
import com.ganesh.dto.BookLendDto;
import com.ganesh.dto.MemberCreationDto;
import com.ganesh.exception.EntityNotFoundException;
import com.ganesh.model.Author;
import com.ganesh.model.Book;
import com.ganesh.model.Member;
import com.ganesh.repository.AuthorRepository;
import com.ganesh.repository.BookRepository;
import com.ganesh.repository.LendRepository;
import com.ganesh.repository.MemberRepository;
import com.ganesh.service.LibraryService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class LibraryServiceImpl implements LibraryService {

    private final AuthorRepository authorRepository;
    private final MemberRepository memberRepository;
    private final LendRepository lendRepository;
    private final BookRepository bookRepository;

    @Override
    public Book readBookById(String id) {
        Optional<Book> book = bookRepository.findById(id);
        if(book.isPresent()){
            return book.get();
        }
        throw new EntityNotFoundException("Cant find any book under given ID: "+ id);
    }

    @Override
    public List<Book> readBooks() {
        return bookRepository.findAll();
    }

    @Override
    public Book readBook(String isbn) {
        Optional<Book> book = bookRepository.findByIsbn(isbn);
        if(book.isPresent()){
            return book.get();
        }
        throw new EntityNotFoundException("Cant find any book under given ISBN: "+ isbn);
    }

    @Override
    public Book createBook(BookCreationDto bookDto) {
        return null;
    }

    @Override
    public void deleteBook(String id) {

    }

    @Override
    public Member createMember(MemberCreationDto memberDto) {
        return null;
    }

    @Override
    public Member updateMember(String id, MemberCreationDto memberDto) {
        return null;
    }

    @Override
    public Author createAutoAuthor(AuthorCreationDto authorDto) {
        return null;
    }

    @Override
    public List<String> lendBook(BookLendDto bookLendDto) {
        return null;
    }
}
