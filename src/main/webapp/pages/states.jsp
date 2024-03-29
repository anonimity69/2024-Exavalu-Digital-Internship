<%@ page import="exavalu.com.entities.State"%>
<%@ page import="java.util.ArrayList"%>

<select class="form-control" name="stateId">
	<option value="0">--Select a State--</option>
	<%
	ArrayList<State> stateList = (ArrayList<State>) request.getAttribute("STATELIST");
	for (State state : stateList) {
	%>
	<option value="<%=state.getStateId()%>"><%=state.getStateName()%>
	</option>
	<%
	}
	%>
</select>


