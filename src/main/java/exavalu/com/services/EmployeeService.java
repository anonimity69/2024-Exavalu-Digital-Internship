package exavalu.com.services;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import exavalu.com.entities.Practice;
import exavalu.com.entities.State;

import com.exavalu.pojos.PropertyValues;

import exavalu.com.entities.Address;
import exavalu.com.entities.City;
import exavalu.com.entities.Country;
import exavalu.com.entities.Employee;
import exavalu.com.entities.EmployeeAddress;
import exavalu.com.entities.EmployeeCount;
import exavalu.com.entities.EmployeeRole;
import exavalu.com.utilities.DbConnectionProvider;

public class EmployeeService {

	public static ArrayList<Practice> getPracticeList(PropertyValues propertyValues) throws IOException {

		ArrayList<Practice> practiceList = new ArrayList<Practice>();
		DbConnectionProvider dbConnectionProvider = DbConnectionProvider.getInstance();
		Connection con = dbConnectionProvider.getDbConnection(propertyValues);

		String sql = "SELECT * FROM practice";

		try {
			PreparedStatement ps = con.prepareStatement(sql);
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				Practice practice = new Practice();
				practice.setPracticeId(rs.getInt("practiceId"));
				practice.setPracticeName(rs.getString("PracticeName"));

				practiceList.add(practice);
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

		return practiceList;
	}

	public static ArrayList<EmployeeRole> getEmployeeRoleList(PropertyValues propertyValues) throws IOException {

		ArrayList<EmployeeRole> employeeRoleList = new ArrayList<EmployeeRole>();
		DbConnectionProvider dbConnectionProvider = DbConnectionProvider.getInstance();
		Connection con = dbConnectionProvider.getDbConnection(propertyValues);

		String sql = "SELECT * FROM employeerole";

		try {
			PreparedStatement ps = con.prepareStatement(sql);
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				EmployeeRole employeeRole = new EmployeeRole();
				employeeRole.setEmployeeRoleId(rs.getInt("employeeRoleId"));
				employeeRole.setEmployeeRole(rs.getString("employeeRole"));

				employeeRoleList.add(employeeRole);
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

		return employeeRoleList;

	}

	public static boolean saveEmployee(Employee employee, PropertyValues propertyValues) throws IOException {
		DbConnectionProvider dbConnectionProvider = DbConnectionProvider.getInstance();

		Connection con = dbConnectionProvider.getDbConnection(propertyValues);

		String sql = "INSERT INTO employees(employeeCode,firstName,lastName,status,employeeRoleId,practiceId,dateOfJoin,salary) "
				+ "VALUES (?,?,?,?,?,?,?,?)";

		boolean result = false;
		try {
			PreparedStatement ps = con.prepareStatement(sql);

			ps.setString(1, employee.getEmployeeCode());
			ps.setString(2, employee.getFirstName());
			ps.setString(3, employee.getLastName());
			ps.setInt(4, employee.getStatus());
			ps.setInt(5, employee.getEmployeeRoleId());
			ps.setInt(6, employee.getPracticeId());
			ps.setString(7, employee.getDateofJoin());
			ps.setDouble(8, employee.getSalary());

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

	public static ArrayList<Employee> getAllActiveEmployee(PropertyValues propertyValues) throws IOException {
		// TODO Auto-generated method stub

		DbConnectionProvider dbConnectionProvider = DbConnectionProvider.getInstance();
		Connection con = dbConnectionProvider.getDbConnection(propertyValues);

		String sql = "SELECT * from employees e ,employeerole R, practice P where E.employeeRoleId = R.employeeRoleId and E.practiceId = P.practiceId  ";

		ArrayList<Employee> empList = new ArrayList<Employee>();

		try {
			PreparedStatement ps = con.prepareStatement(sql);
			ResultSet rs = ps.executeQuery();

			while (rs.next()) {
				Employee emp = new Employee();
				emp.setDateofJoin(rs.getString("dateofJoin"));
				emp.setEmployeeCode(rs.getString("employeeCode"));
				emp.setEmployeeRole(rs.getString("employeeRole"));
				emp.setPracticeName(rs.getString("practiceName"));
				emp.setFirstName(rs.getString("firstName"));
				emp.setLastName(rs.getString("lastName"));
				emp.setSalary(rs.getDouble("salary"));
				emp.setStatus(rs.getInt("status"));

				empList.add(emp);

//				System.out.println();

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

		return empList;
	}

	public static Employee getEmployee(String employeeCode, PropertyValues propertyValues) {

		Employee emp = new Employee();
		DbConnectionProvider dbConnectionProvider = DbConnectionProvider.getInstance();
		Connection con = dbConnectionProvider.getDbConnection(propertyValues);
		String sql = "SELECT * from employees e ,employeerole R, practice P where E.employeeRoleId = R.employeeRoleId and E.practiceId = P.practiceId and E.employeeCode=?";
		System.out.println(sql);
		try {
			PreparedStatement ps = con.prepareStatement(sql);
			ps.setString(1, employeeCode);
			ResultSet rs = ps.executeQuery();

			if (rs.next()) {

				emp.setDateofJoin(rs.getString("dateofJoin"));
				emp.setEmployeeCode(rs.getString("employeeCode"));
				emp.setEmployeeRole(rs.getString("employeeRole"));
				emp.setPracticeName(rs.getString("practiceName"));
				emp.setFirstName(rs.getString("firstName"));
				emp.setLastName(rs.getString("lastName"));
				emp.setSalary(rs.getDouble("salary"));
				emp.setEmployeeRoleId(rs.getInt("employeeRoleId"));
				emp.setPracticeId(rs.getInt("practiceId"));
				emp.setStatus(rs.getInt("status"));

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
		return emp;

	}

	public static boolean updateEmploye(Employee employee, PropertyValues propertyValues) {
		DbConnectionProvider dbConnectionProvider = DbConnectionProvider.getInstance();

		Connection con = dbConnectionProvider.getDbConnection(propertyValues);

		String sql = "UPDATE employees SET firstName=?, lastName=?, status=?, employeeRoleId=?, practiceId=?, dateOfJoin=?, salary=? WHERE employeeCode=?";


		boolean result = false;
		try {
			PreparedStatement ps = con.prepareStatement(sql);

			ps.setString(1, employee.getFirstName());
			ps.setString(2, employee.getLastName());
			ps.setInt(3, employee.getStatus());
			ps.setInt(4, employee.getEmployeeRoleId());
			ps.setInt(5, employee.getPracticeId());
			ps.setString(6, employee.getDateofJoin());
			ps.setDouble(7, employee.getSalary());
			ps.setString(8, employee.getEmployeeCode());

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
	
	public static ArrayList<EmployeeCount> getEmployeeByPractice(PropertyValues propertyValues) {
		ArrayList<EmployeeCount> employeeCountList = new ArrayList<EmployeeCount>();
		DbConnectionProvider dbConnectionProvider = DbConnectionProvider.getInstance();
		Connection con = dbConnectionProvider.getDbConnection(propertyValues);
 
		String sql = "SELECT P.practiceName, COUNT(E.employeeCode) AS employeeCount FROM employees E JOIN practice P ON E.practiceId = P.practiceId GROUP BY P.practiceName";
//		int row = 0;
		try {
			PreparedStatement ps = con.prepareStatement(sql);
//			ps.setInt(1, roleId);
			System.out.println(ps);
			ResultSet rs = ps.executeQuery();
 
			while (rs.next()) {
				EmployeeCount employeeCount = new EmployeeCount();
				employeeCount.setEmployeeCount(rs.getInt("employeeCount"));
				employeeCount.setPracticeName(rs.getString("practiceName"));
//				row++;
				employeeCountList.add(employeeCount);
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
		return employeeCountList;
	}

	public static ArrayList<Country> getCountryList(PropertyValues propertyValues) {
		
		
		ArrayList<Country> countryList = new ArrayList<Country>();
		DbConnectionProvider dbConnectionProvider = DbConnectionProvider.getInstance();
		Connection con = dbConnectionProvider.getDbConnection(propertyValues);

		String sql = "SELECT * FROM countries";

		try {
			PreparedStatement ps = con.prepareStatement(sql);
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				Country country = new Country();
				country.setCountryId(rs.getInt("countryId"));
				country.setCountryName(rs.getString("countryName"));
				
				countryList.add(country);

				
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

		return countryList;
	}

	public static ArrayList<State> getStateList(String countryId, PropertyValues propertyValues) {
		
		ArrayList<State> stateList = new ArrayList<State>();
		
		DbConnectionProvider dbConnectionProvider = DbConnectionProvider.getInstance();
		Connection con = dbConnectionProvider.getDbConnection(propertyValues);

		String sql = "SELECT * FROM states where countryId = ?";
		System.out.println(sql);

		try {
			PreparedStatement ps = con.prepareStatement(sql);
			ps.setString(1, countryId);
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				State state = new State();
				state.setStateId(rs.getInt("stateId"));
				state.setStateName(rs.getString("stateName"));

				stateList.add(state);
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

		return stateList;
	}

	public static ArrayList<City> getcityList(String stateId, PropertyValues propertyValues) {
		
		ArrayList<City> cityList = new ArrayList<City>();
		
		DbConnectionProvider dbConnectionProvider = DbConnectionProvider.getInstance();
		Connection con = dbConnectionProvider.getDbConnection(propertyValues);

		String sql = "SELECT * FROM cities where stateId = ?";
		System.out.println(sql);

		try {
			PreparedStatement ps = con.prepareStatement(sql);
			ps.setString(1, stateId);
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				
				City city = new City();
				city.setCityId(rs.getInt("cityId"));
				city.setCityName(rs.getString("cityName"));
				
				cityList.add(city);
	
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

		return cityList;
	}

	public static int saveAddress(Address address, PropertyValues propertyValues) {

		DbConnectionProvider dbConnectionProvider = DbConnectionProvider.getInstance();

		Connection con = dbConnectionProvider.getDbConnection(propertyValues);

		String sql = "INSERT INTO employeeaddress(countryId,stateId,cityId,addressLine1,addressLine2,zipCode) "
				+ "VALUES (?,?,?,?,?,?)";
		
		int addressId = -1;
		
		try {
			
	        PreparedStatement ps = con.prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS);

			
			ps.setInt(1, address.getCountryId());
			ps.setInt(2, address.getStateId());
			ps.setInt(3, address.getCityId());
			ps.setString(4, address.getAddressLine1());
			ps.setString(5, address.getAddressLine2());
			ps.setString(6, address.getZipCode());
		

			System.out.println("sql sattement " + ps);

			int row = ps.executeUpdate();

			if (row > 0) {
				ResultSet generatedKeys = ps.getGeneratedKeys();
	            if (generatedKeys.next()) {
	                addressId = generatedKeys.getInt(1);
	            }
			}

			return addressId;

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

		return addressId;
	}

	public static boolean saveEmployeeAddress(EmployeeAddress employeeAddress, PropertyValues propertyValues) {
		
		DbConnectionProvider dbConnectionProvider = DbConnectionProvider.getInstance();

		Connection con = dbConnectionProvider.getDbConnection(propertyValues);

		String sql = "INSERT INTO employee_address(employeeCode,addressId) "
				+ "VALUES (?,?)";

		boolean result = false;
		try {
			PreparedStatement ps = con.prepareStatement(sql);

			ps.setString(1, employeeAddress.getEmployeeCode());
			ps.setInt(2, employeeAddress.getAddressId());
			

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

	public static int getAddressId(String employeeCode, PropertyValues propertyValues) {
		
		EmployeeAddress employeeAddress = new EmployeeAddress();
		DbConnectionProvider dbConnectionProvider = DbConnectionProvider.getInstance();
		Connection con = dbConnectionProvider.getDbConnection(propertyValues);
		String sql = "SELECT * from employee_address where employeeCode = ?";
		int addressId = -1;
		try {
			PreparedStatement ps = con.prepareStatement(sql);
			ps.setString(1, employeeCode);
			ResultSet rs = ps.executeQuery();

			if (rs.next()) {
				
				employeeAddress.setAddressId(rs.getInt("addressId"));
				employeeAddress.setEmployeeCode(rs.getString("employeeCode"));
				

			}
			addressId = employeeAddress.getAddressId();
		} catch (Exception e) {
			// TODO: handle exception
		} finally {
			try {
				con.close();
			} catch (SQLException e) {

				e.printStackTrace();
			}
		}
		return addressId;
	}

	

	public static Address getAddress(int addressId, PropertyValues propertyValues) {
		
		Address address = new Address();
		DbConnectionProvider dbConnectionProvider = DbConnectionProvider.getInstance();
		Connection con = dbConnectionProvider.getDbConnection(propertyValues);
		String sql = "SELECT * from employeeaddress e ,employee_address R, employees P, countries C, states S, cities d where E.addressId = R.addressId and R.employeeCode = P.employeeCode and e.countryId = c.countryId and e.stateId = s.stateId and e.cityId = d.cityId and e.addressId = ?";
		try {
			PreparedStatement ps = con.prepareStatement(sql);
			ps.setInt(1, addressId);
			ResultSet rs = ps.executeQuery();

			if (rs.next()) {
				
				address.setAddressLine1(rs.getString("addressLine1"));
				address.setAddressLine2(rs.getString("addressLine2"));
				address.setZipCode(rs.getString("zipCode"));
				address.setCountryId(rs.getInt("countryId"));
				address.setStateId(rs.getInt("stateId"));
				address.setCityId(rs.getInt("cityId"));
				address.setCountryName(rs.getString("countryName"));
				address.setStateName(rs.getString("stateName"));
				address.setCityName(rs.getString("cityName"));


				

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
		return address;
	}

	public static boolean updateAddress(Address address, int addressId, PropertyValues propertyValues) {
		
		DbConnectionProvider dbConnectionProvider = DbConnectionProvider.getInstance();

		Connection con = dbConnectionProvider.getDbConnection(propertyValues);

		String sql = "UPDATE employeeaddress SET addressLine1=?, addressLine2=?, zipCode=?, countryId=?, stateId=?, cityId=? WHERE addressId=?";
		


		boolean result = false;
		try {
			PreparedStatement ps = con.prepareStatement(sql);

			ps.setString(1,  address.getAddressLine1());
			ps.setString(2,  address.getAddressLine2());
			ps.setString(3,  address.getZipCode());
			ps.setInt(4,  address.getCountryId());
			ps.setInt(5,  address.getStateId());
			ps.setInt(6,  address.getCityId());
			ps.setInt(7,  addressId);

			

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


