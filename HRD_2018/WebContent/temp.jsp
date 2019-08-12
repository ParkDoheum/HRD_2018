<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>피자전문점 판매관리 프로그램</title>
<link rel="stylesheet" href="common.css"  type="text/css"> 
</head>
<body>
	<header>
		<h4>(과정평가형 2018-12)피자전문점 판매관리 프로그램 ver1.0</h4>
	</header>	
	<nav>
		<ul>
			<li>
				<a href="sale">매출전표등록</a>
			</li>
			<li>
				<a href="totalSale">통합매출조회</a>
			</li>
			<li>
				<a href="shopSale">지점별매출현황</a>
			</li>
			<li>
				<a href="productSale">상품별매출현황</a>
			</li>
			<li>
				<a href="index.jsp">홈으로</a>
			</li>
		</ul>
	</nav>
	
	<section>	
		<jsp:include page="${view}"></jsp:include>	
	</section>
	
	<footer>HRDKOREA Copyright&copy;2018All rights reserved.Human Resources Development Service of Korea</footer>
</body>
</html>





