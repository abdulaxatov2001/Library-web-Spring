package uz.pdp.libraryweb.copyTest.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import uz.pdp.libraryweb.copyTest.entity.Book;

import java.util.List;

public interface BookRepository
        extends JpaRepository<Book, Integer> {
    List<Book> findAllByLibrary_Id(Integer libraryId);
    List<Book> findAllByNameContainsAndActiveTrue(String name);


}
