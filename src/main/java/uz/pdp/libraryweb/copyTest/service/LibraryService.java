package uz.pdp.libraryweb.copyTest.service;

import org.springframework.stereotype.Service;

import uz.pdp.libraryweb.copyTest.dto.LibraryDto;
import uz.pdp.libraryweb.copyTest.dto.Response;
import uz.pdp.libraryweb.copyTest.entity.Address;
import uz.pdp.libraryweb.copyTest.entity.Library;
import uz.pdp.libraryweb.copyTest.repository.AddressRepository;
import uz.pdp.libraryweb.copyTest.repository.LibraryRepository;

import java.util.List;
import java.util.Optional;

@Service
public class LibraryService {
    final LibraryRepository libraryRepository;
    final AddressRepository addressRepository;

    public LibraryService(LibraryRepository libraryRepository, AddressRepository addressRepository) {
        this.libraryRepository = libraryRepository;
        this.addressRepository = addressRepository;
    }

    public List<Library> getAll() {
        return libraryRepository.findAll();
    }

    public Library getById(Integer id) {
        Optional<Library> byId = libraryRepository.findById(id);
        return byId.orElse(new Library());
    }

    public Response addLibrary(LibraryDto libraryDto) {
        Response response=new Response();
        for (Library library : libraryRepository.findAll()) {
            if(library.getName().toLowerCase().equals(libraryDto.getName().toLowerCase())) {
                response.setMessage("This "+libraryDto.getName()+" name already exist");
                return response;
            }
        }
        Address address=new Address();
        address.setCity(libraryDto.getCity());
        address.setHome(libraryDto.getHome());
        address.setStreet(libraryDto.getStreet());
        Address save = addressRepository.save(address);
        Library library=new Library();
        library.setName(libraryDto.getName());
        library.setActive(libraryDto.getActive());
        library.setAddress(save);
        libraryRepository.save(library);
        response.setMessage("Add library "+libraryDto.getName());
        response.setSuccess(true);
        return response;
    }

    public Response editeLibrary(Library libraryDto) {
        Response response=new Response();
        Optional<Library> allByName = libraryRepository.findAllByName(libraryDto.getName());
        if(allByName.isPresent()){
            Library byId = libraryRepository.getById(libraryDto.getId());
            byId.setName(libraryDto.getName());
            byId.setActive(libraryDto.isActive());
            Address address = byId.getAddress();
            address.setCity(libraryDto.getAddress().getCity());
            address.setHome(libraryDto.getAddress().getHome());
            address.setStreet(libraryDto.getAddress().getStreet());
            byId.setAddress(address);
            libraryRepository.save(byId);
            response.setSuccess(true);
            response.setMessage("Edite library");
            return response;
        }
        response.setMessage(libraryDto.getName()+" -already exist");
        return response;
    }

    public Response deleteLibrary(Integer id) {
        Response response=new Response();
        Optional<Library> byId = libraryRepository.findById(id);
        if(byId.isPresent()){
            Library library = byId.get();
            library.setActive(false);
            libraryRepository.save(library);
            response.setMessage("Delete library");
            response.setSuccess(true);
            return response;
        }
        response.setMessage("This id doesn't exist");
        return response;
    }
}
