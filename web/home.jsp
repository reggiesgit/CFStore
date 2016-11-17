<%-- 
    Document   : home
    Created on : 09/10/2016, 14:59:19
    Author     : Regis
--%>

<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Bem vindo à CFStore!</title>
    </head>
    <body>
        <jsp:include page="css.jsp"/>
        <form action="LojaController?action=buscar" method="POST">
            <br/>
            <input type="text" name="fragmento" placeholder="Filtrar produtos" style="border: none; width: 90%; height: 40px"/>
            <input type="submit" value="Filtrar" style="width: 9%; height: 40px"/><br/><br/>
        </form>    
        <table>
            <caption>Produtos</caption><br/>
            <tr>
                <td>Código</td>
                <td>Nome</td>
                <td>Preço</td>
                <td>Açao</td>
            </tr>
            <c:forEach items="${dbProdutos}" var="produto" >
                <tr>
                    <td>
                        <c:out value="${produto.id}" /><br/>
                    </td>
                    <td>
                        <c:out value="${produto.nome}" /><br/>
                    </td>
                    <td>
                        <c:out value="${produto.precoUnitario}" /><br/>
                    </td>
                    <td>
                        <a href="./LojaController?idDetalhe=${produto.id}&action=verDetalhe">Ver produto</a> <br/>
                    </td>

                </tr>
            </c:forEach>
        </table>
        <div class="center">
            <ul class="pagination">
                <li><a href="#">&laquo;</a></li>
                <li><a href="#">1</a></li>
                <li><a href="#">2</a></li>
                <li><a href="#">3</a></li>
                <li><a href="#">4</a></li>
                <li><a href="#">5</a></li>
                <li><a href="#">6</a></li>
                <li><a href="#">&raquo;</a></li>
            </ul>
        </div>
        <br/>
        <a href='pessoaCadastro.jsp' style="float:right; text-decoration: underline; color: #000; font-size: 150%">Cadastre-se</a><br/><br/>
        <a href='./CarrinhoController?action=listar' style="float:right; text-decoration: underline; color: #000; font-size: 150%">Ver carrinho</a><br/><br/>
    </body>
</html>
