package hu.progmatic.progmaticspringmodulzaro.controller;

import hu.progmatic.progmaticspringmodulzaro.service.ShoppingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;


@Controller
public class PageController {
    private final ShoppingService shoppingService;
@Autowired
    public PageController(ShoppingService shoppingService) {
        this.shoppingService = shoppingService;
    }

    @GetMapping({"","/","/home"})
    public String getHome(Model model){
    model.addAttribute("customers",shoppingService.getAllCustomer());
    return "home";
    }
}
