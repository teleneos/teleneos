		<footer class="static-footer footer modal-footer">
			<div class="container">
				<@s.text name="page.footer" />
			</div>
		</footer>
		<script type="text/javascript" src="${ctx}/static/bootstrap/2.3.0/js/bootstrap.min.js"></script>
		<script type="text/javascript">
			$(function() {
				$('li').has('a[href="${request.requestURI.replaceAll('/add', '').replaceAll('/list', '')}"]').addClass('active');	
			});
		</script>