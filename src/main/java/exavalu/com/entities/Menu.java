package exavalu.com.entities;

import java.io.Serializable;

public class Menu implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private int menuId; 
	private String menuName; 
	private String menuLink;
	
	
	public int getMenuId() {
		return menuId;
	}
	public void setMenuId(int menuId) {
		this.menuId = menuId;
	}
	public String getMenuName() {
		return menuName;
	}
	public void setMenuName(String menuName) {
		this.menuName = menuName;
	}
	public String getMenuLink() {
		return menuLink;
	}
	public void setMenuLink(String menuLink) {
		this.menuLink = menuLink;
	} 

}
