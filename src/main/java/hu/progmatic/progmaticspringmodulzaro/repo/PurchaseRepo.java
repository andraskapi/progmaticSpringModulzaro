package hu.progmatic.progmaticspringmodulzaro.repo;

import hu.progmatic.progmaticspringmodulzaro.model.Customer;
import hu.progmatic.progmaticspringmodulzaro.model.Purchase;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface PurchaseRepo extends JpaRepository<Purchase, Integer> {
    void deleteAllByBuyer(Customer customer);
    List<Purchase> getPurchasesByBuyer(Customer customer);

}
