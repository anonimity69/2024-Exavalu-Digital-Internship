<%@ page import="exavalu.com.entities.User"%>
<%@ page import="com.exavalu.pojos.CustomMessage"%>

<%
User user = (User) request.getSession().getAttribute("USER");
if (user == null || !user.isLoggedIn()) {
	CustomMessage msg = new CustomMessage();
	msg.setMessage("You need to login first to access this page.");
	request.setAttribute("MSG", msg);
	request.getRequestDispatcher("../index.jsp").forward(request, response);
}
%>

<div
	class="d-flex justify-content-between flex-wrap flex-md-nowrap align-items-center pt-3 pb-2 mb-3 border-bottom">
	<h1 class="h2">Dashboard</h1>
	<span class="text-primary"> Welcome - <%
	out.print(user.getFirstName() + " " + user.getLastName());
	%>
	</span>
	<div class="btn-toolbar mb-2 mb-md-0">
		<div class="btn-group me-2">
			<button type="button" class="btn btn-sm btn-outline-secondary">Share</button>
			<button type="button" class="btn btn-sm btn-outline-secondary">Export</button>
		</div>
		<button type="button"
			class="btn btn-sm btn-outline-secondary dropdown-toggle d-flex align-items-center gap-1">
			<svg class="bi">
								<use xlink:href="#calendar3" /></svg>
			This week
		</button>
	</div>
</div>