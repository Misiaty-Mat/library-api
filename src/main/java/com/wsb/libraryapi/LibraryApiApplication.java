package com.wsb.libraryapi;

import com.wsb.libraryapi.authorities.Role;
import com.wsb.libraryapi.entities.Book;
import com.wsb.libraryapi.entities.Loan;
import com.wsb.libraryapi.entities.User;
import com.wsb.libraryapi.repositories.BookRepository;
import com.wsb.libraryapi.repositories.LoanRepository;
import com.wsb.libraryapi.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

@SpringBootApplication
public class LibraryApiApplication implements CommandLineRunner {

	@Autowired
	PasswordEncoder passwordEncoder;
	@Autowired
	BookRepository bookRepository;
	@Autowired
	UserRepository userRepository;
	@Autowired
	LoanRepository loanRepository;

	public static void main(String[] args) {
		SpringApplication.run(LibraryApiApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		if (userRepository.count() == 0) {
			User user1 = User.builder()
					.email("admin@admin.com")
					.role(Role.ADMIN)
					.name("Adam")
					.surname("Kowalski")
					.password(passwordEncoder.encode("admin"))
					.build();

			User user2 = User.builder()
					.email("worker@worker.com")
					.role(Role.WORKER)
					.name("Patrycja")
					.surname("Nowak")
					.password(passwordEncoder.encode("worker"))
					.build();

			User user3 = User.builder()
					.email("customer@customer.com")
					.role(Role.CUSTOMER)
					.name("Michał")
					.surname("Nowak")
					.password(passwordEncoder.encode("customer"))
					.build();

			userRepository.saveAll(List.of(user1, user2, user3));
		}

		if (bookRepository.count() == 0) {
			Book book1 = Book.builder()
					.name("Władca Pierścieni - Powrót Króla")
					.author("J. R. R. Tolkien")
					.category("Fantasy")
					.isbn("123453646")
					.available(true)
					.tag("Gandalf")
					.build();

			Book book2 = Book.builder()
					.name("Harry Potter - Komnata tajemnic")
					.author("J. K. Rowling")
					.category("Fantasy")
					.isbn("32537435")
					.available(true)
					.tag("Wizards")
					.build();

			Book book3 = Book.builder()
					.name("Trzech Muszkieterów")
					.author("Alexandre Dumas")
					.category("Adventure")
					.isbn("6353424")
					.available(false)
					.tag("France")
					.build();

			bookRepository.saveAll(List.of(book1, book2, book3));
		}

		if (loanRepository.count() == 0) {
			Book book = bookRepository.findBookByAvailableFalse();
			User user = userRepository.findAll().get(0);

			Calendar calendar = Calendar.getInstance();
			calendar.add(Calendar.DAY_OF_YEAR, 1);

			Loan loan = Loan.builder()
					.return_date(new Timestamp(calendar.getTime().getTime()))
					.returned(false)
					.book(book)
					.user(user)
					.build();

			loanRepository.save(loan);
		}
	}
}
