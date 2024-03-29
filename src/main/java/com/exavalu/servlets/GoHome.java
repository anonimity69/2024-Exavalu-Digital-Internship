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
import javax.servlet.http.HttpSession;

import com.exavalu.pojos.CustomMessage;
import com.exavalu.pojos.PropertyValues;

import exavalu.com.entities.Employee;
import exavalu.com.entities.EmployeeCount;
import exavalu.com.entities.Menu;
import exavalu.com.entities.User;
import exavalu.com.services.EmployeeService;
import exavalu.com.services.UserService;

/**
 * Servlet implementation class GoHome
 */
@WebServlet("/GoHome")
public class GoHome extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public GoHome() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		
		User user = (User) request.getSession().getAttribute("USER");
		String emailAddress = user.getEmailAddress();
		String password = user.getPassword();

		
		
		ServletContext context = getServletContext();
		String fullPath = context.getRealPath("/WEB-INF/config.properties");
		Properties properties = new Properties();
		PropertyValues propertyValues= PropertyValues.getInstance();
		
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
		
		// Now I need to call my service class method to validate 
		//and retieve user data for further use
		
		
		if(user.isLoggedIn())
		{
			// go to dashboard
			HttpSession session = request.getSession();
			
			ArrayList<Menu> menuList = UserService.prepareMenus(user.getRoleId(),propertyValues);
			ArrayList<EmployeeCount> employeeCountList = EmployeeService.getEmployeeByPractice(propertyValues);
			ArrayList<Employee> empList = EmployeeService.getAllActiveEmployee(propertyValues);
			request.setAttribute("EMPLOYEE",empList );

			if(menuList !=null)
			{
				System.out.println("Menu items size ="+menuList.size());
			}
			else
			{
				System.out.println("Menu items size is zero");
			}
			
			session.setAttribute("MENUS", menuList);
			session.setAttribute("EMPLOYEECOUNT",employeeCountList);
			
			session.setAttribute("USER", user);
			
			request.getRequestDispatcher("pages/dashboard.jsp").forward(request, response);
		}
		else
		{
			//go back to login page with error message
			CustomMessage msg = new CustomMessage();
			msg.setMessage("Either email address or password is wrong");
			request.setAttribute("MSG", msg);
			request.getRequestDispatcher("index.jsp").forward(request, response);
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
