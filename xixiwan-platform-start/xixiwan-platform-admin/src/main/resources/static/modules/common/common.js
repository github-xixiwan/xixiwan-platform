function empty(id) {
	$('#' + id + ' :input').not(':button, :submit, :reset, :hidden, :radio, :checkbox').val('');
}