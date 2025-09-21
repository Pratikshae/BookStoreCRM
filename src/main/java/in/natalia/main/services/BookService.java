package in.natalia.main.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import in.natalia.main.entities.Book;
import in.natalia.main.repositories.BookRepository;

@Service
public class BookService {
	
	@Autowired
	private BookRepository bookRepository;
	
	public void addNewBook(Book book) {
		bookRepository.save(book);
	}
	
	
	public List<Book> getAllBooks() {
		return bookRepository.findAll();
	}
	
	public Book getBookById(Long id) {
		return bookRepository.findById(id).get();
	}
	
	public void updateBook(Book book) {
		bookRepository.save(book);
	}
	
	public void deleteBook(Long id) {
		bookRepository.deleteById(id);
	}
}
