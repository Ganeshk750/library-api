package com.ganesh.service.impl;

import com.ganesh.dto.AuthorCreationDto;
import com.ganesh.dto.BookCreationDto;
import com.ganesh.dto.BookLendDto;
import com.ganesh.dto.MemberCreationDto;
import com.ganesh.exception.EntityNotFoundException;
import com.ganesh.model.*;
import com.ganesh.repository.AuthorRepository;
import com.ganesh.repository.BookRepository;
import com.ganesh.repository.LendRepository;
import com.ganesh.repository.MemberRepository;
import com.ganesh.service.LibraryService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
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
        Optional<Author> author = authorRepository.findById(bookDto.getAuthorId());
         if(author.isPresent()){
             throw new EntityNotFoundException("Author Not Found");
         }
         Book book = new Book();
         BeanUtils.copyProperties(bookDto, book);
         book.setAuthor(author.get());
         return bookRepository.save(book);
    }

    @Override
    public void deleteBook(String id) {
       bookRepository.deleteById(id);
    }

    @Override
    public Member createMember(MemberCreationDto memberDto) {
        Member member = new Member();
        BeanUtils.copyProperties(memberDto, member);
        member.setMemberStatus(MemberStatus.ACTIVE);
        return memberRepository.save(member);
    }

    @Override
    public Member updateMember(String id, MemberCreationDto memberDto) {
        Optional<Member> optionalMember = memberRepository.findById(id);
        if(!optionalMember.isPresent()){
            throw new EntityNotFoundException("Member not present in the db");
        }
        Member member = optionalMember.get();
        member.setLastName(memberDto.getLastName());
        member.setFirstName(memberDto.getFirstName());
        return memberRepository.save(member);
    }

    @Override
    public Author createAutoAuthor(AuthorCreationDto authorDto) {
        Author author = new Author();
        BeanUtils.copyProperties(authorDto, author);
        return authorRepository.save(author);
    }

    @Override
    public List<String> lendBook(BookLendDto bookLendDto) {
        Optional<Member> memberForId = memberRepository.findById(bookLendDto.getMemberId());
         if(!memberForId.isPresent()){
             throw new EntityNotFoundException("Member not present in the database");
         }
         Member member = memberForId.get();
         if(member.getMemberStatus() != MemberStatus.ACTIVE){
             throw new RuntimeException("User is not active to proceed a leading.");
         }
         List<String> booksApprovedToBurrow = new ArrayList<>();
         bookLendDto.getBooksIds().forEach(bookId ->{
             Optional<Book> bookForId = bookRepository.findById(bookId);
             if(!bookForId.isPresent()){
                 throw new EntityNotFoundException("Cant find any book under given ID");
             }
             Optional<Lend> burrowBook = lendRepository.findByBookAndStatus(bookForId.get(), LendStatus.BURROWED);
             if(!burrowBook.isPresent()){
                 booksApprovedToBurrow.add(bookForId.get().getName());
                 Lend lend = new Lend();
                 lend.setMember(memberForId.get());
                 lend.setBook(bookForId.get());
                 lend.setStatus(LendStatus.BURROWED);
                 lend.setStartOn(Instant.now());
                 lend.setDueOne(Instant.now().plus(15, ChronoUnit.DAYS));
                 lendRepository.save(lend);
             }
         });
        return booksApprovedToBurrow;
    }
}
