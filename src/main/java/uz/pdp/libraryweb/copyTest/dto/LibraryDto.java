package uz.pdp.libraryweb.copyTest.dto;

import lombok.Data;

@Data
public class LibraryDto {
    private String name;
    private String city;
    private String home;
    private String street;
    private Boolean active;
}
