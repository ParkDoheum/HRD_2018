<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page import="kr.co.hk.*" %>
<%@ page import="java.util.*" %>
<% 		
	List<PizzaVo> shopList = (List<PizzaVo>)request.getAttribute("shopList");
	List<PizzaVo> pizzaList = (List<PizzaVo>)request.getAttribute("pizzaList");	
%>
<form id="frm" action="sale" method="post" onsubmit="return chk();">
	<table>
		<tr>
			<th>매출전표번호</th>
			<td><input type="text" name="saleno" value="${saleno}"></td>
		</tr>
		<tr>
			<th>지점코드</th>
			<td><select name="scode">
						<option value="">지점선택</option>
						<% for(PizzaVo vo : shopList)  { %>
							<option value="<%=vo.getScode()%>">[<%=vo.getScode()%>]<%=vo.getSname()%></option>
						<% } %>
					</select>
			</td>			
		</tr>
		<tr>
			<th>판매일자</th>
			<td><input type="date" name="saledate" value=""></td>
		</tr>
		<tr>
			<th>피자코드</th>
			<td><select name="pcode">
						<option value="">피자선택</option>
						<% for(PizzaVo vo : pizzaList)  { %>
							<option value="<%=vo.getPcode()%>">[<%=vo.getPcode()%>]<%=vo.getPname()%></option>
						<% } %>
					</select>
			</td>
		</tr>
		<tr>
			<th>판매수량</th>
			<td><input type="number" name="amount"></td>
		</tr>
		<tr>
			<td colspan="2"><input type="submit" value="전표등록">
				   					<input type="reset" value="다시쓰기">
			</td>
		</tr>
	</table>
</form>

<script>
	function chk() {		
		if (frm.saleno.value == '') {
			alert("매출전표가 입력되지 않았습니다 !");
			frm.saleno.focus();
			return false;
			
		} else if (frm.scode.value == '') {
			alert("지점코드가 입력되지 않았습니다.");
			frm.scode.focus();
			return false;
			
		} else if (frm.saledate.value == '') {
			alert("판매일자가 입력되지 않았습니다.");
			frm.saledate.focus();
			return false;			
			
		} else if (frm.pcode.value == '') {
			alert("피자코드가 입력되지 않았습니다.");
			frm.pcode.focus();
			return false;
			
		} else if (frm.amount.value == "") {
			alert("판매수량이 입력되지 않았습니다.");
			frm.amount.focus();
			return false;			
		}
		alert("매출전표가 정상적으로 등록되었습니다!");
		return true;
	}
</script>