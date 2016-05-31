<!DOCTYPE html>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>

<html>
<head>
	<title>GIN - Gestão de Inventário</title>
	
	<link href="<c:url value="/resources/css/bootstrap-flatly.min.css" />" rel="stylesheet" />
	<link href="<c:url value="/webjars/font-awesome/4.4.0/css/font-awesome.css" />" rel="stylesheet" />
	<link href="<c:url value="/resources/css/login-gin.css" />" rel="stylesheet" />
	
	<script src="<c:url value="/webjars/jquery/2.1.0/jquery.min.js" />"></script>
	<script src="<c:url value="/webjars/bootstrap/3.3.2/js/bootstrap.min.js" />"></script>
	<script src="<c:url value="/resources/js/login-gin.js" />"></script>
	<script src="<c:url value="/webjars/jquery-maskedinput/1.3.1/jquery.maskedinput.min.js" />"></script>
	<script src="<c:url value="/webjars/jquery-validation/1.12.0/jquery.validate.min.js" />"></script>
	
</head>
<body onload="document.f.j_username.focus();">

	<div class="login-container" align="center">
	
		<div class="login-header" align="center">
			<img alt="GIN - Gestão de Inventário" src="<c:url value="/resources/images/gin-logo-02.png" />">
		</div>
		
		<div class="container-form">
		
			<div class="form-title"><span>Faça seu login</span></div>
			
			<form id="form-login" name='f' action="<c:url value='j_spring_security_check' />" method='POST'>

				<c:if test="${not empty error}">
					<div id="erro" class="login-error" align="left"><i class="fa fa-times"></i> ${error}</div>
				</c:if>
				
				<div id="error" class="login-error hidden" align="left"><i class="fa fa-asterisk"></i> Informe seu usuario e senha.</div>

				<c:if test="${not empty info}">
					<div class="alert alert-info msg"> <i class="fa fa-info"></i> ${info}</div>
				</c:if>
				
				<div class="form-body">
					<div class="form-group form-icon ${not empty error ? 'has-error' : ''}">
					    <input id="cpf" class="form-control input-lg only-num" type="text" name='j_username' value='' placeholder="CPF..." required="required" min="11" size="11">
						<label for="cpf" class="fa fa-user label-icon"></label>
					</div>
					
					<div class="form-group form-icon ${not empty error ? 'has-error' : ''}">
					    <input id="senha" class="form-control input-lg" type="password" name='j_password' value='' placeholder="Senha..." required="required">
					    <label for="senha" class="fa fa-lock label-icon"></label>
					</div>
				</div>

				<div class="form-group">
					<input class="btn btn-primary pull-right" name="submit" type="submit" value="Login" value="Login" />
				</div>
			</form>
		</div>
	</div>
	
	<footer>
		<p>Universidade Federal do Ceará - Campus Quixadá</p>
		<p>Todos os direitos reservados</p>
	</footer>

</body>
</html>