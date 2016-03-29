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
	<title>Nova Categoria</title>
</head>
<body>

	<div class="container">

		<form:form id="cadastrarCategoria" servletRelativeAction="/patrimonio/cadastrar/categoria" commandName="categoria" method="POST" class="form-horizontal">
			<fieldset>
				<legend>Nova Categoria</legend>
				<div class="form-group">
					<label for="nome" class="col-lg-2 control-label">Nome da Categoria</label>
					<div class="col-lg-10">
						<form:input path="nome" id="nome" class="form-control" type="text" placeholder="Nome da categoria..."/>
						<div class="error-validation">
							<form:errors path="nome"></form:errors>
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
	<jsp:include page="../footer.jsp"></jsp:include>
</body>
</html>