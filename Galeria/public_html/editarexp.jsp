<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>
<%@ page contentType="text/html;charset=windows-1252"%>
<html>
<head>
<link type="text/css" rel="stylesheet" href="css/bootstrap.min.css">
<meta http-equiv="Content-Type" content="text/html; charset=windows-1252">
<title>
Exoposicion
</title>
</head>
<body>
<h2>
Editar Exposicion
</h2><br>

<html:form action="/editarexp">
<h5>Ingrese el Codigo de Exposicion: </h5> <html:text property="cod" styleClass="form-control"/><br>

<html:submit value="Modificar" styleClass="btn btn-info"/>

</html:form>
</body>
</html>