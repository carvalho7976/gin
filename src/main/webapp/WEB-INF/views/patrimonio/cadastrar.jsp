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
<title>Insert title here</title>
</head>
<body>
	
	<div id="container">
		<form:form servletRelativeAction="cadastrar" method="post" modelAttribute="patrimonio" role="form" class="form-horizontal">
			<div class="form-group" style="text-align: center;">
				<label class="control-label" style="font-size: 20px;">Cadastrar Patrimonio</label>
			</div>
			
			<div class="form-group">
			    <label for="tombamento" class="col-sm-1 control-label">Tombamento</label>
			    <div class="col-sm-10">
			    	<form:input id="tombamento" class="form-control" placeholder="Tombamento" path="tombamento"/>
			    	<form:errors path="tombamento" cssClass="error" />
			    </div>
			</div>
			
			<div class="form-group">
			    <label for="descricao" class="col-sm-1 control-label">Descrição</label>
			    <div class="col-sm-10">
			    	<form:input id="descricao" class="form-control" placeholder="Descrição" path="descricao"/>
			    	<form:errors path="descricao" cssClass="error" />
			    </div>
			</div>
			<div class="form-group">
			    <label for="categoria" class="col-sm-1 control-label">Categoria</label>
			    <div class="col-sm-10">
			    	<form:input id="categoria" class="form-control" placeholder="Categoria" path="categoria"/>
			    	<form:errors path="categoria" cssClass="error" />
			    </div>
			</div>
			
			<div class="form-group">
			    <label for="situcao" class="col-sm-1 control-label">Situação</label>
			    <div class="col-sm-10">
			    	<form:select id="situacao" path="situacao" class="form-control">
			    		<form:option value="NONE"> --SELECT--</form:option>
			    		 <form:options items="${situacao}"></form:options>
			    	</form:select>
			    	<form:errors path="situacao" cssClass="error" />
			    </div>
			</div>
			<div class="form-group">
			    <label for="lotacao" class="col-sm-1 control-label">Lotação</label>
			    <div class="col-sm-10">
			    	<form:select id="lotacao" path="list_de_lotacao" class="form-control">
			    		<form:option value="NONE"> --SELECT--</form:option>
			    		 <form:options items="${list_de_lotacao}"></form:options>
			    	</form:select>
			    	<form:errors path="list_de_lotacao" cssClass="error" />
			    </div>
			</div>
			<div class="form-group">
			    <label for="conservacao" class="col-sm-1 control-label">Conservação</label>
			    <div class="col-sm-10">
			    	<form:select id="consercao" path="conservacao" class="form-control">
			    		<form:option value="NONE"> --SELECT--</form:option>
			    		 <form:options items="${conservacao}"></form:options>
			    	</form:select>
			    	<form:errors path="conservacao" cssClass="error" />
			    </div>
			</div>
			<div class="form-group">
			    <label for="data_incorporacao" class="col-sm-1 control-label">Data de Incorporação</label>
			    <div class="col-sm-10">
			    	<form:input id="data_incorporacao" class="form-control" path="data_incorporacao" pattern="[0-9]{2}\/[0-9]{2}\/[0-9]{4}$" />
			    	<form:errors path="data_incorporacao" cssClass="error" />
			    </div>
			</div>
			<div class="form-group">
			    <label for="data_chegada_campus" class="col-sm-1 control-label">Data de Chegada no Campus</label>
			    <div class="col-sm-10">
			    	<form:input id="data_chegada_campus" class="form-control" path="data_chegada_campus" pattern="[0-9]{2}\/[0-9]{2}\/[0-9]{4}$"  />
			    	<form:errors path="data_chegada_campus" cssClass="error" />
			    </div>
			</div>
			
			<div class="controls">
				<input id="criar" class="btn btn-primary" type="submit" value="Cadastrar"/>
				<a href="<c:url value="#"></c:url>" class="btn btn-default">Cancelar</a>
			</div>
		</form:form>
		
	</div>
	<script type="text/javascript">
		$(function(){
			$("#data_incorporacao").datepicker({dateFormat: 'dd/mm/yy'});
			$("#data_chegada_campus").datepicker({dateFormat: 'dd/mm/yy'});
		});
	</script>

	<jsp:include page="../footer.jsp"></jsp:include>
</body>
</html>