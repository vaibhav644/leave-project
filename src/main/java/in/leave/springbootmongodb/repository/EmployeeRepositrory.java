package in.leave.springbootmongodb.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Component;

import in.leave.springbootmongodb.model.Employee;

@Component

public interface EmployeeRepositrory extends MongoRepository<Employee, String> {
	public Employee findById();

	public static Employee getById(String id) {

		return null;
	}

}
