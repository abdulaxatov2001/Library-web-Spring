package uz.pdp.libraryweb.copyTest.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import uz.pdp.libraryweb.copyTest.dto.CategoryDto;
import uz.pdp.libraryweb.copyTest.dto.Response;
import uz.pdp.libraryweb.copyTest.entity.Category;
import uz.pdp.libraryweb.copyTest.service.CategoryService;

import java.util.List;
@Controller
@RequestMapping(path = "/category")
public class CategoryController {
    final CategoryService categoryService;

    public CategoryController(CategoryService categoryService) {
        this.categoryService = categoryService;
    }

    @GetMapping
    public String allOperation(Model model){
        List<Category> all = categoryService.getAll();
        model.addAttribute("categories",all);
        model.addAttribute("categoryDto",new CategoryDto());
        return "categoryOperation";
    }
    @GetMapping(path = "/{id}")
    public Category getById(@PathVariable("id") Integer id){
        return categoryService.getById(id);
    }
   @PostMapping(path = "/add")
    public String addCategory(CategoryDto categoryDto,Model model){

       Response response = categoryService.addCategory(categoryDto);
       if(response.isSuccess())
           model.addAttribute("success",response.getMessage());
       else
           model.addAttribute("error",response.getMessage());
       List<Category> all = categoryService.getAll();
       model.addAttribute("categories",all);
       System.out.println(categoryDto);
        return "categoryOperation";
   }
    @GetMapping (path = "/edite/{id}")
    public String editCategoryPage(@PathVariable("id") Integer id,Model model){
        Category byId = categoryService.getById(id);
        model.addAttribute("editeCategory",byId);
        List<Category> all = categoryService.getAll();
        model.addAttribute("categories",all);
        // categoryService.editeCategory(id,categoryDto);
        return "editePageCategory";
    }
   @PostMapping (path = "/edite/{id}")
    public String editCategory(@PathVariable("id") Integer id,Category category,Model model){
        category.setId(id);
        Response response=categoryService.editeCategory(category);
       List<Category> all = categoryService.getAll();
       model.addAttribute("categories",all);
       model.addAttribute("categoryDto",new CategoryDto());
       if(response.isSuccess())
           model.addAttribute("success",response.getMessage());
       else
           model.addAttribute("error",response.getMessage());
       return "categoryOperation";
   }
   @GetMapping(path = "/delete/{id}")
    public String deleteCategory(@PathVariable("id") Integer id,Model model){
       List<Category> all = categoryService.getAll();
       model.addAttribute("categories",all);
       model.addAttribute("categoryDto",new CategoryDto());
       Response response = categoryService.deleteCategory(id);
       if(response.isSuccess())
           model.addAttribute("success",response.getMessage());
       else
           model.addAttribute("error",response.getMessage());
       return "categoryOperation";
   }

}
