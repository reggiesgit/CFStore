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
                <td>Nome</td>
                <td>Preço</td>
            </tr>
            <c:forEach items="${dbProdutos}" var="produto" >
                <tr>
                    <td>
                        <c:out value="${produto.nome}" /><br/>
                    </td>
                    <td>
                        <c:out value="${produto.precoUnitario}" /><br/>
                    </td>

                </tr>
            </c:forEach>
        </table>
    </body>
</html>
