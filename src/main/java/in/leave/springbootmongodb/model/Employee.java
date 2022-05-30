package in.leave.springbootmongodb.model;

import org.springframework.data.annotation.Id;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
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
<<<<<<< HEAD
	
=======
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getEmployeeName() {
		return employeeName;
	}
	public void setEmployeeName(String employeeName) {
		this.employeeName = employeeName;
	}
	public String getEmployeePost() {
		return employeePost;
	}
	public void setEmployeePost(String employeePost) {
		this.employeePost = employeePost;
	}
	public ContactDetails getContactDetails() {
		return contactDetails;
	}
	public void setContactDetails(ContactDetails contactDetails) {
		this.contactDetails = contactDetails;
	}
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
	public LeaveTypeEnum getLeaveType() {
		return leaveType;
	}
	public void setLeaveType(LeaveTypeEnum leaveType) {
		this.leaveType = leaveType;
	}
>>>>>>> ae8b8e7bf8e8f6ab1f677e98f466a72f9c281a70

}
