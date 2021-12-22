package com.ganesh.controller;

import com.ganesh.dto.AuthorCreationDto;
import com.ganesh.dto.BookCreationDto;
import com.ganesh.dto.BookLendDto;
import com.ganesh.dto.MemberCreationDto;
import com.ganesh.model.Author;
import com.ganesh.model.Book;
import com.ganesh.model.Member;
import com.ganesh.service.LibraryService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/api/library")
@RequiredArgsConstructor
public class LibraryController {

    private final LibraryService libraryService;

    @GetMapping("/book")
    public ResponseEntity readBooks(@RequestParam(required = false) String isbn){
        if(isbn == null){
            return ResponseEntity.ok(libraryService.readBooks());
        }
        return ResponseEntity.ok(libraryService.readBook(isbn));
    }

    @GetMapping("/book/{bookId}")
    public ResponseEntity<Book> readBook(@PathVariable String bookId){
        return ResponseEntity.ok(libraryService.readBookById(bookId));
    }

    @DeleteMapping("/book/{bookId}")
    public ResponseEntity<Void> deleteBook(@PathVariable String bookId){
        libraryService.deleteBook(bookId);
        return ResponseEntity.ok().build();
    }

    @PostMapping("/book")
    public ResponseEntity<Book> createBook(@RequestBody BookCreationDto bookDto){
        return ResponseEntity.ok(libraryService.createBook(bookDto));
    }

    @PatchMapping("/member/{memberId}")
    public ResponseEntity<Member> updateMember(@RequestBody MemberCreationDto memberCreationDto, @PathVariable String memberId){
        return ResponseEntity.ok(libraryService.updateMember(memberId, memberCreationDto));
    }

    @PostMapping("/book/lend")
    public ResponseEntity<List<String>> lendABook(@RequestBody BookLendDto bookLendDto){
        return ResponseEntity.ok(libraryService.lendBook(bookLendDto));
    }

    @PostMapping("/author")
    public ResponseEntity<Author> createAuthor(@RequestBody AuthorCreationDto authorCreationDto){
        return ResponseEntity.ok(libraryService.createAutoAuthor(authorCreationDto));
    }


}
