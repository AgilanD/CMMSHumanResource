package cmms.humanresource.repository;

import cmms.humanresource.entity.Employee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface EmployeeRepository extends JpaRepository<Employee,Long>{

    @Query(value = "SELECT * FROM employees WHERE is_deleted = true", nativeQuery = true)
    List<Employee> findAllDeletedEmployees();

}
