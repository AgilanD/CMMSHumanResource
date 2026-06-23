package cmms.HumanResource.repository;

import cmms.HumanResource.entity.Customers;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CustomerRepository extends JpaRepository<Customers,Long> {

}
