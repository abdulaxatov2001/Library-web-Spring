package uz.pdp.libraryweb.copyTest.entity;

import lombok.*;

import javax.persistence.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
@Entity
public class Address {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Column(nullable = false)
    private String home;
    @Column(nullable = false)
    private String city;
    @Column(nullable = false)
    private String street;
}
