package in.leave.springbootmongodb.controller;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import in.leave.springbootmongodb.model.Employee;
import in.leave.springbootmongodb.model.LeaveTypeEnum;
import in.leave.springbootmongodb.repository.EmployeeRepository;

@RestController
@RequestMapping("/Employee")
public class EmployeeController {
	@Autowired
	EmployeeRepository repository;

	@GetMapping("get/{id}")
	public Optional<Employee> getById(@PathVariable String id) {
		return repository.findById(id);
	}

	@PostMapping("/")
	public Employee create(@RequestBody Employee request) {
		return repository.save(request);
	}
	
	
	
	
	@PostMapping("/getLeavesStatus")
	public LeaveStatusResp getLeavesStatus(@RequestBody String id) {
		Optional<Employee> employeeDetail = repository.findById(id);
		return employeeHelper.getLeavesDetail(employeeDetail);
	}
	
	

	@PostMapping("/getApproval")
	public String calculate(@RequestBody LeaveRequest request) {
		Optional<Employee> employeeDetail = repository.findById(request.getEmployeeId());
		return employeeHelper.getLeaveApproval(employeeDetail.get(), request);
	}
}
