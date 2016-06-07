<html>
<head>
	<jsp:include page="modulos/header.jsp"></jsp:include>
	<title>403 - Acesso negado</title>
</head>
<body>
	<div class="container">
		<div class = "error">
		    <div class="col-lg-8 col-lg-offset-2 text-center">
				<div class="logo">
					<h1>403</h1>
				</div>
				<p class="lead text-muted">${message}</p>
				<br>
				<div class="col-lg-6 col-lg-offset-3">
					<a class="btn btn-warning btn-group-justified back" >Voltar</a>
				</div>
			</div>
		</div>
	</div>
	
	<jsp:include page="modulos/footer.jsp"></jsp:include>
</body>
</html>