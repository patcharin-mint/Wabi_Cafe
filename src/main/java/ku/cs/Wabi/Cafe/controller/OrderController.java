//Patcharin Khangwicha 6410406797
package ku.cs.Wabi.Cafe.controller;

import ku.cs.Wabi.Cafe.model.AddCartRequest;
import ku.cs.Wabi.Cafe.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;


import java.util.UUID;


@Controller
@RequestMapping("/orders")
public class OrderController {


    @Autowired
    private OrderService orderService;


    @PostMapping("/{menuId}")
    public String order(@PathVariable UUID menuId,
                        @ModelAttribute AddCartRequest request, Model model){
        orderService.order(menuId, request);
        return "redirect:/menus";
    }

    @GetMapping
    public String viewCart(Model model) {
        model.addAttribute("cart", orderService.getCurrentOrder());
        return "cart";
    }


    @PostMapping
    public String submitOrder(Model model) {
        orderService.submitOrder();
        model.addAttribute("confirmOrder", true);
        return "home";
    }


}
