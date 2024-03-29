package com.exavalu.servlets;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Properties;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.exavalu.pojos.CustomMessage;
import com.exavalu.pojos.PropertyValues;

import exavalu.com.entities.Country;
import exavalu.com.entities.Employee;
import exavalu.com.entities.EmployeeRole;
import exavalu.com.entities.Practice;
import exavalu.com.entities.User;
import exavalu.com.services.EmployeeService;

/**
 * Servlet implementation class SearchEmployee
 */
@WebServlet("/SearchEmployee")
public class SearchEmployee extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public SearchEmployee() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		User user = (User) (request.getSession().getAttribute("USER"));
		if (user != null || user.isLoggedIn()) {

			ServletContext context = getServletContext();
			String fullPath = context.getRealPath("/WEB-INF/config.properties");
			Properties properties = new Properties();
			PropertyValues propertyValues = PropertyValues.getInstance();

			try (InputStream input = new FileInputStream(fullPath)) {

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
			
			ArrayList<Practice> practiceList = EmployeeService.getPracticeList(propertyValues);
			ArrayList<EmployeeRole> employeeRoleList = EmployeeService.getEmployeeRoleList(propertyValues);

			ArrayList<Country> countryList = EmployeeService.getCountryList(propertyValues);

			
			ArrayList<Employee> empList = EmployeeService.getAllActiveEmployee(propertyValues);


			request.setAttribute("PRACTICELIST", practiceList);
			request.setAttribute("EMPLOYEEROLELIST", employeeRoleList);
			request.setAttribute("EMPLOYEE",empList );
			request.setAttribute("COUNTRYLIST", countryList);
			request.getRequestDispatcher("pages/searchEmployee.jsp").forward(request, response);
		} else {
			CustomMessage msg = new CustomMessage();
			msg.setMessage("Either email address or password is wrong");
			request.setAttribute("MSG", msg);
			request.getRequestDispatcher("dashboard.jsp").forward(request, response);
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
