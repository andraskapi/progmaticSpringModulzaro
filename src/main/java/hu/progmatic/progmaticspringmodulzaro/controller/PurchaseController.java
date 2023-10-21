package hu.progmatic.progmaticspringmodulzaro.controller;

import hu.progmatic.progmaticspringmodulzaro.model.Customer;
import hu.progmatic.progmaticspringmodulzaro.model.Purchase;
import hu.progmatic.progmaticspringmodulzaro.service.ShoppingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/purchase")
public class PurchaseController {
    private final ShoppingService shoppingService;
@Autowired
    public PurchaseController(ShoppingService shoppingService) {
        this.shoppingService = shoppingService;
    }

    @GetMapping()
    public String getAllPurchase(Model model) {
        model.addAttribute("purchases", shoppingService.getAllPurchase());
        return "purchase";
    }

    @GetMapping("/new")
    public String addPurchase(Model model) {
        model.addAttribute("newPurchase", new Purchase());
        model.addAttribute("customers",shoppingService.getAllCustomer());
        return "new-purchase";
    }

    @PostMapping("/add")
    public String addPurchase(@ModelAttribute("newPurchase") Purchase purchase) {
        shoppingService.addPurchase(purchase);
        return "redirect:/purchase";
    }

    @PostMapping("/delete/{id}")
    public String deleteCustomer(@PathVariable("id") Integer id) {
        shoppingService.deletePurchaseByCustomer(shoppingService.findCustomerById(id));
        shoppingService.deleteCustomerById(id);
        return "redirect:/home";
    }
}
