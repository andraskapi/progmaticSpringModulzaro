package hu.progmatic.progmaticspringmodulzaro.repo;

import hu.progmatic.progmaticspringmodulzaro.model.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CustomerRepo extends JpaRepository<Customer, Integer> {
}
