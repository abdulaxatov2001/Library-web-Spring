package uz.pdp.libraryweb.copyTest.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;
import java.util.UUID;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
public class Book {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String name;
    private boolean active=true;

    @GeneratedValue(generator = "uuid2", strategy = GenerationType.AUTO)
    private UUID code=UUID.randomUUID();

    @ManyToOne
    private Library library;

    @ManyToMany
    private List<Category> categoryList;


    @Override
    public String toString() {
        return super.toString();
    }

    public Book(String name, Library library, List<Category> categoryList) {
        this.name = name;
        this.library = library;
        this.categoryList = categoryList;
    }

}
