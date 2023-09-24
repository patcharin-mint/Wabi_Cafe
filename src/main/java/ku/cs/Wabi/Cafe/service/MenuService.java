//Patcharin Khangwicha 6410406797
package ku.cs.Wabi.Cafe.service;

import ku.cs.Wabi.Cafe.entity.Category;
import ku.cs.Wabi.Cafe.entity.Menu;
import ku.cs.Wabi.Cafe.model.MenuRequest;
import ku.cs.Wabi.Cafe.repository.CategoryRepository;
import ku.cs.Wabi.Cafe.repository.MenuRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import java.util.List;
import java.util.UUID;


@Service
public class MenuService {


    @Autowired
    private MenuRepository menuRepository;

    @Autowired
    private CategoryRepository categoryRepository;

    @Autowired
    private ModelMapper modelMapper;


    public List<Menu> getAllMenus() {
        return menuRepository.findAll();
    }


    public Menu getOneById(UUID id) { //กดปุ่ม view
        return menuRepository.findById(id).get();
    }


    public void createMenu(MenuRequest menu) {
        Menu record = modelMapper.map(menu, Menu.class); //map จาก MenuRequest เป็น Menu
        Category category =
                categoryRepository.findById(menu.getCategoryId()).get(); //หา category หาจาก categoryId
        record.setCategory(category);
        menuRepository.save(record);
    }
}
