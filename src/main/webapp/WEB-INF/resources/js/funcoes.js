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
	
	$('div.error-validation:has(span)').find('span').css('color', '#a94442');
	$('div.error-validation:has(span)').find('span').parent().parent().parent().addClass('has-error has-feedback');
});