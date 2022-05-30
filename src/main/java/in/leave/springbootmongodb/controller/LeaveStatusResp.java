package in.leave.springbootmongodb.controller;

import lombok.Data;

@Data
public class LeaveStatusResp {
	private int employeeLeave;
	private int employeeRemainingLeave;
	public int getEmployeeLeave() {
		return employeeLeave;
	}
	public void setEmployeeLeave(int employeeLeave) {
		this.employeeLeave = employeeLeave;
	}
	public int getEmployeeRemainingLeave() {
		return employeeRemainingLeave;
	}
	public void setEmployeeRemainingLeave(int employeeRemainingLeave) {
		this.employeeRemainingLeave = employeeRemainingLeave;
	}
}
