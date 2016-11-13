<%-- 
    Document   : detalhe
    Created on : 11/11/2016, 10:01:55
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
            <caption>Detalhe do produto</caption><br/>
            <tr>
                <td>Código</td>
                <td>Nome</td>
                <td>Preço</td>
                <td>Unidades</td>
                <td>Carrinho</td>
                </tr>
            </tr>
            <%--<c:forEach items="${produto}" var="produto" >--%>
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
                        <input type="number" required="true" name="unidades" maxlength="3"/>
                    </td>
                    <td>
                        <a href="./CarrinhoController?idAdd=${produto.id}&action=addCarrinho">Adicionar</a>  
                        <a href="./CarrinhoController?idRemove=${produto.id}&action=removeCarrinho">Remover</a>
                    </td>

                </tr>
            <%--</c:forEach>--%>
        </table>
    </body>
</html>
