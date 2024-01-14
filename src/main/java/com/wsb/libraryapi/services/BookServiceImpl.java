package com.wsb.libraryapi.services;

import com.wsb.libraryapi.dtos.BookDTO;
import com.wsb.libraryapi.mappers.BookMapper;
import com.wsb.libraryapi.repositories.BookRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class BookServiceImpl implements BookService {
    private final BookRepository bookRepository;
    private final BookMapper bookMapper;

    @Override
    public List<BookDTO> listBooks(String category) {
        if (category == null) {
            return bookRepository.findAll().stream()
                    .map(bookMapper::toDto).collect(Collectors.toList());
        }
        return bookRepository.findAllByCategoryAllIgnoreCase(category).stream()
                .map(bookMapper::toDto).collect(Collectors.toList());
    }

    @Override
    public BookDTO getBookById(UUID id) {
        return bookMapper.toDto(
                bookRepository.findById(id).orElseThrow(EntityNotFoundException::new)
        );
    }

    @Override
    public BookDTO saveBook(BookDTO bookDTO) {
        bookDTO.setAvailable(true);
        return bookMapper.toDto(
            bookRepository.save(
                    bookMapper.toEntity(bookDTO)
            )
        );
    }

    @Override
    public List<String> getCategories() {
        return bookRepository.findDistinctCategories();
    }

}
