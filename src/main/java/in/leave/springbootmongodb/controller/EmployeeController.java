package in.leave.springbootmongodb.controller;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import in.leave.springbootmongodb.model.Employee;
import in.leave.springbootmongodb.repository.EmployeeRepository;

@RestController
@Component
@RequestMapping("/Employee")
public class EmployeeController {
	@Autowired
	EmployeeRepository repository;
	@Autowired
	EmployeeHelper employeeHelper;

	@GetMapping("get/{id}")
	public Optional<Employee> getById(@PathVariable String id) {
		return repository.findById(id);
	}

	@PutMapping("/update/{id}")
	public String pop(@PathVariable String id, @RequestBody Employee newEmployeeDetails) {
		Optional<Employee> existingEmployeeDetail = repository.findById(id);
		Employee updatedEmployee = employeeHelper.updateEmployee(existingEmployeeDetail.get(), newEmployeeDetails);
		repository.save(updatedEmployee);
		return "Update Successfully";
	}
 
	@PostMapping("/save")
	public Employee create(@RequestBody Employee emp) {
		return repository.save(emp);
	}

//	@PutMapping("/update/{id}")
//	public Employee update(@PathVariable int id, @RequestBody Employee employeeObj) {
//		return repository.save(employeeObj);
//	}

	@DeleteMapping("/del/{id}")
	public String delete(@PathVariable String id) {
		Optional<Employee> employee = repository.findById(id);
		if (employee.isPresent()) {
			repository.delete(employee.get());
			return "Employee is deleted with id" + id;
		} else {
			throw new RuntimeException("Employee not found for the id" + id);
		}
	}

	/*
	 * private Optional<Employee> update(@PathVariable String id) {
	 * Optional<Employee> employee = repository.findById(id);
	 * repository.delete(employee); repository.save(updatedEmployee);
	 * 
	 * EmployeeRepository updateEmployee = updatedEmployee; employee =
	 * repository.update(updatedEmployee); return employee;
	 * 
	 * }
	 */
	@PostMapping("/getLeavesStatus")
	public LeaveStatusResp getLeavesStatus(@RequestBody String id) {
		Optional<Employee> employeeDetail = repository.findById(id);
		return employeeHelper.getLeavesDetail(employeeDetail);
	}

	@PostMapping("/getApproval")
	public String calculate(@RequestBody LeaveRequest id) {
		Employee employeeDetail = repository.getById(id.getEmployeeId());
		return employeeHelper.getLeaveApproval(employeeDetail, id);
	}
	
	@GetMapping("/register")
	public String showForm() {
		return "LeaveApplicationForm";
	}
}


