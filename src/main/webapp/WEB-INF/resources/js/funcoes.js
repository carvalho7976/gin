$(document).ready(function() {
	
	var protocolo = window.location.protocol;
	
	$('#tombamento').focus(function() {
		$('#tombamentoInput').removeClass("form-error has-error");
		$('#tombamentoMessage').remove();
	}).blur(function() {
				
		var value = $('#tombamento').val();
		console.log(value);
		if(value.length > 0){
		
			$.ajax({
				method: "POST",
				url: protocolo + "//"+ location.host +"/gin/patrimonio/checkTombamento",
				data: "tombamento="+value+"",
				dataType: "json",
				success: function(message) {
					if(message === true){
						$('#tombamentoInput').addClass("form-error has-error");
						$('#error-tombamento').append('<span id="tombamentoMessage" class="help-block" style="display: block;">Patrimônio já existente.</span>');
					}
					
				}
			});
		}
	});
	
	$('#nomeCategoriaCadastrar').on('keyup blur', function(){
		
		var value = $('#nomeCategoriaCadastrar').val();
		$('#addCategoriaButton').removeAttr('disabled');
		$('#cadasdastrarCategoriaErrorDiv').removeClass("form-error has-error");
		$('#cadastrarCategoriaMessage').remove();
		
		if(value != ''){
		
			$.ajax({
				method: "POST",
				url: protocolo + "//"+ location.host +"/gin/patrimonio/checkCategoria",
				data: "nomeCategoria="+value,
				dataType: "json",
				success: function(existe) {
					if(!existe){
						$('#addCategoriaButton').removeAttr('disabled');
						$('#cadasdastrarCategoriaErrorDiv').removeClass("form-error has-error");
						$('#cadastrarCategoriaMessage').remove();
						
					}else{
						$('#cadasdastrarCategoriaErrorDiv').addClass("form-error has-error");
						$('#error-cadastrarCategoria').append('<span id="cadastrarCategoriaMessage" class="help-block" style="display: block;">Categoria já existente.</span>');
						$('#addCategoriaButton').attr('disabled', 'disabled');
						
					}
					
				}
			});
			
		}else{
			$('#addCategoriaButton').attr('disabled', 'disabled');
		}		
		
	});
	
	$('#nomeLocalCadastrar').on('keyup blur', function(){
		
		var value = $('#nomeLocalCadastrar').val();
		$('#addLocalButton').removeAttr('disabled');
		$('#cadasdastrarLocalErrorDiv').removeClass("form-error has-error");
		$('#cadastrarLocalMessage').remove();
		
		if(value != ''){
			$('#addLocalButton').removeAttr('disabled');
			
		}else{
			$('#addLocalButton').attr('disabled', 'disabled');
		}		
		
	});
	
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
			url : protocolo + "//" + location.host + url,
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
				$(itemForm).find('.comentario').html(element.comentario);
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
				maxlength : 140
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
				required : "Informe o tombamento do patrimônio.",
				
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
	
	$('#cadastrarCategoria').validate({
		rules : {
			nome : {
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
			nome : {
				required : "Informe o nome da categoria.",
			}
		}
		
	});
	
	$('#cadastrarLocal').validate({
		rules : {
			localizacao : {
				required : true,
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
			},
			pavimento : {
				required : "Informe o pavimento do local."
			}
		}
		
	});
	
	$('div.error-validation:has(span)').find('span').css('color', '#a94442');
	$('div.error-validation:has(span)').find('span').parent().parent().parent().addClass('has-error has-feedback');
});