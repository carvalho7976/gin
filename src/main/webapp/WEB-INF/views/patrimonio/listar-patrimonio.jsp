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
</head>
<body>
<jsp:include page="../menu.jsp"></jsp:include>
</br>
<div class="table-responsive">
		<table class="table table-bordered" id="tablePatrimonio">
			<thead>
				<tr class="info">
					<th class ="no-sort">Tombamento</th>
					<th class ="no-sort">Descrição</th>
					<th>Categoria</th>
					<th>Situação</th>
					<th>Lotação</th>
					<th>Local</th>
					<th>Conservação</th>
					<th>Data de Incorporação</th>
					<th>Chegana no campus</th>
					<th>Comentário</th>
					<th>#</th>
				</tr>
			</thead>
			<tbody>
				<c:forEach items="${patrimonios}" var="patrimonio">
					<tr >
						<td>${patrimonio.tombamento}</td>
						<td>${patrimonio.descricao} </td>
						<td>${patrimonio.categoria.nome}</td>
						<td>${patrimonio.situacao}</td>
						<td>${patrimonio.list_de_lotacao}</td>
						<td>${patrimonio.local.fullLocal}</td>
						<td>${patrimonio.conservacao}</td>
						<td> <fmt:formatDate pattern="dd/MM/yyyy" value="${patrimonio.data_incorporacao}" /></td>
						<td> <fmt:formatDate pattern="dd/MM/yyyy" value="${patrimonio.data_chegada_campus}" /></td>
						<td>${patrimonio.comentario.mensagem}</td>
						<td> <a 
							href="<c:url value="/patrimonio/editar/${patrimonio.id }/" ></c:url>">
								<button type="button" class="btn btn-info btn-xs" style="margin: 1px">
									Editar&nbsp Patrimonio
								</button>
						</a> 
						<a
							href="<c:url value="/patrimonio/excluir/${patrimonio.id }/" ></c:url>">
								<button type="button" class="btn btn-danger btn-xs" style="margin: 1px">
									Excluir Patrimonio
								</button>
						</a></td>
												
					</tr>
				</c:forEach>
			</tbody>
		</table>
		
	</div>
	
	
</body>

</html>