package hu.progmatic.progmaticspringmodulzaro.service;

import hu.progmatic.progmaticspringmodulzaro.model.Customer;
import hu.progmatic.progmaticspringmodulzaro.model.Purchase;
import hu.progmatic.progmaticspringmodulzaro.repo.CustomerRepo;
import hu.progmatic.progmaticspringmodulzaro.repo.PurchaseRepo;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class ShoppingService {
    private final CustomerRepo customerRepo;
    private final PurchaseRepo purchaseRepo;
@Autowired
    public ShoppingService(CustomerRepo customerRepo, PurchaseRepo purchaseRepo) {
        this.customerRepo = customerRepo;
        this.purchaseRepo = purchaseRepo;
    }

    public List<Customer> getAllCustomer(){
        return customerRepo.findAll();
    }
    public List<Purchase> getAllPurchase(){
        return purchaseRepo.findAll();
    }
    public Customer findCustomerById(Integer id){
        return customerRepo.findById(id).orElse(null);
    }

    public List<Purchase> getAllPurchaseByCustomer(Customer customer){
    return purchaseRepo.getPurchasesByBuyer(customer);
    }
    public void addCustomer(Customer customer){
        customerRepo.save(customer);
    }

    public void updateCustomer(Customer customer){
        customerRepo.save(customer);
    }

    public void addPurchase(Purchase purchase){
        purchaseRepo.save(purchase);
    }

    @Transactional
    public void deleteCustomerById(Integer id) {
        customerRepo.deleteById(id);
    }

    @Transactional
    public void deletePurchaseByCustomer(Customer customer) {
        purchaseRepo.deleteAllByBuyer(customer);
    }

    public Customer getMostExpensesPerson() {
        Map<Customer, Integer> customerPurchase = fillCustomersMap();
        return findMostPurchaseFromCustomerPurchaseMap(customerPurchase);
    }

    public Customer findMostPurchaseFromCustomerPurchaseMap(Map<Customer, Integer> customerPurchase){
        int max = 0;
        for (var actual: customerPurchase.entrySet()) {
            if(actual.getValue() > max){
                max = actual.getValue();
            }
        }
        for (var actual: customerPurchase.entrySet()) {
            if(actual.getValue() == max){
                return actual.getKey();
            }
        }
        return null;
    }

    private Map<Customer, Integer> fillCustomersMap() {
        Map<Customer, Integer> customerPurchase = new HashMap<>();
        uploadCustomerMapWithCustomer(customerPurchase);
        uploadCustomerMapWithPurchase(customerPurchase);
        return customerPurchase;
    }

    private void uploadCustomerMapWithCustomer(Map<Customer, Integer> customerPurchase) {
        List<Customer> customers = getAllCustomer();
        for (Customer actual : customers) {
            customerPurchase.put(actual, 0);
        }
    }

    private void uploadCustomerMapWithPurchase(Map<Customer, Integer> customerPurchase) {
        List<Purchase> purchases = getAllPurchase();
        for (Purchase actual : purchases) {
            Customer actualCustomer = actual.getBuyer();
            customerPurchase.put(actualCustomer, customerPurchase.get(actualCustomer) + actual.getAmount());
        }
    }




}


