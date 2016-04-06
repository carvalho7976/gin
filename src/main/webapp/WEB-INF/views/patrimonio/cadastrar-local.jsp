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
	<jsp:include page="../header.jsp"></jsp:include>
	<title>Novo local</title>
</head>
<body>

	<div class="container">

		<form:form id="cadastrarLocal" servletRelativeAction="/patrimonio/cadastrar/local" commandName="local" method="POST" class="form-horizontal">
			<input type="hidden"  name="acao" value="${acao}"/>
			<input type="hidden" name="idd" value= "${id}"/>
			<fieldset>
				<legend>Novo Local</legend>
				<div class="form-group">
					<label for="nome" class="col-lg-2 control-label">Nome do local</label>
					<div class="col-lg-10">
						<form:input path="nome" id="nome" class="form-control" type="text" placeholder="Nome do local..."/>
						<div class="error-validation">
							<form:errors path="nome"></form:errors>
						</div>
					</div>
				</div>
				<div class="form-group">
					<label for="pavimento" class="col-lg-2 control-label">Pavimento</label>
					<div class="col-lg-10">
						<form:input path="pavimento" id="pavimento" class="form-control" type="text" placeholder="Pavimento..."/>
						<div class="error-validation">
							<form:errors path="pavimento"></form:errors>
						</div>
					</div>
				</div>
				<div class="form-group">
					<label for="bloco" class="col-lg-2 control-label">Bloco</label>
					<div class="col-lg-10">
						<form:input path="bloco" id="bloco" class="form-control" type="text" placeholder="Bloco..."/>
						<div class="error-validation">
							<form:errors path="bloco"></form:errors>
						</div>
					</div>
				</div>
				<div class="form-group">
					<div class="col-lg-10 col-lg-offset-2">
						<a	href="<c:url value="/patrimonio/listar"></c:url>"
					class="btn btn-danger"">Cancelar</a>
						<button type="submit" class="btn btn-success">Adicionar</button>
					</div>
				</div>
			</fieldset>
		</form:form>
	</div>
	<jsp:include page="../footer.jsp"></jsp:include>
</body>
</html>