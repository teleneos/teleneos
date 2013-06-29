		<footer class="static-footer footer modal-footer">
			<div class="container">
				<@s.text name="page.footer" />
			</div>
		</footer>
		<script type="text/javascript" src="${ctx}/static/bootstrap/2.3.0/js/bootstrap.min.js"></script>
		<script type="text/javascript">
			$(function() {
				$('li').has('a').each(function() {
					var href = $(this).find('a').attr('href');
					var current = '${request.requestURI}';

					if (current.indexOf(href + '/') == 0 && href != '' && href != '/') {
						$(this).addClass('active');
						$(this).parent('li').addClass('active');
					} else if(href == current) {
						$(this).addClass('active');
						$(this).parent('li').addClass('active');
					}
				});
			});
		</script>