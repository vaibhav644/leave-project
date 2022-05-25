package in.leave.springbootmongodb.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import in.leave.springbootmongodb.model.Employee;
import in.leave.springbootmongodb.repository.EmpRepository;

@RestController
@RequestMapping("/Employee")
public class EmployeeController {
	@Autowired
	private EmpRepository empService;
	
	@GetMapping("/get/{id}")
	public Employee getById(@PathVariable String id) {
		return EmpRepository.getById(id);
	}
	@PostMapping("/")
	public Employee create(@RequestBody Employee employee) {
		boolean isValidRequest = true;
		
		return EmpRepository.save() ;
	
	}
	

}