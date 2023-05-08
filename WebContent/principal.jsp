<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Cadastro de Clientes</title>
</head>
<body>

	<br />

	<center>
		<h1>Gerenciamentos de Contatos</h1>
		<h2>
			<a href="adicionarContatos.jsp">Adicionar Contatos</a> &nbsp;&nbsp;&nbsp;

		</h2>
	</center>
	<div align="center">
		<table border="1" cellpadding="5">
			<caption>
				<h2>Lista de Contatos</h2>
			</caption>
			<tr>
				<th>ID</th>
				<th>Nome</th>
				<th>Telefone</th>
				<th>E-mail</th>
				<th>Ações</th>
			</tr>
			<c:forEach items="${livros}" var="livro">
				<tr>
					<td><c:out value="${livro.id }"></c:out></td>
					<td><c:out value="${livro.titulo}"></c:out></td>
					<td><c:out value="${livro.autor}"></c:out></td>
					<td><c:out value="${livro.preco}"></c:out></td>
					<td><a href="ServletsLivro?acao=editar&livro=${livro.id }" />Editar</a>
						&nbsp;&nbsp;&nbsp;&nbsp; <a
						href="ServletsLivro?acao=delete&livro=${livro.id }"
						onclick="return confirm('Tem certeza que deseja excluir ?')">Excluir</a></td>
				</tr>
			</c:forEach>
		</table>

		<h3 style="text-align: center">${mensagem }</h3>
	</div>
</body>
</html>