package uz.pdp.libraryweb.copyTest.controller;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import uz.pdp.libraryweb.copyTest.dto.LibraryDto;
import uz.pdp.libraryweb.copyTest.dto.Response;
import uz.pdp.libraryweb.copyTest.entity.Library;
import uz.pdp.libraryweb.copyTest.service.LibraryService;

import java.util.List;

@Controller
@RequestMapping(path = "/library")
public class LibraryController {
    final LibraryService libraryService;

    public LibraryController(LibraryService libraryService) {
        this.libraryService = libraryService;
    }


    @GetMapping
    public String allLibraries(Model model){
        List<Library> all = libraryService.getAll();
        if(all.size()==0){
            model.addAttribute("message","Not found any library");
        }
        model.addAttribute("libraries",all);
        return "showLibrary";
    }
    @GetMapping(path = "/{id}")
    public Library getById(@PathVariable("id") Integer id){
        return libraryService.getById(id);
    }

    @GetMapping(path = "/add")
    public String addLibrary(Model model){
       model.addAttribute("libraryDto",new LibraryDto());
       return "addLibrary";
    }

    @PostMapping(path = "/add")
    public String addLibrary(LibraryDto libraryDto,Model model){
        Response response = libraryService.addLibrary(libraryDto);
        if (response.isSuccess())
            model.addAttribute("success",response.getMessage());
        else
            model.addAttribute("error",response.getMessage());
        return "index";
    }

    @GetMapping (path = "/edite/{id}")
    public String pageEditLibrary(@PathVariable("id") Integer id,Model model){
        Library library = libraryService.getById(id);
        model.addAttribute("library",library);
        return "editeLibrary";
    }
   @PostMapping (path = "/edite/{id}")
    public String editLibrary(@PathVariable("id") Integer id,Library library,Model model){
       library.setId(id);
       Response response = libraryService.editeLibrary(library);
       if(response.isSuccess())
       model.addAttribute("success",response.getMessage());
       else
           model.addAttribute("error",response.getMessage());

       return "index";
   }
   @GetMapping(path = "/delete/{id}")
    public String deleteLibrary(@PathVariable("id") Integer id,Model model){
       Response response = libraryService.deleteLibrary(id);
       if(response.isSuccess())
           model.addAttribute("success",response.getMessage());
       else
           model.addAttribute("error",response.getMessage());
       return "index";
   }

}
