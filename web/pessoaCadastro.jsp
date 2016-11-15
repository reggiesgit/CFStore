<%-- 
    Document   : pessoaCadastro
    Created on : 23/10/2016, 14:52:48
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

        <form action="PessoaController?action=cadastrar" method="POST" style="text-align: center" >
            <br/>
            Informações pessoais:<br/>
            <input type="number" required="true" name="documento" placeholder="CPF" maxlength="11" style="border: none; width: 80%; height: 40px"/> <br/><br/>
            <input type="text" required="true" name="nome" placeholder="Nome" style="border: none; width: 80%; height: 40px"/><br/><br/>
            <input type="text" required="true" name="sobrenome" placeholder="Sobrenome" style="border: none; width: 80%; height: 40px"/><br/><br/>
            <input type="text" required="true" name="email" placeholder="E-Mail" style="border: none; width: 80%; height: 40px"/><br/><br/>
            <input type="text" required="true" name="senha1" placeholder="Informe uma senha" style="border: none; width: 80%; height: 40px"/><br/><br/>
            <input type="text" required="true" name="senha2" placeholder="Confirmação da senha" style="border: none; width: 80%; height: 40px"/><br/><br/>
            <!--<input type="number" required="true" name="nascimento" placeholder="Data de nascimento" style="border: none; width: 80%; height: 40px"/><br/><br/>-->
            Informações endereço:<br/>
            <input type="number" required="true" name="cep" placeholder="CEP" style="border: none; width: 80%; height: 40px"/><br/><br/>
            <input type="text" required="true" name="rua" placeholder="Rua" style="border: none; width: 80%; height: 40px"/><br/><br/>
            <input type="number" required="true" name="numero" placeholder="Número" style="border: none; width: 80%; height: 40px"/><br/><br/>
            <input type="text" required="true" name="complemento" placeholder="Complemento" style="border: none; width: 80%; height: 40px"/><br/><br/>
            <input type="text" required="true" name="bairro" placeholder="Bairro" style="border: none; width: 80%; height: 40px"/><br/><br/>
            <input type="text" required="true" name="cidade" placeholder="Cidade" style="border: none; width: 80%; height: 40px"/><br/><br/>
            <input type="text" required="true" name="estado" placeholder="Estado" style="border: none; width: 80%; height: 40px"/><br/><br/>
            <input type="number" required="true" name="celular" placeholder="Celular" style="border: none; width: 80%; height: 40px"/><br/><br/>
            
            <input type="submit" value="Cadastrar"/>
        </form>
    </body>
</html>
