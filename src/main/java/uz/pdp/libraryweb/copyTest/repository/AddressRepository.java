package uz.pdp.libraryweb.copyTest.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import uz.pdp.libraryweb.copyTest.entity.Address;

public interface AddressRepository
        extends JpaRepository<Address, Integer> {

}
