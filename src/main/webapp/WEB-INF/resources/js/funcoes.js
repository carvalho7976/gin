$(document).ready(function() {
	
	$('a.back').click(function() {
		parent.history.back();
		return false;
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
	
	$('#cadastrarPatrimonio').validate({
		rules : {
			tombamento : {
				required : true
			},
			descricao : {
				required : true
			},
			categoria : {
				required : true
			},
			local : {
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
				required : "Informe uma descrição do patrimônio."
			},
			categoria : {
				required : "Informe a categoria do patrimônio."
			},
			local : {
				required : "Informe o local do patrimônio."
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
				type: "POST",
				url: "http://"+ location.host +"/gin/patrimonio/checkCategoria",
				data: "nome="+value,
				dataType: "html",
				success: function(message) {
					response = (message == 'true') ? false : true;
				}
			});
			return response;
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
				type: "POST",
				url: "http://"+ location.host +"/gin/patrimonio/checkLocalizacao",
				data: "localizacao="+value,
				dataType: "html",
				success: function(message) {
					response = (message == 'true') ? false : true;
				}
			});
			return response;
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
			},
			bloco : {
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
			},
			bloco : {
				required : "Informe o bloco do local."
				
			}
		}
		
	});
	
	$('div.error-validation:has(span)').find('span').css('color', '#a94442');
	$('div.error-validation:has(span)').find('span').parent().parent().parent().addClass('has-error has-feedback');
});