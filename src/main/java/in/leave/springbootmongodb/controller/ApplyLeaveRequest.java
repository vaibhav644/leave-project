package in.leave.springbootmongodb.controller;

import in.leave.springbootmongodb.model.LeaveTypeEnum;
import lombok.Data;

@Data
public class ApplyLeaveRequest {
	private String id;
	private LeaveTypeEnum leaveType;
	private int leaveDays;
}
