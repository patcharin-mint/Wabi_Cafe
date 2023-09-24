//Patcharin Khangwicha 6410406797
package ku.cs.Wabi.Cafe.controller;

import ku.cs.Wabi.Cafe.model.SignupRequest;
import ku.cs.Wabi.Cafe.service.SignupService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class SignupController {


    @Autowired
    private SignupService signupService;


    @GetMapping("/signup")
    public String getSignupPage() {
        return "signup"; // return ชื่อ template >>> จะได้หน้า signup ออกมา
    }


    @PostMapping("/signup")
    public String signupUser(@ModelAttribute SignupRequest user, Model model) { //รับ DTO เข้ามา (SignupRequest)


        if (signupService.isUsernameAvailable(user.getUsername())) { //username นี้ใช้ได้มั้ย
            signupService.createUser(user);
            model.addAttribute("signupSuccess", true);
        } else {
            model.addAttribute("signupError", "Username not available");
        }
        // return หน้าฟอร์ม signup.html เช่นกัน แต่จะมี message ปรากฎ
        return "signup";
    }
}

