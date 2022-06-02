package in.leave.springbootmongodb.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Component;

import in.leave.springbootmongodb.model.Employee;

@Component

public interface EmployeeRepository extends MongoRepository<Employee, String> {


	Employee getById(String id);


	

}


