<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="kr.co.hk.*" %>
<%@ page import="java.util.*" %>
<%@ page import="java.text.*" %>
<%@ page import="java.math.*" %>
<%
	List<PizzaVo> list = (ArrayList<PizzaVo>)request.getAttribute("list");
%>

<h2>통합매출현황조회</h2>

<table>
  <tr>
    <th>매출전표번호</th>
    <th>지점명</th>
    <th>판매일자</th>
    <th>피자코드</th>
    <th>피자명</th>
    <th>판매수량</th>
    <th>매출액</th>
  </tr>
  <% 
  	for(PizzaVo vo : list){  		
  		String total = NumberFormat.getCurrencyInstance(Locale.KOREA).format(vo.getTotal());
  		//String total = String.format("%,d", vo.getTotal()); 
  %>
	  <tr>
	    <td><%=vo.getSaleno() %></td>
	    <td><%=vo.getScode() %>-<%=vo.getSname() %></td>
	    <td><%=vo.getSaledate() %></td>
	    <td><%=vo.getPcode() %></td>
	    <td><%=vo.getPname() %></td>
	    <td><%=vo.getAmount() %></td>
	    <td>￦<%=total %></td>
	  </tr>
  <% } %>
</table>
