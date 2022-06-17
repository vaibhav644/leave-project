package in.leave.springbootmongodb.controller;

import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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

	private static final Logger logger = LoggerFactory.getLogger(EmployeeController.class);

	/********************** CRUD APIs *****************************/
	@PostMapping("/save")
	public Employee create(@RequestBody Employee emp) {
		emp.setEmployeeRemainingLeave(40);
		if(employeeHelper.validateParams(emp)) {
			return repository.save(emp);
		}else {
			throw new RuntimeException("asdfghjkl");
		}
		
	}

	@GetMapping("get/{id}")
	public Optional<Employee> getById(@PathVariable String id) {
		return repository.findById(id);
	}

	@GetMapping("/findAll")
//	@Cacheable(key = "id")
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

	// this api is rendering the submit form UI
	@GetMapping("/submit")
	public ModelAndView findById(Model model) {
		ModelAndView modelAndView = new ModelAndView("submit.html");
		Employee emp = new Employee();
		modelAndView.getModel().put("employee", emp);
		return modelAndView;
	}
	@PostMapping("/save/viaSubmitForm")
	public ModelAndView saveViaSubmitPage(@ModelAttribute("employee") Employee employee, Model model) {
		employee.setEmployeeRemainingLeave(40);
		if(employeeHelper.validateParams(employee)) {
		repository.save(employee);
		ModelAndView modelAndView = new ModelAndView("xyz.html"); // succesfully submitted
		return modelAndView;
		}else {
			throw new RuntimeException("asdfghjkl");
		}
	}

	@GetMapping("/leaves")
	public ModelAndView leaves(Model model) {
		ModelAndView modelAndView = new ModelAndView("leave.html");
		ApplyLeaveRequest applyLeaveRequest = new ApplyLeaveRequest();
		modelAndView.getModel().put("applyLeaveRequest", applyLeaveRequest);
		return modelAndView;
	}

	@PostMapping("/save/display")
	public ModelAndView showUserList(@ModelAttribute("employee") Employee employee, Model model) {
		employee.setEmployeeRemainingLeave(40);
		if(employee== null || employee.getContactDetails() == null || employee.getEmployeeName() == null || employee.getEmployeePost() == null) {
			throw new RuntimeException("Some parameters are missing");
		}
		repository.save(employee);
		ModelAndView modelAndView = new ModelAndView("submit.html");
		modelAndView.getModel().put("employees", repository.findAll());
		return modelAndView;

	}
	@GetMapping("/display")
	public ModelAndView showUserList() {
		ModelAndView modelAndView = new ModelAndView("main.html");
		modelAndView.getModel().put("employees", repository.findAll());
		return modelAndView;
	}

	@GetMapping("/main")
	public ModelAndView main(Model model) {
		ModelAndView modelAndView = new ModelAndView("main.html");
			
		return modelAndView;
	}

	@PostMapping("/getLeavesStatus")
	public LeaveStatusResp getLeavesStatus(@ModelAttribute("applyLeaveRequest") ApplyLeaveRequest request,
			Model model) {
		Optional<Employee> employeeDetail = repository.findById(request.getId());
		System.out.println();
		return employeeHelper.getLeavesDetail(employeeDetail);
	}

	@PostMapping("/getApproval")
	public String calculate(@ModelAttribute("applyLeaveRequest") ApplyLeaveRequest request, Model model) {
 		Employee employeeDetail = repository.getById(request.getId());
		if (employeeDetail == null) {
			throw new RuntimeException(String.format("ID = %s not found in Database", request.getId()));
		}
		return employeeHelper.getLeaveApproval(employeeDetail, request);
	}
	
}


