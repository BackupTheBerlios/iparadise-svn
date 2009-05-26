<%@ taglib uri="/WEB-INF/sitemesh-decorator.tld" prefix="decorator" %>

<html>
  <head>  	
    <title><decorator:title/></title>
    <link rel="stylesheet" href="../decorators/style.css">
    <decorator:head/>
  </head>
  <body>
    <table width="100%">
      <tr>
        <td class="title" colspan="2">
           <decorator:title/>
        </td>
      </tr>
      <tr>
        <td class="body">
           <decorator:body/>
        </td>
      </tr>
    </table>
    
  </body>
</html>

