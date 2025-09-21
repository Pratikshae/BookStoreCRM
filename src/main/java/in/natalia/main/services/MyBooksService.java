package in.natalia.main.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import in.natalia.main.entities.MyBookList;
import in.natalia.main.repositories.MyBooksRepository;

@Service
public class MyBooksService {
	
	@Autowired
	private MyBooksRepository myBooksRepository;
	
	
	public void saveMyBook(MyBookList myBookList) {
		myBooksRepository.save(myBookList);
	}
	
	public List<MyBookList> getAllMyBooks() {
		return myBooksRepository.findAll();
	}
    
	
	public void deleteBookById(Long id) {
		myBooksRepository.deleteById(id);
	}
}
