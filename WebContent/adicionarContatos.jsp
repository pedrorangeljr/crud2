<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Cadastrar Contatos</title>
</head>
<body>

	<center>
		<h1>Gerenciamento de Contatos</h1>
		<h2>
			&nbsp;&nbsp;&nbsp; <a href="">Lista
				Contatos</a>

		</h2>
	</center>

	<div align="center">

		<form action="ServletsLivro" method="post">

			<table border="1" cellpadding="5">
				<caption>
					<h2>Cadastrar Contatos</h2>
				</caption>

				<tr>
					<th>ID:</th>
					<td><input type="text" id="id" name="id" placeholder="ID"
						readonly="readonly" value=""></td>
				</tr>

				<tr>
					<th>Nome:</th>
					<td><input type="text" id="nome" name="nome" size="45"
						value="" /></td>
				</tr>
				<tr>
					<th>Telefone:</th>
					<td><input type="text" id="telefone" name="telefone" size="45"
						value="" /></td>
				</tr>
				<tr>
					<th>E-mail:</th>
					<td><input type="text" id="email" name="email" size="45"
						value="" /></td>
				</tr>
				<tr>
					<td colspan="2" align="center"><input type="submit"
						value="Cadastrar" /></td>
				</tr>

			</table>

		</form>
	</div>

</body>
</html>