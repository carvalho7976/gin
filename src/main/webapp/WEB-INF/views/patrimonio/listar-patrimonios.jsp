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
	<jsp:include page="../modulos/header.jsp"></jsp:include>
	<title>Inventário UFC/Quixadá</title>
</head>
<body>

	<jsp:include page="../modulos/header-gin.jsp"></jsp:include>

	<div class="container">
	
		<c:if test="${not empty erro}">
			<div class="alert alert-danger alert-dismissible" role="alert"
				id="alert-erro">
				<button type="button" class="close" data-dismiss="alert">
					<span aria-hidden="true">&times;</span><span class="sr-only">Close</span>
				</button>
				<c:out value="${erro}"></c:out>
			</div>
		</c:if>
		<c:if test="${not empty info}">
			<div class="alert alert-success alert-dismissible" role="alert"
				id="alert-info">
				<button type="button" class="close" data-dismiss="alert">
					<span aria-hidden="true">&times;</span><span class="sr-only">Close</span>
				</button>
				<c:out value="${info}"></c:out>
			</div>
		</c:if>
		
		<h3 class="section">Buscar Patrimônios</h3>
		
		<form:form id="formBuscarPatrimonio" role="form" servletRelativeAction="/patrimonio/buscar" method="POST" class="bs-component">
			<div class="form-group">
				<div class="input-group">
					<input id="bucaPratrimonio" name="descricao" type="text" class="form-control" placeholder="Tombamento ou Descrição..." required="required" value="${descricao }" />
					<span class="input-group-btn">
						<button class="btn btn-default" name="submit" type="submit">
							<span class="glyphicon glyphicon-search"></span>
						</button>
					</span>
				</div>
			</div>
		</form:form>
		<br>
		
		<h3 class="section">Buscar por Categoria ou Local</h3>
		
		<form:form id="formBuscar" name="formBuscar" servletRelativeAction="/patrimonio/buscaPersolanizada" method="POST" class="bs-component">
			<div class="form-group">
				<div class="select-group">
					<label for="selectLocais">Locais</label>
					<select id="selectLocais" name="selectLocais" class="form-control">
						<option value="-1" selected>Escolha um local...</option>
						<c:forEach var="local" items="${locais}">
							<option value="${local.id}">${local.localizacao}</option>
						</c:forEach>
					</select>
				</div>
			</div>
			<div class="form-group">
				<div class="select-group">
					<label for="selectCategorias">Categorias</label>
					<select id="selectCateogrias" name="selectCateogrias" class="form-control">
						<option value="-1" selected>Escolha uma categoria...</option>
						<c:forEach var="categoria" items="${categorias}">
							<option value="${categoria.id}">${categoria.nome}</option>
						</c:forEach>
					</select>
				</div>
			</div>
			<div align="right">
				<button id="buscarLocalCategoria" type="submit" class="btn btn-success">Buscar</button>
			</div>
		</form:form>
		
		<br>
		
		<h3 class="section">Patrimônios da UFC/Quixadá</h3>
		
		<div align="right">
			<a class="btn btn-success" href="<c:url value="/patrimonio/cadastrar"/>"> Novo Patrimônio</a>
		</div> <br>

		<c:if test="${empty patrimonios }">
			<div class="alert alert-info">
				<strong>Atenção!</strong> Não há patrimônios cadastrados. Por favor
				<a href="<c:url value="/patrimonio/cadastrar"/>" class="alert-link">cadastre
					novos patrimônios.</a>
			</div>
		</c:if>

		<c:if test="${not empty patrimonios }">
			<table class="table table-striped table-hover">
				<thead>
					<tr>
						<th>#</th>
						<th>Tombamento</th>
						<th>Descrição</th>
						<th>Categoria</th>
						<th>Local</th>
						<th>Detalhes</th>
						<th>Editar</th>
						<th>Excluir</th>
					</tr>
				</thead>
				<tbody>
					<c:forEach items="${patrimonios }" var="patrimonio"
						varStatus="cont">
						<tr>
							<td>${cont.count}</td>
							<td>${patrimonio.tombamento}</td>
							<td>${patrimonio.descricao}</td>
							<td>${patrimonio.categoria.nome}</td>
							<td>${patrimonio.local.fullLocal}</td>
							<td><a class="btn btn-primary"
								data-href="<c:url value="/patrimonio/detalhe/${patrimonio.id }"></c:url>"
								data-toggle="modal" data-target="#detalhe-patrimonio"> <span
									class="glyphicon glyphicon-eye-open"></span>
							</a></td>
							<td><a class="btn btn-info"
								href="<c:url value="/patrimonio/editar/${patrimonio.id }"></c:url>">
									<span class="glyphicon glyphicon-pencil"></span>
							</a></td>
							<td><a class="btn btn-danger"
								data-href="<c:url value="/patrimonio/excluir/${patrimonio.id }"></c:url>"
								data-toggle="modal" data-target="#confirm-delete"> <span
									class="glyphicon glyphicon-trash"></span>
							</a></td>
						</tr>
					</c:forEach>
				</tbody>
			</table>
		</c:if>
	</div>

	<div id="confirm-delete" class="modal" role="dialog" tabindex="-1" aria-labelledby="myModalLabel" aria-hidden="true">
		<div class="modal-dialog">
			<div class="modal-content">
				<div class="modal-header">
					<button type="button" class="close" data-dismiss="modal"
						aria-hidden="true">&times;</button>
					<h4 class="modal-title" id="myModalLabel">Confirmar Deleção</h4>
				</div>
				<div class="modal-body">
					<p>Você está a ponto de excluir um patrimônio do estoque, esse
						procedimento é irreversível.</p>
					<p>Você deseja continuar?</p>
				</div>
				<div class="modal-footer">
					<button type="button" class="btn btn-default" data-dismiss="modal">Cancelar</button>
					<a class="btn btn-danger btn-ok">Deletar</a>
				</div>
			</div>
		</div>
	</div>
	
	<div id="detalhe-patrimonio" class="modal" role="dialog" tabindex="-1" aria-labelledby="myModalLabel" aria-hidden="true">
		<div class="modal-dialog modal-lg">
			<div class="modal-content">
				<div class="modal-header">
					<button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
					<h4 class="modal-title" id="myModalLabel">Detalhes do Patrimônio</h4>
				</div>
				<div class="modal-body">
						<table class="table table-striped table-hover">
							<tbody>
								<tr class="active">
									<td><b>Tombamento:</b></td>
									<td></td>
									<td class="tombamento"></td>
									<td></td>
								</tr>
								<tr>
									<td><b>Descrição:</b></td>
									<td></td>
									<td class="descricao"></td>
									<td></td>
								</tr>
								<tr class="active">
									<td><b>Categoria:</b></td>
									<td></td>
									<td class="categoria"></td>
									<td></td>
								</tr>
								<tr>
									<td><b>Local:</b></td>
									<td></td>
									<td class="local"></td>
									<td></td>
								</tr>
								<tr class="active">
									<td><b>Situação:</b></td>
									<td></td>
									<td class="situacao"></td>
									<td></td>
								</tr>
								<tr>
									<td><b>Lotação:</b></td>
									<td></td>
									<td class="lotacao"></td>
									<td></td>
								</tr>
								<tr class="active">
									<td><b>Conservação:</b></td>
									<td></td>
									<td class="conservacao"></td>
									<td></td>
								</tr>
								<tr>
									<td><b>Conforme Relatório:</b></td>
									<td></td>
									<td class="conformeRelatorio"></td>
									<td></td>
								</tr>
								<tr class="active">
									<td><b>Data de Incorporação:</b></td>
									<td></td>
									<td class="incorporacao"></td>
									<td></td>									
								</tr>
								<tr>
								<td><b>Data de Chegada no Campus:</b></td>
								    <td></td>
									<td class="chegadaCampus"></td>
									<td></td>
								</tr>
								<tr class="active">
									<td><b>Comentário:</b></td>
									<td></td>
									<td class="comentario"></td>
									<td></td>
								</tr>
							</tbody>
						</table>
				</div>
				<div class="modal-footer">
					<button type="button" class="btn btn-default" data-dismiss="modal">Sair</button>
				</div>
			</div>
		</div>
	</div>

	<jsp:include page="../modulos/footer.jsp"></jsp:include>
</body>
</html>