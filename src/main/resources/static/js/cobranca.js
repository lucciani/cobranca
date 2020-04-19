
$('#confirmacaoExclusaoModal').on('show.bs.modal', function(event) {
	var button = $(event.relatedTarget);
	var codigoTitulo = button.data('codigo');
	var descricaoNome = button.data('nome');
	var form = $(this).find('form');	
	form.attr('action', '/titulos/delete/' + codigoTitulo);
	
	$('.modal-body span').html('Tem certeza que deseja excluir o título <strong>' + descricaoNome + '</strong>?');
});

/*
 * Efeito para exibir efeito com a funcionalidade no botão(editar/excluir) na aba ação
 * 
 * */
$(function(){
	$('[rel="tooltip"]').tooltip();
})


/*
$('#confirmacaoExclusaoModal').on('show.bs.modal', function(event) {
	var button = $(event.relatedTarget);
	
	var codigoTitulo = button.data('codigo');
	var descricaoNome = button.data('nome');
	
	var modal = $(this);
	var form = modal.find('form');
	var action = form.attr('url-base');
	if (!action.endsWith('/')) {
		action += '/';
	}
	form.attr('action', action + codigoTitulo);
	
	modal.find('.modal-body span').html('Tem certeza que deseja excluir o título <strong>' + descricaoNome + '</strong>?');
});*/