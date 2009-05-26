<%@ taglib uri="/WEB-INF/sitemesh-decorator.tld" prefix="decorator"%>
<%@ taglib uri="/WEB-INF/sitemesh-page.tld" prefix="page"%>
<html>
<head>
<title><decorator:title /></title>
<link rel="stylesheet" href="../decorators/style.css">
<decorator:head />
</head>
<body>
<table width="100%">
	<tr>
		<td class="header" colspan="2"><page:applyDecorator name="header">
           		This is header.
           </page:applyDecorator></td>
	</tr>
	<tr>
		<td width="30%"><page:applyDecorator name="menu">
           		This is menu.
           </page:applyDecorator></td>
		<td width="70%">
		<table width="100%">
			<tr>
				<td class="title"><decorator:title /></td>
			</tr>
			<tr>
				<td class="body"><decorator:body /></td>
			</tr>
		</table>
		</td>
	</tr>
	<tr>
		<td class="footer" colspan="2"><page:applyDecorator name="footer">
           		This is footer.
           </page:applyDecorator></td>
	</tr>
</table>
</body>
</html>

