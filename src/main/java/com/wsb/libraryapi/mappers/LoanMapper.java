package com.wsb.libraryapi.mappers;

import com.wsb.libraryapi.dtos.LoanDTO;
import com.wsb.libraryapi.dtos.LoanDetailsDto;
import com.wsb.libraryapi.entities.Loan;
import org.mapstruct.*;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE, componentModel = MappingConstants.ComponentModel.SPRING)
public interface LoanMapper {
    Loan toEntity(LoanDTO loanDTO);

    LoanDetailsDto toDto(Loan loan);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    Loan partialUpdate(LoanDTO loanDTO, @MappingTarget Loan loan);
}