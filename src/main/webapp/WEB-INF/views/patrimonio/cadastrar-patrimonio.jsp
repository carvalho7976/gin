<!DOCTYPE html>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>

<c:if test="${action eq 'cadastrar' }">
	<c:url var="url" value="/patrimonio/cadastrar" ></c:url>
	<c:set var="titulo" value="Novo Patrimonio"></c:set>
	<c:set var="botao" value="Confirmar"></c:set>
</c:if>
<c:if test="${action eq 'editar' }">
	<c:url var="url" value="/patrimonio/editar"></c:url>
	<c:set var="titulo" value="Alterar Patrimonio"></c:set>
	<c:set var="botao" value="Salvar Alterações"></c:set>
</c:if>

<html>
<head>
	<jsp:include page="../header.jsp"></jsp:include>
	<title>${titulo}</title>
</head>
<body>

	<jsp:include page="../header-gin.jsp"></jsp:include>
	<br>
	<div class="container">
		<form:form id="cadastrarPatrimonio" servletRelativeAction="${url}" modelAttribute="patrimonio" method="POST" class="form-horizontal">
			<fieldset>
				<legend class="section">${titulo}</legend>

				<c:if test="${not empty erro}">
					<div class="alert alert-danger alert-dismissible" role="alert" id="alert-erro">
						<button type="button" class="close" data-dismiss="alert">
							<span aria-hidden="true">&times;</span><span class="sr-only">Close</span>
						</button>
						<c:out value="${erro}"></c:out>
					</div>
				</c:if>
				<c:if test="${not empty info}">
					<div class="alert alert-success alert-dismissible" role="alert" id="alert-info">
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
						<form:input path="tombamento" id="tombamento" class="form-control only-num valid-num" type="text" placeholder="000..."/>
						<div class="error-validation" id="error-tombamento">
							<form:errors path="tombamento"></form:errors>
						</div>
					</div>
				</div>
				<div class="form-group form-error">
					<label for="descricao" class="col-lg-2 control-label"><span class="red">*</span> Descrição</label>
					<div class="col-lg-10">
						<form:input path="descricao" id="descricao" class="form-control upper-fl" type="text" placeholder="Descrição..."/>
						<span class="help-block">Descrição simples de o que é o patrimônio.</span>
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
						<a class="btn btn-success" 
							href="<c:url value="#"></c:url>"
							data-toggle="modal"
							data-target="#cadastrar-categoria" > 
							<span class="glyphicon glyphicon-plus"></span> 
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
						<a class="btn btn-success" 
							data-href="<c:url value="#"></c:url>" 
							data-toggle="modal"
							data-target="#cadastrar-local"> 
							<span class="glyphicon glyphicon-plus"></span> 
						</a>
					</div>
				</div>
				<div class="form-group form-error">
					<label for="situacao" class="col-lg-2 control-label"><span class="red">*</span> Conformidade com Relatório</label>
					<div class="col-lg-10">
						<form:select path="conformeRelatorio" id="conformeRelatorio" class="form-control">
							<form:option value=""> Selecione uma opção</form:option>
							<form:options items="${conformeRelatorio}" itemLabel="tipo"/>
						</form:select>
						<div class="error-validation" id="error-conformeRelatorio">
							<form:errors path="conformeRelatorio"></form:errors>
						</div>
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
						<form:textarea path="comentario.mensagem" id="comentario" class="form-control" rows="3"/>
						<span class="help-block">Comentário extra sobre o patrimônio a cerca de sua situação, conversação ou quaisquer outro tipo de característica.</span>
					</div>
				</div>
				<br> 
				<div class="form-group">
					<div class="col-lg-10 col-lg-offset-2">
						<a	href="<c:url value="/patrimonio/listar"></c:url>" class="btn btn-danger">Cancelar</a>
						<button type="submit" class="btn btn-success">${botao}</button>
					</div>
				</div>
			</fieldset>
		</form:form>
	</div>

	<div id="cadastrar-categoria" class="modal" role="dialog" tabindex="-1" aria-labelledby="myModalLabel" aria-hidden="true">
		<div class="modal-dialog">
			<div class="modal-content">
				<form:form id="cadastrarCategoria" servletRelativeAction="/patrimonio/cadastrar/categoria" modelAttribute="categoria" method="POST" class="form-horizontal">
					
					<input type="hidden" name="action" value="${action}"/>
					<input type="hidden" name="idPatrimonio" value="${idPatrimonio}"/>
					
					<div class="modal-header">
						<button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
						<h4 class="modal-title" id="myModalLabel">Nova Categoria</h4>
					</div>
					<div class="modal-body">
						<div class="form-group form-error">
							<label for="nome" class="col-lg-4 control-label"><span class="red">*</span> Nome da Categoria</label>
							<div class="col-lg-8">
								<form:input path="nome" id="nome" class="form-control upper-fl" type="text" placeholder="Nome da categoria..." />
								<div class="error-validation" id="error-nome">
									<form:errors path="nome"></form:errors>
								</div>
							</div>
						</div>
					</div>
					<div class="modal-footer">
						<button type="button" class="btn btn-default" data-dismiss="modal">Cancelar</button>
						<button type="submit" class="btn btn-success">Adicionar</button>
					</div>
				</form:form>
			</div>
		</div>
	</div>

	<div id="cadastrar-local" class="modal" role="dialog" tabindex="-1" aria-labelledby="myModalLabel" aria-hidden="true">
		<div class="modal-dialog">
			<div class="modal-content">
				<form:form id="cadastrarLocal" servletRelativeAction="/patrimonio/cadastrar/local" modelAttribute="local" method="POST" class="form-horizontal">
					
					<input type="hidden" name="action" value="${action}"/>
					<input type="hidden" name="idPatrimonio" value="${idPatrimonio}"/>
					
					<div class="modal-header">
						<button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
						<h4 class="modal-title" id="myModalLabel">Novo Local</h4>
					</div>
					<div class="modal-body">
						<div class="form-group form-error">
							<label for="localizacao" class="col-lg-4 control-label"><span class="red">*</span> Localização</label>
							<div class="col-lg-8">
								<form:input path="localizacao" id="localizacao" class="form-control upper-fl" type="text" placeholder="Nome do local..." />
								<div class="error-validation" id="error-nome">
									<form:errors path="localizacao"></form:errors>
								</div>
							</div>
						</div>
						<div class="form-group form-error">
							<label for="pavimento" class="col-lg-4 control-label"><span class="red">*</span> Pavimento</label>
							<div class="col-lg-8">
								<form:input path="pavimento" id="pavimento" class="form-control upper-fl" type="text" placeholder="Pavimento..." />
								<div class="error-validation">
									<form:errors path="pavimento"></form:errors>
								</div>
							</div>
						</div>
						<div class="form-group form-error">
							<label for="bloco" class="col-lg-4 control-label"><span class="red">*</span> Bloco</label>
							<div class="col-lg-8">
								<form:input path="bloco" id="bloco" class="form-control only-num" type="text" placeholder="Bloco..." />
								<div class="error-validation">
									<form:errors path="bloco"></form:errors>
								</div>
							</div>
						</div>
					</div>
					<div class="modal-footer">
						<button type="button" class="btn btn-default" data-dismiss="modal">Cancelar</button>
						<button type="submit" class="btn btn-success">Adicionar</button>
					</div>
				</form:form>
			</div>
		</div>
	</div> <br><br>

	<jsp:include page="../footer.jsp"></jsp:include>

	<script type="text/javascript">
		$(function(){
			$("#incorporacao").datepicker({dateFormat: 'dd/mm/yy'});
			$("#chegadaCampus").datepicker({dateFormat: 'dd/mm/yy'});
		});
	</script>
</body>
</html>