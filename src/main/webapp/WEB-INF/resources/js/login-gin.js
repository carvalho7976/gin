$(document).ready(function() {
	
	$(".cpf").mask("99999999999");
	
	$('.message-field').addClass('hidden');
	
	$('.only-num').keyup(function() {
		$(this).val($(this).val().replace(" ",''));
		$(this).val($(this).val().replace(/[^0-9]/g,''));
	});
	
	$('#form-login').validate({
        rules: {
            
        },
        highlight: function(element) {
            $(element).closest('.form-group').addClass('has-error');
        },
        unhighlight: function(element) {
            $(element).closest('.form-group').removeClass('has-error');
        },
        errorElement: 'span',
        errorClass: 'help-block',
        errorPlacement: function(error, element) {
            error.insertAfter(element.parent().children().last());
            $("#erro").remove();
            $("#error").removeClass('hidden');
        },
        messages:{
        	j_username:{
        		required: ''
            },
            j_password:{
            	required: ''
            },
        }
    });
	
});