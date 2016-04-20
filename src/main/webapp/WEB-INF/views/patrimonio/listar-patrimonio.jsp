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
	<title>Lista de Patrimonios</title>
	
	<style type="text/css">
		.dataTables_wrapper {
   			margin-left: 10px;
    		margin-right: 10px;
    		margin-top: 20px;
		}
	</style>
	
</head>
<body>
	<jsp:include page="../menu.jsp"></jsp:include>
	
		<div class="table-responsive">
			<table class="table table-bordered" id="tablePatrimonio">
				<thead>
					<tr class="info">
						<th class="no-sort">Tombamento</th>
						<th class="no-sort">Descrição</th>
						<th>Categoria</th>
						<th>Situação</th>
						<th>Lotação</th>
						<th>Local</th>
						<th>Conservação</th>
						<th>Incorporação</th>
						<th>Chegada no Campus</th>
						<th>Comentário</th>
						<th>#</th>
					</tr>
				</thead>
				<tbody>
					<c:forEach items="${patrimonios}" var="patrimonio">
						<tr>
							<td>${patrimonio.tombamento}</td>
							<td>${patrimonio.descricao}</td>
							<td>${patrimonio.categoria.nome}</td>
							<td>${patrimonio.situacao.tipo}</td>
							<td>${patrimonio.lotacao.tipo}</td>
							<td>${patrimonio.local.fullLocal}</td>
							<td>${patrimonio.conservacao.tipo}</td>
							<td><fmt:formatDate pattern="dd/MM/yyyy" value="${patrimonio.incorporacao}" /></td>
							<td><fmt:formatDate pattern="dd/MM/yyyy" value="${patrimonio.chegadaCampus}" /></td>
							<td>${patrimonio.comentario.mensagem}</td>
							<td><a
								href="<c:url value="/patrimonio/editar/${patrimonio.id }/" ></c:url>">
									<button type="button" class="btn btn-info btn-xs" style="margin: 1px">Editar&nbsp Patrimonio</button>
							</a> <a 
									data-href="<c:url value="/patrimonio/excluir/${patrimonio.id }"></c:url>"
									data-toggle="modal" 
									data-target="#confirm-delete"
									class="btn btn-danger btn-xs"> Excluir Patrimônio
							</a> </td>

						</tr>
					</c:forEach>
				</tbody>
			</table>
		</div>
		
		<div id="confirm-delete" class="modal" role="dialog" tabindex="-1" aria-labelledby="myModalLabel" aria-hidden="true">
			<div class="modal-dialog">
				<div class="modal-content">
					<div class="modal-header">
						<button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
						<h4 class="modal-title" id="myModalLabel">Confirmar Deleção</h4>
					</div>
					<div class="modal-body">
						<p>Você está a ponto de excluir um patrimônio do estoque, esse procedimento é irreversível.</p>
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