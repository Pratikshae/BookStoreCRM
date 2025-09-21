package in.natalia.main.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import in.natalia.main.services.MyBooksService;

@Controller
public class MyBookListController {
	
	@Autowired
	private MyBooksService myBooksService;
	
	@RequestMapping("/deleteMyBook/{id}")
	public String deleteMyBook(@PathVariable("id") Long id) {
		myBooksService.deleteBookById(id);
		
		return "redirect:/myBooks";
	}
 
}
