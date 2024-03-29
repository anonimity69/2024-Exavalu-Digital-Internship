package exavalu.com.entities;

import java.io.Serializable;

public class User implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String emailAddress;
    private String password;
    private int status;
    private int roleId;
    private int serialNumber;
    private String firstName;
    private String lastName;
    private String imagePath;
    private boolean loggedIn;
    
    
	public String getEmailAddress() {
		return emailAddress;
	}
	public void setEmailAddress(String emailAddress) {
		this.emailAddress = emailAddress;
	}
	
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	
	public int getStatus() {
		return status;
	}
	public void setStatus(int status) {
		this.status = status;
	}
	
	public int getRoleId() {
		return roleId;
	}
	public void setRoleId(int roleId) {
		this.roleId = roleId;
	}
	
	public int getSerialNumber() {
		return serialNumber;
	}
	public void setSerialNumber(int serialNumber) {
		this.serialNumber = serialNumber;
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
    
	public boolean isLoggedIn() {
		return loggedIn;
	}
	public void setLoggedIn(boolean loggedIn) {
		this.loggedIn = loggedIn;
	}
	public String getImagePath() {
		return imagePath;
	}
	public void setImagePath(String imagePath) {
		this.imagePath = imagePath;
	}
    
    
}
