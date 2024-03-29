package com.exavalu.servlets;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Properties;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import exavalu.com.entities.Address;
import exavalu.com.entities.Country;
import exavalu.com.entities.Employee;
import exavalu.com.entities.EmployeeAddress;
import exavalu.com.entities.EmployeeRole;
import exavalu.com.entities.Practice;
import exavalu.com.entities.User;
import com.exavalu.pojos.CustomMessage;
import com.exavalu.pojos.PropertyValues;
import exavalu.com.services.EmployeeService;

/**
 * Servlet implementation class SaveEmployee
 */
@WebServlet("/SaveEmployee")
public class SaveEmployee extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public SaveEmployee() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		User user = (User) request.getSession().getAttribute("USER");
		
		if(user != null || user.isLoggedIn()) {
			
			String employeeCode = request.getParameter("employeeCode");
			String firstName = request.getParameter("firstName");
			String lastName = request.getParameter("lastName");
			
			int employeeRoleId = Integer.parseInt( request.getParameter("employeeRoleId")); //have to convert to int
			int practiceId = Integer.parseInt( request.getParameter("practiceId"));  //have to convert to int
			String dateOfJoin = request.getParameter("dateOfJoin");
			double salary = Double.parseDouble(request.getParameter("salary"));  //have to convert to double
			int countryId = Integer.parseInt(request.getParameter("countryId"));			
			int stateId = Integer.parseInt(request.getParameter("stateId"));			
			int cityId = Integer.parseInt(request.getParameter("cityId"));			
			String addressLine1 = request.getParameter("addressLine1");
			String addressLine2 = request.getParameter("addressLine2");
			String zipCode = request.getParameter("zipCode");

			

			
			

			
			
			
			
			ServletContext context = getServletContext();
			String fullPath = context.getRealPath("/WEB-INF/config.properties");
			Properties properties = new Properties();
			PropertyValues propertyValues = PropertyValues.getInstance();

			try (FileInputStream input = new FileInputStream(fullPath)) {

				properties.load(input);
				String dbName = properties.getProperty("dbname");
				String url = properties.getProperty("url");
				String user1 = properties.getProperty("user");
				String dbpassword = properties.getProperty("password");

				propertyValues.setDbname(dbName);
				propertyValues.setPassword(dbpassword);
				propertyValues.setUrl(url);
				propertyValues.setUser(user1);

			} catch (IOException e) {
				e.printStackTrace(); // Handle the exception appropriately
			}
			
			Address address = new Address();
			address.setCountryId(countryId);
			address.setStateId(stateId);
			address.setCityId(cityId);
			address.setAddressLine1(addressLine1);
			address.setAddressLine2(addressLine2);
			address.setZipCode(zipCode);
			
			int addressId = EmployeeService.saveAddress(address, propertyValues);
			
			EmployeeAddress employeeAddress = new EmployeeAddress();
			employeeAddress.setAddressId(addressId);
			employeeAddress.setEmployeeCode(employeeCode);
			
			boolean result2 = EmployeeService.saveEmployeeAddress(employeeAddress, propertyValues);
			
			
			
			Employee employee = new Employee();
			employee.setEmployeeCode(request.getParameter("employeeCode"));
			employee.setFirstName(request.getParameter("firstName"));
			employee.setLastName(request.getParameter("lastName"));
			employee.setDateofJoin(request.getParameter("dateOfJoin"));
			employee.setEmployeeRoleId(Integer.parseInt(request.getParameter("employeeRoleId")));
			employee.setPracticeId(Integer.parseInt(request.getParameter("practiceId")));
			employee.setStatus(1);
			employee.setSalary(Double.parseDouble(request.getParameter("salary")));
			
			boolean result = EmployeeService.saveEmployee(employee, propertyValues);
			
			ArrayList<Employee> empList = EmployeeService.getAllActiveEmployee(propertyValues);
			System.out.println("**** emplist size ==="+empList.size());
			
			if(result) {
				
				CustomMessage msg = new CustomMessage();
				msg.setMessage("Employee saved Successfully....");
				request.setAttribute("MSG", msg);
				
				System.out.println("*********** data saved successfully");
				ArrayList<Practice> practiceList = EmployeeService.getPracticeList(propertyValues);
				ArrayList<EmployeeRole> employeeRoleList = EmployeeService.getEmployeeRoleList(propertyValues);
				ArrayList<Country> countryList = EmployeeService.getCountryList(propertyValues);

				
				request.setAttribute("PRACTICELIST", practiceList);
				request.setAttribute("EMPLOYEEROLELIST", employeeRoleList);
				request.setAttribute("EMPLOYEE",empList );
				request.setAttribute("COUNTRYLIST", countryList);

				request.getRequestDispatcher("pages/addEmployee.jsp").forward(request, response);
				
//				request.getRequestDispatcher("pages/addEmployee.jsp").forward(request, response);
				
				
		//		ArrayList<Employee> empList = EmployeeService.getAllActiveEmployees(propertyValues);
				
			} else {
				CustomMessage msg = new CustomMessage();
				msg.setMessage("Duplicate Entry");
				request.setAttribute("MSG", msg);
				request.getRequestDispatcher("pages/dashboard.jsp").forward(request, response);
				
				
			}
			
		} else {
			
		}
		
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
