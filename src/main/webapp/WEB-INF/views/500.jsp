<!DOCTYPE html>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<html>
	<sec:authorize access="isAuthenticated()">
		<head>
			<jsp:include page="modulos/header.jsp"></jsp:include>
			<title>500 - Erro técnico</title>
		</head>
		<body>
			<jsp:include page="modulos/header-gin.jsp"></jsp:include>
			
			<div class="container">
				<div class = "error">
				    <div class="col-lg-8 col-lg-offset-2 text-center">
						<div class="logo">
							<h1>500</h1>
						</div>
						<p class="lead text-muted">${message}</p>
						<br>
						<div class="col-lg-6 col-lg-offset-3">
							<h4>Entre em contato</h4>
							<label>Ricardo Costa - <strong>ricardo.costa@ufc.br</strong></label>
						</div>
					</div>
				</div>
			</div>
			
			<jsp:include page="modulos/footer.jsp"></jsp:include>
		</body>
	</sec:authorize>
	<sec:authorize access="isAnonymous()">
		<c:redirect url="/login"></c:redirect>
	</sec:authorize>
</html>