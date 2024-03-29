<%@ page import="exavalu.com.entities.Employee"%>
 
<%@ page import="java.util.ArrayList"%>

<%
ArrayList<Employee> emp = (ArrayList<Employee>)request.getAttribute("EMPLOYEE");
%>
 
<h2>Employee Table</h2>
<div class="table-responsive small">
	<table class="table table-striped table-sm">
		<thead>
			<tr>
				<th scope="col">Employee Code</th>
				<th scope="col">First Name</th>
				<th scope="col">Last Name</th>
				<th scope="col">Practice</th>
				<th scope="col">Role</th>
				<th scope="col">Salary</th>
				<th scope="col">Status</th>
			</tr>
		</thead>
		<tbody>
			<% if (emp != null) { %>
			<%
			for (Employee empList : emp){ %>
			<tr>
				<td>
					<a href="EditEmployee?employeeCode=<%=empList.getEmployeeCode()%>">
				
				<%=empList.getEmployeeCode()%>
				</a>
				</td>
				<td><%=empList.getFirstName()%></td>
				<td><%=empList.getLastName()%></td>
				<td><%=empList.getPracticeName()%></td>
				<td><%=empList.getEmployeeRole()%></td>
				<td>$<%=empList.getSalary()%></td>
				<td><% if(empList.getStatus() == 1) {
				%> <button type="button" class="btn btn-success"></button>
				<%} else {%> <button type="button" class="btn btn-danger"></button>
 <%} %>
				</td>
			</tr>
			<%	
			}
			%>
			<% } else { %>
			<!-- Handle the case when emp is null -->
			<td>No employees found.</td>
			<% } %>
		</tbody>
	</table>
</div>