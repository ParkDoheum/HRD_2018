<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<%@ page import="kr.co.hk.*" %>
<%@ page import="java.util.*" %>

<%
	List<PizzaVo> list = (ArrayList<PizzaVo>)request.getAttribute("list");
%>

<h2>상품별 매출 현황</h2>

<table>
  <tr>
    <th>피자 코드</th>
    <th>피자 명</th>
    <th>총매출액</th>
  </tr>
  <%
  	for(PizzaVo vo : list){
  %>
  <tr>
    <td><%=vo.getPcode() %></td>
    <td><%=vo.getPname() %></td>
    <td>￦<%=vo.getTotal() %></td>
  </tr>
  <% } %>
</table>
    