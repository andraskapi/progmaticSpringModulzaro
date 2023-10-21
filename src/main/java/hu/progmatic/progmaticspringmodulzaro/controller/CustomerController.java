package hu.progmatic.progmaticspringmodulzaro.controller;

import hu.progmatic.progmaticspringmodulzaro.model.Customer;
import hu.progmatic.progmaticspringmodulzaro.model.Purchase;
import hu.progmatic.progmaticspringmodulzaro.service.ShoppingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/customer")
public class CustomerController {
    private final ShoppingService shoppingService;

    @Autowired
    public CustomerController(ShoppingService shoppingService) {
        this.shoppingService = shoppingService;
    }

    @GetMapping()
    public String getAllCustomer(Model model) {
        model.addAttribute("customers", shoppingService.getAllCustomer());
        return "customer";
    }

    @GetMapping({"/{id}"})
    public String getCustomerById(Model model, @PathVariable("id") Integer id) {
        model.addAttribute("buyer", shoppingService.findCustomerById(id));
        return "show-customer";
    }

    @GetMapping("/new")
    public String createCustomer(Model model) {
        model.addAttribute("buyer", new Customer());
        return "new-customer";
    }

    @PostMapping("/add")
    public String createPerson(@ModelAttribute("buyer") Customer customer) {
        shoppingService.addCustomer(customer);
        return "redirect:/customer";
    }

    @GetMapping("/update/{id}")
    public String updateCustomer(Model model, @PathVariable("id") Integer id) {
        model.addAttribute("buyer", shoppingService.findCustomerById(id));
        return "update-customer";
    }

    @PostMapping("/update/{id}")
    public String updateCustomer(@PathVariable("id") Integer id, @ModelAttribute("buyer") Customer updated) {
        shoppingService.addCustomer(updated);
        return "redirect:/customer/" + id;
    }

    @GetMapping("/highest-spender")
    public String findHighestPurchase(Model model) {
        Customer customer = shoppingService.getMostExpensesPerson();
        model.addAttribute("customer", customer);
        model.addAttribute("purchase", shoppingService.getAllPurchaseByCustomer(customer));
        return "most-spent";
    }

    @GetMapping("/spent/{id}")
    public String spentByCustomer(@PathVariable("id") Integer id, Model model){
        Customer customer = shoppingService.findCustomerById(id);

        List<Purchase> purchases = shoppingService.getAllPurchaseByCustomer(customer);
        model.addAttribute("buyer", customer);
        model.addAttribute("purchaseList", purchases);
        return "spent-money";
    }






}








