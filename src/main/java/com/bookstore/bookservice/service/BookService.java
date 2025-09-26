package com.bookstore.bookservice.service;

import com.bookstore.bookservice.entity.Book;
import com.bookstore.bookservice.repository.BookRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class BookService {

	private final BookRepository bookRepository;

	public BookService(BookRepository bookRepository) {
		this.bookRepository = bookRepository;
	}

	public Book addBook(Book book) {
		return bookRepository.save(book);
	}

	public List<Book> getAllBooks() {
		return bookRepository.findAll();
	}

	public Optional<Book> getBookById(Long id) {
		return bookRepository.findById(id);
	}

	public Book updateBook(Long id, Book updatedBook) {
		return bookRepository.findById(id).map(book -> {
			book.setTitle(updatedBook.getTitle());
			book.setAuthor(updatedBook.getAuthor());
			book.setPrice(updatedBook.getPrice());
			book.setStock(updatedBook.getStock());
			return bookRepository.save(book);
		}).orElseThrow(() -> new RuntimeException("Book not found with id " + id));
	}

	public void deleteBook(Long id) {
		bookRepository.deleteById(id);
	}
}
