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
import exavalu.com.entities.State;
import exavalu.com.entities.User;
import exavalu.com.services.EmployeeService;

/**
 * Servlet implementation class LoadStates
 */
@WebServlet("/LoadStates")
public class LoadStates extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public LoadStates() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		String countryId = request.getParameter("countryId");
		System.out.println(countryId);
		User user = (User) request.getSession().getAttribute("USER");

		if (user != null || user.isLoggedIn()) {
			System.out.println("hello here");

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
			ArrayList<State> stateList = EmployeeService.getStateList(countryId, propertyValues);

			request.setAttribute("STATELIST", stateList);
			request.getRequestDispatcher("pages/states.jsp").forward(request, response);


		

	} else {
		System.out.println("faltu code");
	}
}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
