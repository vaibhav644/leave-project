package in.leave.springbootmongodb.controller;

import in.leave.springbootmongodb.model.LeaveTypeEnum;
import lombok.Data;

@Data
public class LeaveRequest {
	private String employeeId;
	private LeaveTypeEnum leaveType;
	private int totalNoOfLeaves;
	public int getTotalNoOfLeaves() {
		return totalNoOfLeaves;
	}
	public void setTotalNoOfLeaves(int totalNoOfLeaves) {
		this.totalNoOfLeaves = totalNoOfLeaves;
	}
	public String getEmployeeId() {
		return employeeId;
	}
	public void setEmployeeId(String employeeId) {
		this.employeeId = employeeId;
	}
	public LeaveTypeEnum getLeaveType() {
		return leaveType;
	}
	public void setLeaveType(LeaveTypeEnum leaveType) {
		this.leaveType = leaveType;
	}
}
