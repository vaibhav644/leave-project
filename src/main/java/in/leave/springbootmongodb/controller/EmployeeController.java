package in.leave.springbootmongodb.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

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

	/********************** CRUD APIs *****************************/
	@PostMapping("/save")
	public Employee create(@RequestBody Employee emp) {
		return repository.save(emp);
	}

	@GetMapping("get/{id}")
	public Optional<Employee> getById(@PathVariable String id) {
		return repository.findById(id);
	}

	@GetMapping("/findAll")
	public List<Employee> findAll() {
		return repository.findAll();
	}

	@PutMapping("/update/{id}")
	public String pop(@PathVariable String id, @RequestBody Employee newEmployeeDetails) {
		Optional<Employee> existingEmployeeDetail = repository.findById(id);
		Employee updatedEmployee = employeeHelper.updateEmployee(existingEmployeeDetail.get(), newEmployeeDetails);
		repository.save(updatedEmployee);
		return "Update Successfully";
	}

	@DeleteMapping("/{id}")
	public String delete(@PathVariable String id) {
		Optional<Employee> employee = repository.findById(id);
		if (employee.isPresent()) {
			repository.delete(employee.get());
			return "Employee is deleted with id" + id;
		} else {
			throw new RuntimeException("Employee not found for the id" + id);
		}
	}

	@DeleteMapping("/many")
	public String deleteMany(@RequestBody List<Employee> list) {
		for (Employee listElem : list) {
			repository.delete(listElem);
		}
		return "Deletion Successful";
	}

	/************************** Other APIs*****************************8 */

	// this api is redering the submit form UI
	@GetMapping("/submit")
	public ModelAndView findById(Model model) {
		ModelAndView modelAndView = new ModelAndView("submit.html");
		Employee emp = new Employee();
		modelAndView.getModel().put("employee", emp);
		return modelAndView;
	}

	@PostMapping("/display")
	public ModelAndView showUserList(@ModelAttribute("employee") Employee employee, Model model) {
		repository.save(employee);
		ModelAndView modelAndView = new ModelAndView("displayList.html");
		modelAndView.getModel().put("employees", repository.findAll());
		return modelAndView;

	}

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

}
