package uz.pdp.libraryweb.copyTest.service;
import org.springframework.stereotype.Service;
import uz.pdp.libraryweb.copyTest.dto.CategoryDto;
import uz.pdp.libraryweb.copyTest.dto.Response;
import uz.pdp.libraryweb.copyTest.entity.Category;
import uz.pdp.libraryweb.copyTest.repository.CategoryRepository;

import java.util.List;
import java.util.Optional;

@Service
public class CategoryService {
    final CategoryRepository categoryRepository;

    public CategoryService(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }

    public List<Category> getAll() {
        return categoryRepository.findAll();
    }

    public Category getById(Integer id) {
        Optional<Category> byId = categoryRepository.findById(id);
        return byId.orElse(new Category());
    }

    public Response addCategory(CategoryDto categoryDto) {
        Response response=new Response();
        for (Category category : categoryRepository.findAll()) {
            if(category.getName().toLowerCase().equals(categoryDto.getName().toLowerCase())) {
                response.setMessage("This "+categoryDto.getName()+" name already exist");
                return response;
            }
        }
        categoryRepository.save(new Category(categoryDto.getName()));
        response.setMessage("Add category "+categoryDto.getName());
        response.setSuccess(true);
        return response;
    }

    public Response editeCategory(Category category) {
        Response response=new Response();
            for (Category category1 : categoryRepository.findAll()) {
                if(category1.getName().toLowerCase().equals(category.getName().toLowerCase())&&!category.getId().equals(category1.getId())) {
                    response.setMessage("This "+category.getName()+" name already exist");
                    return response;
                }
            }
            categoryRepository.save(category);
            response.setSuccess(true);
            response.setMessage("Edite category");
            return response;
    }

    public Response deleteCategory(Integer id) {
        Response response=new Response();
        Optional<Category> byId = categoryRepository.findById(id);
        if(byId.isPresent()){
            Category category = byId.get();
            category.setActive(false);
            categoryRepository.save(category);
            response.setMessage("Delete category");
            response.setSuccess(true);
            return response;
        }
        response.setMessage("This id doesn't exist");
        return response;
    }

    public List<Category> findAllId(List<Integer> categoryList) {
        return categoryRepository.findAllById(categoryList);
    }
}
