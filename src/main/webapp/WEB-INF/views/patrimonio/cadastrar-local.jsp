<!DOCTYPE html>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<jsp:include page="../header.jsp"></jsp:include>
<title>Novo local</title>
</head>
<body>
	<div class="container">

		<form:form id="cadastrarLocal" servletRelativeAction="/patrimonio/cadastrar/local" commandName="local" method="POST" class="form-horizontal">
			<fieldset>
				<legend>Novo Local</legend>
				<div class="form-group">
					<label for="nome" class="col-lg-2 control-label">Nome do local</label>
					<div class="col-lg-10">
						<form:input path="nome" id="nome" class="form-control" type="text" placeholder="Nome do local"/>
						<div class="error-validation">
							<form:errors path="nome"></form:errors>
						</div>
					</div>
				</div>
				<div class="form-group">
					<label for="pavimento" class="col-lg-2 control-label">Pavimento</label>
					<div class="col-lg-10">
						<form:input path="pavimento" id="pavimento" class="form-control" type="text" placeholder="pavimento"/>
						<div class="error-validation">
							<form:errors path="pavimento"></form:errors>
						</div>
					</div>
				</div>
				<div class="form-group">
					<label for="bloco" class="col-lg-2 control-label">Bloco</label>
					<div class="col-lg-10">
						<form:input path="bloco" id="bloco" class="form-control" type="text" placeholder="bloco"/>
						<div class="error-validation">
							<form:errors path="bloco"></form:errors>
						</div>
					</div>
				</div>
				<div class="form-group">
					<div class="col-lg-10 col-lg-offset-2">
						<button type="reset" class="btn btn-default">Cancel</button>
						<button type="submit" class="btn btn-success">Adicionar</button>
					</div>
				</div>
			</fieldset>
		</form:form>

	</div>
</body>
</html>