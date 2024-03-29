<%@ page import="exavalu.com.entities.City"%>
<%@ page import="java.util.ArrayList"%>

<select class="form-control" name="cityId">
	<option value="0">--Select a cIty--</option>
	<%
	ArrayList<City> cityList = (ArrayList<City>) request.getAttribute("CITYLIST");
	for (City city : cityList) {
	%>
	<option value="<%=city.getCityId()%>"><%=city.getCityName()%>
	</option>
	<%
	}
	%>
</select>