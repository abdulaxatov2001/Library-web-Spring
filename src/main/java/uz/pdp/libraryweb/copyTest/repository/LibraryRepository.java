package uz.pdp.libraryweb.copyTest.repository;
import org.springframework.data.jpa.repository.JpaRepository;
import uz.pdp.libraryweb.copyTest.entity.Library;

import java.util.Optional;

public interface LibraryRepository
        extends JpaRepository<Library, Integer> {
    Optional<Library> findAllByName(String name);


}
