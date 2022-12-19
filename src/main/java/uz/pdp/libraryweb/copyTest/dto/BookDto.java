package uz.pdp.libraryweb.copyTest.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class BookDto {
    private Integer id;
    private String name;
    private Integer library;
    private boolean active;
    private List<Integer> categoryList;
    public String toString() {
        return super.toString();
    }

    public BookDto(String name) {
        this.name = name;
    }

    public BookDto(String name, Integer library) {
        this.name = name;
        this.library = library;
    }

    public BookDto(String name, boolean active) {
        this.name = name;
        this.active = active;
    }
}
