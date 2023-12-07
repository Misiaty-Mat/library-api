package com.wsb.libraryapi.mappers;

import com.wsb.libraryapi.dtos.BookDTO;
import com.wsb.libraryapi.entities.Book;
import org.mapstruct.*;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE, componentModel = MappingConstants.ComponentModel.SPRING)
public interface BookMapper {
    Book toEntity(BookDTO bookDTO);

    BookDTO toDto(Book book);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    Book partialUpdate(BookDTO bookDTO, @MappingTarget Book book);
}