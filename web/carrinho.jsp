<%-- 
    Document   : carrinho
    Created on : 10/11/2016, 15:49:46
    Author     : Regis
--%>

<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <jsp:include page="css.jsp"/>
        <table>
            <caption>Produtos no carrinho</caption><br/>
            <tr>
                <td>Código</td>
                <td>Nome</td>
                <td>Preço</td>
            </tr>
            <c:forEach items="${itens}" var="produto" >
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
                </tr>
            </c:forEach>
        </table>
    </body>
</html>
