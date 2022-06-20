package in.leave.springbootmongodb.controller;

import java.util.Optional;
import java.util.regex.Pattern;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import in.leave.springbootmongodb.model.Employee;
import in.leave.springbootmongodb.model.LeaveTypeEnum;
import in.leave.springbootmongodb.repository.EmployeeRepository;

@Component
public class EmployeeHelper {

	@Autowired
	private EmployeeRepository repository;

	public String getLeaveApproval(Employee employee, ApplyLeaveRequest request) {
		String respString = "";
		// TODO: need to build logic for this method
		// int totalLeaves = employee.getEmployeeLeave();
		int leavesLeft = employee.getEmployeeRemainingLeave();
		if (request.getLeaveType().compareTo(LeaveTypeEnum.FULL_TIME) == 0) {
			if (leavesLeft >= 2) {
				respString = "Appproved";
				leavesLeft = leavesLeft - (2 * request.getLeaveDays());
				employee.setEmployeeRemainingLeave(leavesLeft);
				repository.save(updateEmployee(repository.getById(employee.getId()), employee));
				// Employee test = repository.getById(employee.getId());
				// int x = test.getEmployeeRemainingLeave();

			} else if (leavesLeft == 1) {
				respString = "Not Appproved : But you can apply for a half Day leave";
			} else {
				respString = "Not Approved : No paid leaves are available, BUT you can have a unPaid leave";
			}

		} else if (request.getLeaveType().compareTo(LeaveTypeEnum.HALF_TIME) == 0) {
			if (leavesLeft >= 1) {
				respString = "Appproved";
				leavesLeft = leavesLeft - (1 * request.getLeaveDays());
				employee.setEmployeeRemainingLeave(leavesLeft);
				repository.save(updateEmployee(repository.getById(employee.getId()), employee));
				// Employee test = repository.getById(employee.getId());
				// int Y = test.getEmployeeRemainingLeave();
				leavesLeft = leavesLeft - 1;
			} else {
				respString = "Not Approved : No paid leaves are available, BUT you can have a unPaid leave";
			}
		} else {
			throw new RuntimeException("INVALID LEAVE TYPE");
		}
		return respString;
	}

	public Employee updateEmployee(Employee existingEmployeeDetail, Employee newEmployeeDetail) {
		existingEmployeeDetail.setEmployeeName(newEmployeeDetail.getEmployeeName());
		existingEmployeeDetail.setContactDetails(newEmployeeDetail.getContactDetails());
		existingEmployeeDetail.setEmployeePost(newEmployeeDetail.getEmployeePost());
		existingEmployeeDetail.setLeaveType(newEmployeeDetail.getLeaveType());
		existingEmployeeDetail.setEmployeeRemainingLeave(newEmployeeDetail.getEmployeeRemainingLeave());
		return existingEmployeeDetail;
	}

	public LeaveStatusResp getLeavesDetail(Optional<Employee> employeeDetail) {
		// TODO: need to write logic for this method
		LeaveStatusResp leaveStatusResp = new LeaveStatusResp();
		leaveStatusResp.setEmployeeLeave(employeeDetail.get().getEmployeeLeave());
		leaveStatusResp.setEmployeeRemainingLeave(employeeDetail.get().getEmployeeRemainingLeave());
		return leaveStatusResp;
	}

	// TODO: validate request params
	public ValidateParamResp validateParams(Employee employee) {
		ValidateParamResp resp = new ValidateParamResp();
		if (employee.getEmployeeName() == null || !matchesRegex(employee.getEmployeeName(), "^[A-Za-z]{1,29}$")) {
			resp.setValid(false);
			resp.setMessage("Name is invalid");
		} else if (employee.getId() == null || !matchesRegex(employee.getId(), "^[\\d*]{1,10}$")) {
			resp.setValid(false);
			resp.setMessage("ID is invalid");
		} else if (employee.getEmployeePost() == null
				|| !matchesRegex(employee.getEmployeePost(), "^[A-Za-z]{1,10}$")) {
			resp.setValid(false);
			resp.setMessage("Post is invalid");

		} else if (employee.getContactDetails().getEmailId() == null
				|| !matchesRegex(employee.getContactDetails().getEmailId(),
						"^(?=.{1,64}@)[A-Za-z0-9+_-]+(.[A-Za-z0-9+_-]+)*@"
								+ "[^-][A-Za-z0-9\\+-]+(\\.[A-Za-z0-9\\+-]+)*([A-Za-z]{2,})$")) {
			resp.setValid(false);
			resp.setMessage("Email is invalid");
		} else if (employee.getContactDetails().getMobileNo() == null
				|| !matchesRegex(employee.getContactDetails().getMobileNo(), "^\\d{10}$")) {
			resp.setValid(false);
			resp.setMessage("Mobile No. is invalid");
		} else {
			resp.setValid(true);
		}
		return resp;
	}

	public boolean matchesRegex(String s, String myregex) {
		// TODO:

		return Pattern.compile(myregex).matcher(s).matches();
//		 Pattern p = Pattern.compile(myregex);
//		 java.util.regex.Matcher m = p.matcher(s);
//		 return m.matches();
//		  boolean a = Pattern.compile(myregex).matcher(s).matches();
//		  return a;
	}

}
