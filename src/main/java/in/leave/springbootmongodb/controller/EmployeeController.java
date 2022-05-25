package in.leave.springbootmongodb.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import in.leave.springbootmongodb.model.Employee;

@RestController
@RequestMapping("/Employee")
public class EmployeeController {
	@GetMapping("/get")
	public Employee getById(@PathVariable String id) {
		return null;
	}

}
