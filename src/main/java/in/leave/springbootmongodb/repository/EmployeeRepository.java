package in.leave.springbootmongodb.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import in.leave.springbootmongodb.model.Employee;

@Component

public interface EmployeeRepository extends MongoRepository<Employee, String> {

//	Employee update(Employee request);
//	Employee save(Employee student);
//
	Employee getById(String id);

}


