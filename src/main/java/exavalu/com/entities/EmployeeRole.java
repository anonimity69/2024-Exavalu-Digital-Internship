package exavalu.com.entities;

import java.io.Serializable;

public class EmployeeRole implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private int employeeRoleId;  
	private String employeeRole;
	
	
	
	
	public int getEmployeeRoleId() {
		return employeeRoleId;
	}
	public void setEmployeeRoleId(int employeeRoleId) {
		this.employeeRoleId = employeeRoleId;
	}
	public String getEmployeeRole() {
		return employeeRole;
	}
	public void setEmployeeRole(String employeeRole) {
		this.employeeRole = employeeRole;
	}
	
	

}
