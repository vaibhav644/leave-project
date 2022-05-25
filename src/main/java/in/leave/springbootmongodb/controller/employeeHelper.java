package in.leave.springbootmongodb.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import in.leave.springbootmongodb.repository.EmployeeRepositrory;

@Component
public class employeeHelper {
	@Autowired
	EmployeeRepositrory reposiotory;

	public static String calculateLeave(String id) {
		
		return null;
	}
}

	
