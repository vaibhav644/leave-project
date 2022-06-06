package in.leave.springbootmongodb.model;

import org.springframework.data.annotation.Id;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Employee {

	@Id
	private String id;
	private String employeeName;
	private String employeePost;
	private ContactDetails contactDetails;
	private int employeeLeave = 40;
	private int employeeRemainingLeave;
	private LeaveTypeEnum leaveType;
	private int leaveDays;
}
