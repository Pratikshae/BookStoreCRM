package in.natalia.main.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

import in.natalia.main.entities.Book;
import in.natalia.main.entities.MyBookList;
import in.natalia.main.services.BookService;
import in.natalia.main.services.MyBooksService;

@Controller
public class BookStoreController {

	@Autowired
	private BookService bookService;

	@Autowired
	private MyBooksService myBooksService;

	@GetMapping({ "/", "/home" })
	public String openHomePage() {
		return "home";

	}

	@GetMapping("/bookRegister")
	public String registerNewBook() {
		return "register-book";
	}

	@PostMapping("/addNewBook")
	public String addNewBook(@ModelAttribute Book book) {
		bookService.addNewBook(book);
		return "redirect:/availableBooks";
	}

	@GetMapping("/availableBooks")
	public ModelAndView getAllAvailableBooks() {
		List<Book> bookList = bookService.getAllBooks();
//		ModelAndView modelAndView = new ModelAndView();
//		modelAndView.setViewName("bookList");
//		modelAndView.addObject("bookList", bookList);

		// we can do this in single line
		return new ModelAndView("available-books", "bookList", bookList);
	}

	@GetMapping("/myBooks")
	public String getMyBooks(Model model) {

		List<MyBookList> myBookList = myBooksService.getAllMyBooks();
		model.addAttribute("myBookList", myBookList);

		return "my-books";
	}

	@GetMapping("/myBookList/{id}")
	public String getMyBookList(@PathVariable("id") Long id) {

		Book book = bookService.getBookById(id);
		MyBookList myBookList = new MyBookList();
		myBookList.setId(book.getId());
		myBookList.setBookTitle(book.getBookTitle());
		myBookList.setAuthorName(book.getAuthorName());
		myBookList.setPrice(book.getPrice());
		myBooksService.saveMyBook(myBookList);

		return "redirect:/myBooks";
	}

	@GetMapping("/editBook/{id}")
	public String editBookInfo(@PathVariable("id") Long id, Model model) {
		Book myBook = bookService.getBookById(id);

		model.addAttribute("myBook", myBook);

		return "edit-book";
	}

	@PostMapping("/saveChanges")
	public String saveEditedBook(@ModelAttribute("myBook") Book book, Model model) {
		try {
			bookService.updateBook(book);

			model.addAttribute("successMsg", "Book details updated successfully");

		} catch (Exception e) {
			e.printStackTrace();
			model.addAttribute("errorMsg", "Failed to update book, Please try again.");

		}
		return "redirect:/availableBooks";
	}

	@GetMapping("/deleteBook/{id}")
	public String deleteBook(@PathVariable("id") Long id, Model model) {
		try {
			bookService.deleteBook(id);
			model.addAttribute("successMsg", "book deleted successfully.");

		} catch (Exception e) {
			e.printStackTrace();
			model.addAttribute("errorMsg", "Something went wrong, could not delete. Please try later!");

		}
		return "redirect:/availableBooks";
	}

}
