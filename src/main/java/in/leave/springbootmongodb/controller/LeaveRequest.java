package in.leave.springbootmongodb.controller;

import in.leave.springbootmongodb.model.LeaveTypeEnum;
import lombok.Data;

@Data
public class LeaveRequest {
	private String employeeId;
	private LeaveTypeEnum leaveType;
	private int totalNoOfLeaves;
	}

	