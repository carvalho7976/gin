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

				<c:if test="${not empty info}">
					<div class="alert alert-success alert-dismissible" role="alert"
						id="alert-info">
						<button type="button" class="close" data-dismiss="alert">
							<span aria-hidden="true">&times;</span><span class="sr-only">Close</span>
						</button>
						<c:out value="${info}"></c:out>
					</div>
				</c:if>
				<form:input path="id" type="hidden"/>
				<div class="form-group form-error">
					<label for="tombamento" class="col-lg-2 control-label "><span class="red">*</span> Tombamento</label>
					<div class="col-lg-10">
						<form:input path="tombamento" id="tombamento" class="form-control only-num valid-num" type="text" placeholder="Tombamento..."/>
						<div class="error-validation" id="error-tombamento">
							<form:errors path="tombamento"></form:errors>
						</div>
					</div>
				</div>
				<div class="form-group form-error">
					<label for="descricao" class="col-lg-2 control-label"><span class="red">*</span> Descrição</label>
					<div class="col-lg-10">
						<form:input path="descricao" id="descricao" class="form-control" type="text" placeholder="Descrição..."/>
						<div class="error-validation" id="error-descricao">
							<form:errors path="descricao"></form:errors>
						</div>
					</div>
				</div>
				<div class="row form-group form-error">
					<div>
						<label for="categoria" class="col-lg-2 control-label"><span class="red">*</span> Categoria</label>
						<div class="col-lg-9">
							<form:select path="categoria.id" id="categoria" class="form-control">
								<form:option value=""> Selecione uma categoria</form:option>
								<form:options items="${categorias }" itemLabel="nome" itemValue="id"/>
							</form:select>
							<div class="error-validation" id="error-categoria">
								<form:errors path="categoria"></form:errors>
							</div>
						</div>
					</div>
					<div class="col-lg-1">
						<a class="btn btn-success" href="<c:url value="/patrimonio/cadastrar/categoria/${action}/${id}"></c:url>" > <span class="glyphicon glyphicon-plus"></span> 
						</a>
					</div>
				</div>
				<div class="row form-group form-error">
					<div>
						<label for="local" class="col-lg-2 control-label"><span class="red">*</span> Local</label>
						<div class="col-lg-9">
							<form:select path="local.id" id="local" class="form-control">
								<form:option value=""> Selecione um local</form:option>
								<form:options items="${locais}" itemLabel="fullLocal" itemValue="id"/>
							</form:select>
							<div class="error-validation" id="error-local">
								<form:errors path="local"></form:errors>
							</div>
						</div>
					</div>
					<div class="col-lg-1">
						<a class="btn btn-success" href="<c:url value="/patrimonio/cadastrar/local/${action}/${id}"></c:url>" > <span class="glyphicon glyphicon-plus"></span> 
						</a>
					</div>
				</div>
				<div class="form-group form-error">
					<label for="situacao" class="col-lg-2 control-label"><span class="red">*</span> Situação</label>
					<div class="col-lg-10">
						<form:select path="situacao" id="situacao" class="form-control">
							<form:option value=""> Selecione uma opção</form:option>
							<form:options items="${situacao }" itemLabel="tipo"/>
						</form:select>
						<div class="error-validation" id="error-situacao">
							<form:errors path="situacao"></form:errors>
						</div>
					</div>
				</div>
				<div class="form-group form-error">
					<label for="lotacao" class="col-lg-2 control-label"><span class="red">*</span> Lotação</label>
					<div class="col-lg-10">
						<form:select path="lotacao" id="lotacao" class="form-control">
							<form:option value=""> Selecione uma opção</form:option>
							<form:options items="${lotacao }" itemLabel="tipo"/>
						</form:select>
						<div class="error-validation" id="error-lotacao">
							<form:errors path="lotacao"></form:errors>
						</div>
					</div>
				</div>
				<div class="form-group form-error">
					<label for="conservacao" class="col-lg-2 control-label"><span class="red">*</span> Conservação</label>
					<div class="col-lg-10">
						<form:select path="conservacao" id="conservacao" class="form-control">
							<form:option value=""> Selecione uma opção</form:option>
							<form:options items="${conservacao}" itemLabel="tipo"/>
						</form:select>
						<div class="error-validation" id="error-conservacao">
							<form:errors path="conservacao"></form:errors>
						</div>
					</div>
				</div>
				<div class="row">
					<div class="form-error">
						<label for="incorporacao" class="col-lg-2 control-label"><span class="red">*</span> Data de Incorporação</label>
						<div class="col-lg-4">
							<form:input id="incorporacao" class="form-control" path="incorporacao" pattern="[0-9]{2}\/[0-9]{2}\/[0-9]{4}$" />
							<div class="error-validation" id="error-data-incorporacao">
								<form:errors path="incorporacao"></form:errors>
							</div>
						</div>
					</div>
					<div class="form-error">
						<label for="chegadaCampus" class="col-lg-2 control-label">Chegada no Campus</label>
						<div class="col-lg-4">
							<form:input id="chegadaCampus" class="form-control" path="chegadaCampus" pattern="[0-9]{2}\/[0-9]{2}\/[0-9]{4}$" />
							<div class="error-validation" id="error-chegada-campus">
								<form:errors path="chegadaCampus"></form:errors>
							</div>
						</div>
					</div>
				</div>
				<br>
				<div class="form-group">
					<label for="comentario" class="col-lg-2 control-label">Comentario</label>
					<div class="col-lg-10">
						<form:textarea path="comentario.mensagem" id="comentario" class="form-control" placeholder="Comentário..." rows="3"/>
					</div>
				</div>
				<br> <br>
				<div class="form-group">
					<div class="col-lg-10 col-lg-offset-2">
						<a	href="<c:url value="/patrimonio/listar"></c:url>" class="btn btn-danger">Cancelar</a>
						<button type="submit" class="btn btn-success">${botao}</button>
					</div>
				</div>
			</fieldset>
		</form:form>

	</div>
	<jsp:include page="../footer.jsp"></jsp:include>

	<script type="text/javascript">
		$(function(){
			$("#incorporacao").datepicker({dateFormat: 'dd/mm/yy'});
			$("#chegadaCampus").datepicker({dateFormat: 'dd/mm/yy'});
		});
	</script>
</body>
</html>