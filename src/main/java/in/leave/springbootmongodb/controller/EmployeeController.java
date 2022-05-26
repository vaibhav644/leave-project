package in.leave.springbootmongodb.controller;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
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

	@PostMapping("/save")
	public Employee create(@RequestBody Employee request) {
		return repository.save(request);
	}

	@PutMapping("/update/{id}")
	public Employee update(@PathVariable int id, @RequestBody Employee employeeObj) {
		return repository.save(employeeObj);
	}

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
	public String calculate(@RequestBody LeaveRequest request) {
		Employee employeeDetail = repository.getById(request.getEmployeeId());
		return employeeHelper.getLeaveApproval(employeeDetail, request);
	}
}
