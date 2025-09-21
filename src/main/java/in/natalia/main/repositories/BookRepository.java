package in.natalia.main.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import in.natalia.main.entities.Book;

@Repository
public interface BookRepository extends JpaRepository<Book, Long>{
	List<Book> findAll();
}
