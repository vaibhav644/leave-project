package in.leave.springbootmongodb.controller;

import java.util.Optional;

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
//		int totalLeaves = employee.getEmployeeLeave();
		int leavesLeft = employee.getEmployeeRemainingLeave();
		if (request.getLeaveType().compareTo(LeaveTypeEnum.FULL_TIME) == 0) {
			if (leavesLeft >= 2) {
				respString = String.format("leave approved for the employee with ID = %s with leaveleft %s",request.getId(), leavesLeft);
				leavesLeft = leavesLeft - (2*request.getLeaveDays());
				if(leavesLeft<0) {
					return "not Approved";
				}
				employee.setEmployeeRemainingLeave(leavesLeft);
				repository.save(updateEmployee(repository.getById(employee.getId()), employee));
				Employee test = repository.getById(employee.getId());
				int x = test.getEmployeeRemainingLeave();

			} else if (leavesLeft == 1) {
				respString = "Not Appproved : But you can apply for a half Day leave";
			} else {
				respString = "Not Approved : No paid leaves are available, BUT you can have a unPaid leave";
			}

		} else if (request.getLeaveType().compareTo(LeaveTypeEnum.HALF_TIME) == 0) {
			if (leavesLeft >= 1) {
				leavesLeft = leavesLeft - (1*request.getLeaveDays());
				employee.setEmployeeRemainingLeave(leavesLeft);
				respString = String.format("leave approved for the employee with ID = %s with leaveleft %s",request.getId(), leavesLeft);
				repository.save(updateEmployee(repository.getById(employee.getId()), employee));
				Employee test = repository.getById(employee.getId());
				int Y = test.getEmployeeRemainingLeave();
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
	public boolean validateParams(Employee employee) {
		if(matchesRegex(employee.getEmployeeName(), "^[A-Za-z]\\\\w{5,29}$")){
			if(matchesRegex(employee.getContactDetails().getMobileNo(), "123456789")) {
//				if() {
//					if() {
//						
//					}
//				}
			}
		}
		return false;
	}
	
	public boolean matchesRegex(String s, String regex) {
		// TODO:  
		
		return true;
	}
}
