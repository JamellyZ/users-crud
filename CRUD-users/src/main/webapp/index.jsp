<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1" import="java.util.*" import="com.UsersMODAL.Usuarios"%>
 
<!DOCTYPE html>
<html>
<head>
<style>
.grid-container {
  display: grid;
  grid-template: 50px / auto auto auto auto;
  grid-gap: 10px;
  background-color: #2196F3;
  padding: 10px;
}

.grid-container > div {
  background-color: rgba(255, 255, 255, 0.8);
  text-align: center;
  padding: 20px 0;
  font-size: 13px;
}

.titulo{
	font-size: 18px;
	font-weight: bold;
}
</style>
<meta charset="ISO-8859-1">
<title>PITANG JAVA JR</title>
</head>
<body>

<h3> CADASTRAR </h3>
<form action = "ProcessInfo" method = "post">

	<label>Nome: </label>
	<input type="text" name="nome"><br><br>
	<label>Email: </label>
	<input type="text" name="email"><br><br>
	<label>Senha: </label>
	<input type="password" name="senha"><br><br>
	
	<div id="phone">
		<label>DDD: </label>
		<input type="text" name="ddd">
		<label>Telefone: </label>
		<input type="text" name="telefone">
		<label>Tipo(Celular ou Fixo): </label>
		<input type="text" name="tipo"><br><br>
	</div>
	
	<input type="submit" name="Send"><br><br>

</form>

<%
if(request.getAttribute("listaDeUsuarios") != null){
	
	
	@SuppressWarnings("unchecked")
	List<Usuarios> listaDeUsuarios = (List<Usuarios>)request.getAttribute("listaDeUsuarios");
	
	out.print("<div class='grid-container'><div class='titulo'>ID</div><div class='titulo'>NOME</div><div class='titulo'>E-MAIL</div><div class='titulo'>TELEFONE</div>");
	for(Usuarios u: listaDeUsuarios){
		out.print("<div class=item"+u.getId()+" id="+u.getId()+">"+u.getId()+"</div>");
		out.print("<div class=item"+u.getId()+" id="+u.getId()+">"+u.getNome()+"</div>");
		out.print("<div class=item"+u.getId()+" id="+u.getId()+">"+u.getEmail()+"</div>");
		out.print("<div class=item"+u.getId()+" id="+u.getId()+">"+u.getTelefone()+"</div>");
		out.print("<div class=item"+u.getId()+" id="+u.getId()+">"+u.getTelefone()+"</div>");
		
		
	}
	
}

%>

</body>
</html>