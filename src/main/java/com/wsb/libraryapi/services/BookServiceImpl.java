package com.wsb.libraryapi.services;

import com.wsb.libraryapi.dtos.BookDTO;
import com.wsb.libraryapi.entities.Book;
import com.wsb.libraryapi.mappers.BookMapper;
import com.wsb.libraryapi.repositories.BookRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class BookServiceImpl implements BookService {
    private final BookRepository bookRepository;
    private final BookMapper bookMapper;

    @Override
    public List<BookDTO> listBooks() {
        return bookRepository.findAll().stream().map(bookMapper::toDto).collect(Collectors.toList());
    }

    @Override
    public BookDTO saveBook(BookDTO bookDTO) {
        return bookMapper.toDto(
            bookRepository.save(
                    bookMapper.toEntity(bookDTO)
            )
        );
    }

}
