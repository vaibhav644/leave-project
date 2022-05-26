package in.leave.springbootmongodb.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import in.leave.springbootmongodb.model.Employee;

@Component
@Repository
public interface EmployeeRepository extends MongoRepository<Employee, String> {
//	public Employee findById();

	//public static Employee findById(String id) {

		//return null;
	}


