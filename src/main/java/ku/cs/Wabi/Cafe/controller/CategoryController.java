//Patcharin Khangwicha 6410406797
package ku.cs.Wabi.Cafe.controller;

import ku.cs.Wabi.Cafe.model.CategoryRequest;
import ku.cs.Wabi.Cafe.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;


@Controller
@RequestMapping("/categories")
public class CategoryController {


    @Autowired
    private CategoryService categoryService;

    @GetMapping("/add")
    public String getCategoryForm(Model model) {
        return "category-add";
    }


    @PostMapping("/add")
    public String createCategory(@ModelAttribute CategoryRequest category,
                                 Model model) {
        categoryService.createCategory(category);
        model.addAttribute("categories", categoryService.getAllCategories()); //เอา categories ไปใช้ใน html
        return "redirect:/menus";
    }
}

