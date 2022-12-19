package uz.pdp.libraryweb.copyTest.controller;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import uz.pdp.libraryweb.copyTest.dto.BookDto;
import uz.pdp.libraryweb.copyTest.dto.Response;
import uz.pdp.libraryweb.copyTest.entity.Book;
import uz.pdp.libraryweb.copyTest.entity.Category;
import uz.pdp.libraryweb.copyTest.entity.Library;
import uz.pdp.libraryweb.copyTest.service.BookService;
import uz.pdp.libraryweb.copyTest.service.CategoryService;
import uz.pdp.libraryweb.copyTest.service.LibraryService;


import java.util.List;
import java.util.stream.Collectors;

@Controller
@RequestMapping(path = "/book")
public class BookController {
   final BookService bookService;
   final CategoryService categoryService;
   final LibraryService libraryService;

    public BookController(BookService bookService, CategoryService categoryService, LibraryService libraryService) {
        this.bookService = bookService;
        this.categoryService = categoryService;
        this.libraryService = libraryService;
    }

    @GetMapping
    public String pageOperation(Model model){
        List<Book> all = bookService.getAll();
        if(all.size()>0)
            model.addAttribute("success","Connection good");
        else
            model.addAttribute("error","Not found any book");
        List<Category> categoryList = categoryService.getAll();
        model.addAttribute("categories",categoryList);
        List<Library> libraryList = libraryService.getAll();
        model.addAttribute("libraries",libraryList);
        model.addAttribute("books",all);
        model.addAttribute("bookDto",new BookDto());

        return "bookOperation";
    }
    @GetMapping(path = "/library/{id}")
    public List<Book> allBookByLibraryId(@PathVariable("id") Integer id){
        return bookService.getAllBookByLib(id);
    }
    @GetMapping(path = "/{id}")
    public Book getById(@PathVariable("id") Integer id){
        return bookService.getById(id);
    }
    @PostMapping(path = "/add")
    public String addBook( BookDto bookDto){
       bookService.addBook(bookDto);
        return "redirect:/book";
    }
    @GetMapping (path = "/edite/{id}")
    public String editBook(@PathVariable("id") Integer id,Model model){
        Book book = bookService.getById(id);
        List<Book> all = bookService.getAll();
        List<Category> categoryList = categoryService.getAll();
        model.addAttribute("categories",categoryList);
        List<Library> libraryList = libraryService.getAll();
        model.addAttribute("libraries",libraryList);
        model.addAttribute("books",all);
        BookDto bookDto=new BookDto();
        bookDto.setId(book.getId());
        bookDto.setLibrary(book.getLibrary().getId());
        bookDto.setActive(book.isActive());
        bookDto.setName(book.getName());
        bookDto.setCategoryList(book.getCategoryList().stream().map(Category::getId).collect(Collectors.toList()));
        model.addAttribute("bookDto",bookDto);
      return "editeBookPage";
    }
    @PostMapping (path = "/edite/{id}")
    public String editBookLast(BookDto bookDto,Model model){
        System.out.println(bookDto);
        Response response = bookService.editeBook( bookDto);
        if(response.isSuccess())
            model.addAttribute("success",response.getMessage());
        else
            model.addAttribute("error","Something wrong");
        List<Book> all = bookService.getAll();
        List<Category> categoryList = categoryService.getAll();
        model.addAttribute("categories",categoryList);
        List<Library> libraryList = libraryService.getAll();
        model.addAttribute("libraries",libraryList);
        model.addAttribute("books",all);
        model.addAttribute("bookDto",new BookDto());

        return "bookOperation";
    }
    @GetMapping(path = "delete/{id}")
    public String deleteBook(@PathVariable("id") Integer id){
         bookService.deleteBook(id);
        return "redirect:/book";
    }
    @GetMapping(path = "/search")
    public String searchBook(Model model){
        List<Category> categoryList = categoryService.getAll();
        model.addAttribute("categories",categoryList);
        List<Library> libraryList = libraryService.getAll();
        model.addAttribute("libraries",libraryList);
        return "searchBook";
    }
    @PostMapping(path = "/search")
    public String searchBookResuly( @RequestParam("name") String name,Model model){
        List<Book> books = bookService.searchBook(name);
        if(books.size()>0)
            model.addAttribute("success","Connection good total result "+books.size());
        else
            model.addAttribute("error","Not found any book");
        model.addAttribute("books",books);
        return "searchBookResult";
    }

}
