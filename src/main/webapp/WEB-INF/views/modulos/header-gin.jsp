<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<nav class="navbar navbar-inverse navbar-fixed-top">
	<div class="container">
		<div class="navbar-header">
			<button type="button" class="navbar-toggle collapsed" data-toggle="collapse" data-target="#navbar-gin">
				<span class="sr-only">GIN</span>
				<span class="icon-bar"></span>
				<span class="icon-bar"></span>
				<span class="icon-bar"></span>
			</button>
			<a class="navbar-brand" href="<c:url value="/patrimonio/listar"/>">
				<span class="fa fa-group"></span> Gestão de Inventário
			</a>
		</div>
		<div class="collapse navbar-collapse" id="navbar-gin">
			<ul class="nav navbar-nav menu">
				<li id="inventario"> <a href="<c:url value="/patrimonio/listar"/>"><span class="fa fa-group"></span> Patrimônios</a></li>
			</ul>
			
			<ul class="nav navbar-nav menu">
				<li id="inventario"> <a href="<c:url value="/patrimonio/downloadPDF"/>"><span class="fa fa-group"></span> Relatórios</a></li>
			</ul>
		</div>
	</div>
</nav>
<br><br><br>