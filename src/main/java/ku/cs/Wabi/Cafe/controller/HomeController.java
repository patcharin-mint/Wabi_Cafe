//Patcharin Khangwicha 6410406797
package ku.cs.Wabi.Cafe.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class HomeController {

    @RequestMapping("/") //ทุกREST functionจะมาที่นี่
    public String getHomePage(Model model) {
        model.addAttribute("greeting", "Sawaddee krab :)"); //เอาตัวแปร greeting ส่งไปให้ไฟล์ home.html ผ่าน class Model
        // ต้องคืนค่าเป็นชื่อไฟล์ html template โดยในเมธอดนี้ คืนค่าเป็น home.html = String
        return "home";
    }
}

