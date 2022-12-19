package uz.pdp.libraryweb.copyTest.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import javax.persistence.*;
import java.util.Set;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
public class Library {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Column(nullable = false)
    private String name;
    private boolean active=true;

    @OneToOne
    private Address address;
    @JsonIgnore
    @OneToMany(mappedBy = "library")
    @ToString.Exclude
    private Set<Book>books;

    public Library(String name, Address address) {
        this.name = name;
        this.address = address;
    }
}
