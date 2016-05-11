$(document).ready(function() {
	
	$('a.back').click(function() {
		parent.history.back();
		return false;
	});
	
	$('.upper-fl').keyup(function() {
		$(this).val($(this).val().charAt(0).toUpperCase() + $(this).val().substr(1));
	});
	
	$('.valid-num').keyup(function() {
		$(this).val($(this).val().replace(" ",''));
		$(this).val($(this).val().replace(/[^0-9\.]/g,''));
	});
	
	$('.only-num').keyup(function() {
		$(this).val($(this).val().replace(" ",''));
		$(this).val($(this).val().replace(/[^0-9]/g,''));
	});
	
	$('#confirm-delete').on('show.bs.modal', function(e) {
		$(this).find('.btn-ok').attr('href', $(e.relatedTarget).data('href'));
	});
	
	$('#detalhe-patrimonio').on('show.bs.modal', function(e) {
		var url = $(e.relatedTarget).data('href');
		var itemForm = $(this);
		$.ajax({
			method : "GET",
			url : "http://" + location.host + url,
			dataType : "json",
			success : function(element) {
				$(itemForm).find('.tombamento').html(element.tombamento);
				$(itemForm).find('.descricao').html(element.descricao);
				$(itemForm).find('.categoria').html(element.categoria.nome);
				$(itemForm).find('.local').html(element.local.fullLocal);
				$(itemForm).find('.situacao').html(element.situacao);
				$(itemForm).find('.lotacao').html(element.lotacao);
				$(itemForm).find('.conservacao').html(element.conservacao);
				$(itemForm).find('.conformeRelatorio').html(element.conformeRelatorio);
				$(itemForm).find('.incorporacao').html(element.incorporacao);
				$(itemForm).find('.chegadaCampus').html(element.chegadaCampus);
			}
		});
	});
	
	$('#cadastrarPatrimonio').validate({
		rules : {
			tombamento : {
				required : true
			},
			descricao : {
				required : true,
				maxlength : 15
			},
			categoria : {
				required : true
			},
			local : {
				required : true
			},
			conformeRelatorio : {
				required : true
			},
			situacao : {
				required : true
			},
			lotacao : {
				required : true
			},
			conservacao : {
				required : true
			},
			incorporacao : {
				required : true
			}
		},
		highlight : function(element) {
			$(element).closest('.form-error').addClass('has-error');
		},
		unhighlight : function(element) {
			$(element).closest('.form-error').removeClass('has-error');
		},
		errorElement : 'span',
		errorClass : 'help-block',
		errorPlacement : function(error, element) {
			error.insertAfter(element.parent().children().last());
			var itemForm = element.parent();
			var id = element.attr("name");
			$(itemForm).find("span").attr("id", id);
		},
		messages : {
			tombamento : {
				required : "Informe o tombamento do patrimônio."
			},
			descricao : {
				required : "Informe uma descrição do patrimônio.",
				maxlength : "Descrição deve ser de no máximo {0} caracteres."
			},
			categoria : {
				required : "Informe a categoria do patrimônio."
			},
			local : {
				required : "Informe o local do patrimônio."
			},
			conformeRelatorio : {
				required : "Informe a conformidade com o relatório."
			},
			situacao : {
				required : "Informe a situação do patrimônio."
			},
			lotacao : {
				required : "Informe a lotação do patrimônio."
			},
			conservacao : {
				required: "Informe o estado de conservação do patrimônio."
			},
			incorporacao : {
				required : "Informe a data de incorporação do patrimônio."
			}
		}
			
	});
	
	var response;
	$.validator.addMethod(
			"uniqueCategoria",
			function(value, element) {
				
					$.ajax({
						method: "POST",
						url: "http://"+ location.host +"/gin/patrimonio/checkCategoria",
						data: "nomeCategoria="+value,
						dataType: "json",
						success: function(message) {
							response = !message;
						}
					});
				 
				 if(response != undefined) {
					 var temp = response;
					 response = undefined;
					 return temp;
				 }
			},
			"Categoria já cadastrada."
		);
	
	$('#cadastrarCategoria').validate({
		rules : {
			nome : {
				required : true,
				uniqueCategoria : true
			}
		},
		highlight : function(element) {
			$(element).closest('.form-error').addClass('has-error');
		},
		unhighlight : function(element) {
			$(element).closest('.form-error').removeClass('has-error');
		},
		errorElement : 'span',
		errorClass : 'help-block',
		errorPlacement : function(error, element) {
			error.insertAfter(element.parent().children().last());
			var itemForm = element.parent();
			var id = element.attr("name");
			$(itemForm).find("span").attr("id", id);
		},
		messages : {
			nome : {
				required : "Informe o nome da categoria.",
				uniqueCategoria : "Esta categoria já está cadastrada."
			}
		}
		
	});
	
	$.validator.addMethod(
		"uniqueLocalizacao",
		function(value, element) {
			$.ajax({
				method: "POST",
				url: "http://"+ location.host +"/gin/patrimonio/checkLocalizacao",
				data: "localizacao="+value,
				dataType: "json",
				success: function(message) {
					response = !message;
				}
			});
			if(response != undefined) {
				 var temp = response;
				 response = undefined;
				 return temp;
			 }
		},
		"Localização já cadastrada."
	);
	
	$('#cadastrarLocal').validate({
		rules : {
			localizacao : {
				required : true,
				uniqueLocalizacao: true
			},
			pavimento : {
				required : true,
			}
		},
		highlight : function(element) {
			$(element).closest('.form-error').addClass('has-error');
		},
		unhighlight : function(element) {
			$(element).closest('.form-error').removeClass('has-error');
		},
		errorElement : 'span',
		errorClass : 'help-block',
		errorPlacement : function(error, element) {
			error.insertAfter(element.parent().children().last());
			var itemForm = element.parent();
			var id = element.attr("name");
			$(itemForm).find("span").attr("id", id);
		},
		messages : {
			localizacao : {
				required : "Informe o nome do local.",
				uniqueLocalizacao : "Esta localização já está cadastrada."
			},
			pavimento : {
				required : "Informe o pavimento do local."
			}
		}
		
	});
	
	$('div.error-validation:has(span)').find('span').css('color', '#a94442');
	$('div.error-validation:has(span)').find('span').parent().parent().parent().addClass('has-error has-feedback');
});