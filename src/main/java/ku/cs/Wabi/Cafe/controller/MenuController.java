//Patcharin Khangwicha 6410406797
package ku.cs.Wabi.Cafe.controller;

import ku.cs.Wabi.Cafe.entity.Menu;
import ku.cs.Wabi.Cafe.model.MenuRequest;
import ku.cs.Wabi.Cafe.service.CategoryService;
import ku.cs.Wabi.Cafe.service.MenuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;


@Controller
@RequestMapping("/menus")  //ทุก method จะขึ้นต้นด้วย "/menus"
public class MenuController {


    @Autowired
    private MenuService menuService;

    @Autowired
    private CategoryService categoryService;

    @GetMapping //ใช้ "/menus" เลย
    public String getAllMenus(Model model) {
        model.addAttribute("categories", categoryService.getAllCategories()); //เอาทุก category ไปใช้เพื่อเอา menu เพราะ ในแต่ละ category จะมี list menu อยู่        //เอา all menus ไปเก็บที่ menus
        return "menu-all";
    }


    @GetMapping("/{id}")
    public String getOneMenu(@PathVariable UUID id, Model model) {
        Menu menu = menuService.getOneById(id);
        model.addAttribute("menu", menu); //เอา menu ใส่ใน model เพื่อเอาไปใส่ในหน้า menu-view
        return "menu-view";
    }


    @GetMapping("/add")
    public String getMenuForm(Model model) {
        model.addAttribute("categories", categoryService.getAllCategories());
        return "menu-add";
    }

    @PostMapping("/add")
    public String createMenu(@ModelAttribute MenuRequest menu, Model model) {
        menuService.createMenu(menu);
        model.addAttribute("categories", categoryService.getAllCategories());
        return "redirect:/menus";
    }
}

