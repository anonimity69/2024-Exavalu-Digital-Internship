package exavalu.com.entities;

import java.io.Serializable;
import java.security.KeyStore.PrivateKeyEntry;

public class Employee implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String employeeCode;   
	private String firstName;  
	private String lastName;  
	private int status;  
	private int employeeRoleId;  
	private String employeeRole;
	private int practiceId;  
	private String dateofJoin;  
	private double salary;
	private String practiceName;
	
	
	public String getEmployeeCode() {
		return employeeCode;
	}
	public void setEmployeeCode(String employeeCode) {
		this.employeeCode = employeeCode;
	}
	public String getFirstName() {
		return firstName;
	}
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	public String getLastName() {
		return lastName;
	}
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	public int getStatus() {
		return status;
	}
	public void setStatus(int status) {
		this.status = status;
	}
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
	public int getPracticeId() {
		return practiceId;
	}
	public void setPracticeId(int practiceId) {
		this.practiceId = practiceId;
	}
	public String getDateofJoin() {
		return dateofJoin;
	}
	public void setDateofJoin(String dateofJoin) {
		this.dateofJoin = dateofJoin;
	}
	public double getSalary() {
		return salary;
	}
	public void setSalary(double salary) {
		this.salary = salary;
	}
	public String getPracticeName() {
		return practiceName;
	}
	public void setPracticeName(String practiceName) {
		this.practiceName = practiceName;
	}
	
	

}
