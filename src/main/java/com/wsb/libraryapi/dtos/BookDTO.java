package com.wsb.libraryapi.dtos;

import lombok.Builder;
import lombok.Data;

import java.util.UUID;

@Builder
@Data
public class BookDTO {
    private UUID id;
    private String name;
    private String author;
    private String category;
    private String tag;
    private String isbn;
    private Boolean available;
}
