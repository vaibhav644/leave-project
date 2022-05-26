package in.leave.springbootmongodb.controller;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import in.leave.springbootmongodb.model.Employee;
import in.leave.springbootmongodb.model.LeaveTypeEnum;
import in.leave.springbootmongodb.repository.EmployeeRepository;

@Component
public class employeeHelper {

	@Autowired
	EmployeeRepository repository;

	public static String getLeaveApproval(Employee employee, LeaveRequest request) {
		String respString = "";
		//TODO: need to build logic for this method
//		int totalLeaves = employee.getEmployeeLeave();
		int leavesLeft = employee.getEmployeeRemainingLeave();
		if(request.getLeaveType().compareTo(LeaveTypeEnum.FULL_TIME)==0) {
			if(leavesLeft>=2) {
				respString = "Appproved";
				leavesLeft = leavesLeft - 2;
				employee.setEmployeeRemainingLeave(leavesLeft);
				
			}else if(leavesLeft ==1){
				respString = "Not Appproved : But you can apply for a half Day leave";
			}else {
				respString = "Not Approved : No paid leaves are available, BUT you can have a unPaid leave";
			}
		}else if(request.getLeaveType().compareTo(LeaveTypeEnum.HALF_TIME)==0) {
			if(leavesLeft>=1) {
				respString = "Appproved";
				leavesLeft = leavesLeft - 1;
			}else {
				respString = "Not Approved : No paid leaves are available, BUT you can have a unPaid leave";
			}
		}else {
			throw new RuntimeException("INVALID LEAVE TYPE");
		}
		return respString;
	}

	public static LeaveStatusResp getLeavesDetail(Optional<Employee> employeeDetail) {
		// TODO: need to write logic for this method
		LeaveStatusResp leaveStatusResp = new LeaveStatusResp();
		leaveStatusResp.setEmployeeLeave(employeeDetail.get().getEmployeeLeave());
		leaveStatusResp.setEmployeeRemainingLeave(employeeDetail.get().getEmployeeRemainingLeave());
		return leaveStatusResp;
	}
}
