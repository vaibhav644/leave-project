package in.leave.springbootmongodb.controller;

import lombok.Data;

@Data
public class ValidateParamResp {
	private boolean isValid;
	private String message;

}
