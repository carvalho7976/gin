<!DOCTYPE html>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>

<c:if test="${action eq 'cadastrar' }">
	<c:url var="url" value="/patrimonio/cadastrar" ></c:url>
	<c:set var="titulo" value="Novo Patrimonio"></c:set>
	<c:set var="botao" value="Cadastrar"></c:set>
</c:if>
<c:if test="${action eq 'editar' }">
	<c:url var="url" value="/patrimonio/editar"></c:url>
	<c:set var="titulo" value="Editar Patrimonio"></c:set>
	<c:set var="botao" value="Atualizar"></c:set>
</c:if>

<html>
<head>
	<jsp:include page="../header.jsp"></jsp:include>
	<title>${titulo}</title>
</head>
<body>
<jsp:include page="../menu.jsp"></jsp:include>
	<div class="container">
		<form:form id="cadastrarPatrimonio" servletRelativeAction="${url}" commandName="patrimonio" method="POST" class="form-horizontal">
			<fieldset>
				<legend>${titulo}</legend>
				<form:input path="id" id="id" type="hidden"/>
				<form:input path="categoria.id" id="categoria.id" type="hidden"/>
				<div class="form-group">
					<label for="tombamento" class="col-lg-2 control-label">Tombamento</label>
					<div class="col-lg-10">
						<form:input path="tombamento" id="tombamento" class="form-control" type="text" placeholder="Tombamento..."/>
						<div class="error-validation">
							<form:errors path="tombamento"></form:errors>
						</div>
					</div>
				</div>
				<div class="form-group">
					<label for="descricao" class="col-lg-2 control-label">Descrição</label>
					<div class="col-lg-10">
						<form:input path="descricao" id="descricao" class="form-control" type="text" placeholder="Descrição..."/>
						<div class="error-validation">
							<form:errors path="descricao"></form:errors>
						</div>
					</div>
				</div>
				<div class="form-group">
					<label for="categoria" class="col-lg-2 control-label">Categoria</label>
					<div class="col-lg-10">
						<form:input path="categoria.nome" id="categoria" class="form-control" type="text" placeholder="Categoria..."/>
						<div class="error-validation">
							<form:errors path="categoria.nome"></form:errors>
						</div>
					</div>
				</div>
				<div class="form-group">
					<label for="situacao" class="col-lg-2 control-label">Situação</label>
					<div class="col-lg-10">
						<form:select path="situacao" id="situacao" class="form-control">
							<form:option value="NONE"> Selecione uma opção</form:option>
							<form:options items="${situacao.tipo }"/>
						</form:select>
						<div class="error-validation">
							<form:errors path="situacao"></form:errors>
						</div>
					</div>
				</div>
				<div class="form-group">
					<label for="lotacao" class="col-lg-2 control-label">Lotação</label>
					<div class="col-lg-10">
						<form:select path="list_de_lotacao" id="lotacao" class="form-control">
							<form:option value="NONE"> Selecione uma opção</form:option>
							<form:options items="${list_de_lotacao.tipo }"/>
						</form:select>
						<div class="error-validation">
							<form:errors path="list_de_lotacao"></form:errors>
						</div>
					</div>
				</div>
				<div class="form-group">
					<label for="conservacao" class="col-lg-2 control-label">Conservação</label>
					<div class="col-lg-10">
						<form:select path="conservacao" id="conservacao" class="form-control">
							<form:option value="NONE"> Selecione uma opção</form:option>
							<form:options items="${conservacao.tipo }"/>
						</form:select>
						<div class="error-validation">
							<form:errors path="conservacao"></form:errors>
						</div>
					</div>
				</div>
				<div class="form-group">
					<label for="data_incorporacao" class="col-lg-2 control-label">Data de Incorporação</label>
					<div class="col-lg-10">
						<form:input id="data_incorporacao" class="form-control" path="data_incorporacao" pattern="[0-9]{2}\/[0-9]{2}\/[0-9]{4}$" />
						<form:errors path="data_incorporacao" cssClass="error" />
					</div>
				</div>
				<div class="form-group">
					<label for="data_chegada_campus" class="col-lg-2 control-label">Chegada no Campus</label>
					<div class="col-lg-10">
						<form:input id="data_chegada_campus" class="form-control" path="data_chegada_campus" pattern="[0-9]{2}\/[0-9]{2}\/[0-9]{4}$" />
						<form:errors path="data_chegada_campus" cssClass="error" />
					</div>
				</div>
				<div class="form-group">
					<div class="col-lg-10 col-lg-offset-2">
						<button type="reset" class="btn btn-default">Cancel</button>
						<button type="submit" class="btn btn-primary">${botao}</button>
					</div>
				</div>
			</fieldset>
		</form:form>
	</div>
	<script type="text/javascript">
		$(function(){
			$("#data_incorporacao").datepicker({dateFormat: 'dd/mm/yy'});
			$("#data_chegada_campus").datepicker({dateFormat: 'dd/mm/yy'});
		});
	</script>

</body>
</html>