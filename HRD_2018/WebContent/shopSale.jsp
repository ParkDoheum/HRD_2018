<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<%@ page import="kr.co.hk.*" %>
<%@ page import="java.util.*" %>

<%
	List<PizzaVo> list = (ArrayList<PizzaVo>)request.getAttribute("list");
%>

<h2>지점별 매출 현황</h2>

<table>
  <tr>
    <th>지점코드</th>
    <th>지점 명</th>
    <th>총매출액</th>
  </tr>
  <%
  	for(PizzaVo vo : list){
  %>
  <tr>
    <td><%=vo.getScode() %></td>
    <td><%=vo.getSname() %></td>
    <td>￦<%=vo.getTotal() %></td>
  </tr>
  <% } %>
</table>
