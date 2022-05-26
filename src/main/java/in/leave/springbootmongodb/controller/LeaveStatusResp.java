package in.leave.springbootmongodb.controller;

import lombok.Data;

@Data
public class LeaveStatusResp {
	private int employeeLeave;
	private int employeeRemainingLeave;
}
