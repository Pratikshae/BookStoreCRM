package in.natalia.main.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import in.natalia.main.entities.MyBookList;

@Repository
public interface MyBooksRepository extends JpaRepository<MyBookList, Long>{

}
