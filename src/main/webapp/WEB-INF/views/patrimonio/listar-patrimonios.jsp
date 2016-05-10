<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>

<!DOCTYPE html>
<html>
<head>
	<jsp:include page="../header.jsp"></jsp:include>
	<title>Patrimônios</title>
</head>
<body>

	<jsp:include page="../header-gin.jsp"></jsp:include>

	<div class="container">
		<h3 class="section">Patrimônios da UFC/Quixadá</h3>
		
		<div></div>

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
								href="<c:url value="#"></c:url>"> <span
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

	<div id="confirm-delete" class="modal" role="dialog" tabindex="-1"
		aria-labelledby="myModalLabel" aria-hidden="true">
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

	<jsp:include page="../footer.jsp"></jsp:include>
</body>
</html>