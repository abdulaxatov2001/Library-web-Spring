package uz.pdp.libraryweb.copyTest.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import uz.pdp.libraryweb.copyTest.entity.Category;

public interface CategoryRepository
        extends JpaRepository<Category, Integer> {
}
