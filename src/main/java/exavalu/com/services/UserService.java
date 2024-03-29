package exavalu.com.services;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import exavalu.com.entities.Employee;
import exavalu.com.entities.Menu;
import exavalu.com.entities.User;
import com.exavalu.pojos.PropertyValues;
import exavalu.com.utilities.DbConnectionProvider;

public class UserService {

	public static User validateUser(String emailAddress, String password, PropertyValues propertyValues) {

		// Now we need to connect to DB for validation
		DbConnectionProvider dbConnectionProvider = DbConnectionProvider.getInstance();
		Connection con = dbConnectionProvider.getDbConnection(propertyValues);

		// handle if con is null
		String sql = "SELECT emailAddress, password, status, roleid, serialNumber, firstname, lastname, imagePath FROM users "
				+ " where emailAddress=? and password=? and status = 1";
		User user = new User();
		try {
			PreparedStatement ps = con.prepareStatement(sql);
			ps.setString(1, emailAddress);
			ps.setString(2, password);
			System.out.println("SQL =" + ps);
			ResultSet rs = ps.executeQuery();

			if (rs.next()) {
				user.setEmailAddress(rs.getString("emailAddress"));
				user.setFirstName(rs.getString("firstname"));
				user.setLastName(rs.getString("lastname"));
				user.setRoleId(rs.getInt("roleid"));
				user.setStatus(rs.getInt("status"));
				user.setSerialNumber(rs.getInt("serialNumber"));
				user.setImagePath(rs.getString("imagePath"));
				;
				user.setLoggedIn(true);

			} else {
				user.setLoggedIn(false);
			}

		} catch (SQLException e) {
			user.setLoggedIn(false);
			e.printStackTrace();
		} finally {
			try {
				con.close();
			} catch (SQLException e) {

				e.printStackTrace();
			}
		}

		return user;
	}

	public static ArrayList<Menu> prepareMenus(int roleId, PropertyValues propertyValues) {

		ArrayList<Menu> menuList = new ArrayList<Menu>();
		DbConnectionProvider dbConnectionProvider = DbConnectionProvider.getInstance();
		Connection con = dbConnectionProvider.getDbConnection(propertyValues);

		System.out.println("role id  =" + roleId);

		String sql = "SELECT menuId, menuName, menuLink FROM menu WHERE menuid in "
				+ "(select menuid from menu_role where roleId =" + roleId + ")";

		int row = 0;

		try {
			PreparedStatement ps = con.prepareStatement(sql);
//			ps.setInt(1, roleId);

			ResultSet rs = ps.executeQuery();

			while (rs.next()) {
				Menu menu = new Menu();
				menu.setMenuId(rs.getInt("menuId"));
				menu.setMenuName(rs.getString("menuName"));
				menu.setMenuLink(rs.getString("menuLink"));
				row++;
				menuList.add(menu);
			}

		} catch (SQLException e) {

			e.printStackTrace();
		} finally {
			try {
				con.close();
			} catch (SQLException e) {

				e.printStackTrace();
			}
		}

		System.out.println("Menu items size from query =" + row);
		return menuList;
	}

	public static User getUser(String emailAddress, PropertyValues propertyValues) {

		User user = new User();
		DbConnectionProvider dbConnectionProvider = DbConnectionProvider.getInstance();
		Connection con = dbConnectionProvider.getDbConnection(propertyValues);
		String sql = "SELECT emailAddress, password, status, roleid, serialNumber, firstname, lastname FROM users where emailAddress= ?";
		
		try {
			PreparedStatement ps = con.prepareStatement(sql);
			ps.setString(1, emailAddress);
			ResultSet rs = ps.executeQuery();

			if (rs.next()) {
				
				user.setEmailAddress(rs.getString("emailAddress"));
				user.setPassword(rs.getString("password"));
				user.setFirstName(rs.getString("firstname"));
				user.setLastName(rs.getString("lastname"));
				user.setRoleId(rs.getInt("roleid"));
				user.setStatus(rs.getInt("status"));
				user.setSerialNumber(rs.getInt("serialNumber"));
			}
		} catch (Exception e) {
			// TODO: handle exception
		} finally {
			try {
				con.close();
			} catch (SQLException e) {

				e.printStackTrace();
			}
		}
		return user;
	}

	public static boolean updateUser(User user1, PropertyValues propertyValues) {
		
		DbConnectionProvider dbConnectionProvider = DbConnectionProvider.getInstance();

		Connection con = dbConnectionProvider.getDbConnection(propertyValues);

		String sql = "UPDATE users SET emailAddress=?, firstName=?, lastName=?, password=?, status=? WHERE serialNumber=?";


		boolean result = false;
		try {
			PreparedStatement ps = con.prepareStatement(sql);

			ps.setString(1, user1.getEmailAddress());
			ps.setString(2, user1.getFirstName());
			ps.setString(3, user1.getLastName());
			ps.setString(4, user1.getPassword());
			ps.setInt(5, user1.getStatus());
			ps.setInt(6, user1.getSerialNumber());
			

			System.out.println("sql sattement " + ps);

			int row = ps.executeUpdate();

			if (row > 0) {
				result = true;
			}

			return result;

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			try {
				con.close();
			} catch (SQLException e) {

				e.printStackTrace();
			}
		}

		return false;
	}

}
