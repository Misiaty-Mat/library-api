package com.wsb.libraryapi.entities;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.*;
import org.hibernate.annotations.JdbcTypeCode;
import org.hibernate.annotations.UuidGenerator;
import org.hibernate.type.SqlTypes;

import java.sql.Timestamp;
import java.util.UUID;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity(name = "Loan")
@Table(name = "loan")
public class Loan {
    @Id
    @UuidGenerator()
    @JdbcTypeCode(SqlTypes.CHAR)
    @Column(length = 36, columnDefinition = "varchar(36)", updatable = false, nullable = false)
    private UUID id;
    
    @Column(name = "return_date", updatable = false)
    private Timestamp return_date;

    @Column(name = "returned", nullable = false)
    private Boolean returned;

    @ManyToOne
    private User user;

    @ManyToOne
    private Book book;
}