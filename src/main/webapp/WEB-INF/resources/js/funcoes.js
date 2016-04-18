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
			list_de_lotacao : {
				required : true
			},
			conservacao : {
				required : true
			},
			data_incorporacao : {
				required : true
			},
			data_chegada_campus : {
				required : true
			},
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
			list_de_lotacao : {
				required : "Informe a lotação do patrimônio."
			},
			conservacao : {
				required: "Informe o estado de conservação do patrimônio."
			},
			data_incorporacao : {
				required : "Informe a data de incorporação do patrimônio."
			},
			data_chegada_campus : {
				required : "Informe a data de chegada do patrimônio no campus."
			}
		}
			
	});
	
	$('#cadastrarCategoria').validate({
		rules : {
			nome : {
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
			nome : {
				required : "Informe o nome da categoria."
			}
		}
		
	});
	
	$('#cadastrarLocal').validate({
		rules : {
			nome : {
				required : true
			},
			pavimento : {
				required : true
			},
			bloco : {
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
			nome : {
				required : "Informe o nome do local."
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