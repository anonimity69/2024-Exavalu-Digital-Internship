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

import com.exavalu.pojos.CustomMessage;
import com.exavalu.pojos.PropertyValues;

import exavalu.com.entities.Employee;
import exavalu.com.entities.EmployeeRole;
import exavalu.com.entities.Practice;
import exavalu.com.entities.User;
import exavalu.com.services.EmployeeService;
import exavalu.com.services.UserService;

/**
 * Servlet implementation class UpdateUser
 */
@WebServlet("/UpdateUser")
public class UpdateUser extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public UpdateUser() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		User user = (User) request.getSession().getAttribute("USER");

		if (user != null || user.isLoggedIn()) {

			String firstName = request.getParameter("firstName");
			String lastName = request.getParameter("lastName");
			String emailAddress = request.getParameter("emailAddress");
			String password = request.getParameter("password");
			
			
			
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
			
			User user1 = new User();
			
			user1.setFirstName(request.getParameter("firstName"));
			user1.setLastName(request.getParameter("lastName"));
			user1.setEmailAddress(request.getParameter("emailAddress"));
			user1.setPassword(request.getParameter("password"));
			if(request.getParameter("status") == null) {
				user1.setStatus(0);
			}else {
				user1.setStatus(1);
			}
			user1.setSerialNumber(Integer.parseInt(request.getParameter("Serial")));
			
			boolean result = UserService.updateUser(user1, propertyValues);
			

			ArrayList<Employee> empList = EmployeeService.getAllActiveEmployee(propertyValues);
			System.out.println("**** emplist size ===" + empList.size());

			if (result) {

				CustomMessage msg = new CustomMessage();
				msg.setMessage("User updated Successfully....");
				request.setAttribute("MSG", msg);

				System.out.println("*****User Profile Update*****");
				ArrayList<Practice> practiceList = EmployeeService.getPracticeList(propertyValues);
				ArrayList<EmployeeRole> employeeRoleList = EmployeeService.getEmployeeRoleList(propertyValues);

				request.setAttribute("PRACTICELIST", practiceList);
				request.setAttribute("EMPLOYEEROLELIST", employeeRoleList);
				request.setAttribute("EMPLOYEE", empList);
				request.getRequestDispatcher("pages/dashboard.jsp").forward(request, response);


			} else {
				CustomMessage msg = new CustomMessage();
				msg.setMessage("Please login to access this functionality...");
				request.setAttribute("MSG", msg);
				request.getRequestDispatcher("index.jsp").forward(request, response);

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
