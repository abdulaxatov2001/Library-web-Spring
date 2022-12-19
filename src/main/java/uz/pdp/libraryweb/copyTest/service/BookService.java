package uz.pdp.libraryweb.copyTest.service;

import org.springframework.stereotype.Service;
import uz.pdp.libraryweb.copyTest.dto.BookDto;
import uz.pdp.libraryweb.copyTest.dto.Response;
import uz.pdp.libraryweb.copyTest.entity.Book;
import uz.pdp.libraryweb.copyTest.entity.Category;
import uz.pdp.libraryweb.copyTest.entity.Library;
import uz.pdp.libraryweb.copyTest.repository.BookRepository;
import uz.pdp.libraryweb.copyTest.repository.CategoryRepository;
import uz.pdp.libraryweb.copyTest.repository.LibraryRepository;


import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
public class BookService {
    final LibraryRepository libraryRepository;
    final CategoryRepository categoryRepository;
    final BookRepository bookRepository;

    public BookService(LibraryRepository libraryRepository, CategoryRepository categoryRepository, BookRepository bookRepository) {
        this.libraryRepository = libraryRepository;
        this.categoryRepository = categoryRepository;
        this.bookRepository = bookRepository;
    }

    public List<Book> getAll() {
        return bookRepository.findAll();
    }

    public Book getById(Integer id) {
        Optional<Book> byId = bookRepository.findById(id);
        return byId.orElse(new Book());
    }

    public Response addBook(BookDto bookDto) {
        Response response=new Response();
        String name = bookDto.getName();
        Integer libraryId = bookDto.getLibrary();
        List<Integer> categoryList = bookDto.getCategoryList();
        if(!Objects.isNull(name)){
            Optional<Library> byId = libraryRepository.findById(libraryId);
            if (byId.isPresent()) {
                Library library = byId.get();
                List<Category> allById = categoryRepository.findAllById(categoryList);
                Book book=new Book(name,library,allById);
                book.setActive(bookDto.isActive());
                bookRepository.save(book);
                response.setMessage("Add book");
                response.setSuccess(true);
                return response;
            }
            response.setMessage("Not found library");
        }
        response.setMessage("Name mustn't empty");
       return response;
    }

    public Response editeBook( BookDto bookDto) {
        Library library = libraryRepository.getById(bookDto.getId());
        List<Category> allById = categoryRepository.findAllById(bookDto.getCategoryList());
        Book book = bookRepository.getById(bookDto.getId());
        book.setLibrary(library);
        book.setCategoryList(allById);
        book.setActive(bookDto.isActive());
        bookRepository.save(book);
        return new Response("Edite book",true);
    }

    public Response deleteBook(Integer id) {
        Response response=new Response();
        Optional<Book> byId = bookRepository.findById(id);
        if(byId.isPresent()){
            Book book = byId.get();
            book.setActive(false);
            bookRepository.save(book);
            response.setMessage("Delete book");
            response.setSuccess(true);
            return response;
        }
        response.setMessage("This id doesn't exist");
        return response;
    }

    public List<Book> getAllBookByLib(Integer id) {
      return   bookRepository.findAllByLibrary_Id(id);
    }

    public List<Book> searchBook(String name) {
     return bookRepository.findAllByNameContainsAndActiveTrue(name);
    }


}
