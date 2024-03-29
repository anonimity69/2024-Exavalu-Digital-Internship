<%@ page import="exavalu.com.entities.EmployeeCount"%>
<%@ page import="java.util.ArrayList"%>

<%
ArrayList<EmployeeCount> employeeCountList = (ArrayList<EmployeeCount>) request.getSession().getAttribute("EMPLOYEECOUNT");
int count = 0; // Initialize a counter
%>

<div class="container-fluid">
    <div class="row justify-content-center">
        <%
        for (EmployeeCount employeeCount : employeeCountList) {
            if (count % 4 == 0 && count != 0) {
        %>
    </div>
    <div class="row justify-content-center">
        <% } %>
        <div class="col-lg-3 col-md-4 col-sm-6 mb-4">
            <div class="card" style="width: 18rem;">
                <img src="https://avatar.iran.liara.run/public" class="card-img-top" alt="" height=200px">
                <div class="card-body">
                    <p class="card-text"><%=employeeCount.getPracticeName()%>: <%=employeeCount.getEmployeeCount()%></p>
                    <p class="card-text"></p>
                </div>
            </div>
        </div>
        <% count++; // Increment the counter
        } %>
    </div>
</div>

		