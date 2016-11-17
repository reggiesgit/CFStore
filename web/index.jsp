<%-- 
    Document   : index
    Created on : 13/11/2016, 17:50:16
    Author     : leandro
--%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
		<meta name="viewport" content="width=device-width, initial-scale=1">
		<!-- Latest compiled and minified CSS -->
		<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
		<link rel="stylesheet" type="text/css" href="style.css">
		<!-- jQuery library -->
		<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.1.1/jquery.min.js"></script>

		<!-- Latest compiled JavaScript -->
		<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
		<script src="estilo.js"></script>
	
	</head>
	<body>
            <div class= "container">
                <div class = "row">
                    <!-- header -->
                    <div class ="col-sm-12">
                        <nav class="navbar navbar-default navbar-fixed-top" role="navigation">
                            <div class="container">
                                <div class="navbar-header">
                                        <button type="button" class="navbar-toggle" data-toggle="collapse" data-target="#bs-example-navbar-collapse-1">
                                                <span class="sr-only">Toggle navigation</span>
                                                <span class="icon-bar"></span>
                                                <span class="icon-bar"></span>
                                                <span class="icon-bar"></span>
                                        </button>
                                        <a class="navbar-brand active" href="index.jsp"><span class="glyphicon glyphicon-filter"></span>CF Store</a>
                                </div>
                                <div class="navbar-right">
                                    <form class="navbar-form" action = "Loja?action=buscar" method = "POST">		
                                        <div class="input-group inputBuscar">
                                            <input type="text" name ="fragmento" class="form-control" placeholder="Buscar produtos...">
                                            <span class="input-group-btn">
                                              <button class="btn btn-default" type="submit">Buscar</button>
                                            </span>
                                        </div><!-- /input-group -->
                                   </form>
                                </div>    
                                <div class="collapse navbar-collapse" id="bs-example-navbar-collapse-1">

                                    <ul class="nav navbar-nav navbar-right">
                                        <li><a href="#"><span class="glyphicon glyphicon-user"></span>Login</a></li>
                                        <li><a href="#"><span class="glyphicon glyphicon-shopping-cart"></span>Carrinho</a></li>
                                    </ul>
                                </div><!-- /.navbar-collapse -->
                            </div><!-- /.container-fluid -->
                        </nav>
                    </div>
                </div><!-- fim do header -->
                <div class = "row">
                    <!-- menu lateral -->
                    <div class = "col-sm-3">
                        <nav class="navbar navbar-default sidebar" role="navigation">
                            <div class="container-fluid">
                                <div class="navbar-header">
                                  <button type="button" class="navbar-toggle" data-toggle="collapse" data-target="#bs-sidebar-navbar-collapse-1">
                                    <span class="sr-only">Toggle navigation</span>
                                    <span class="icon-bar"></span>
                                    <span class="icon-bar"></span>
                                    <span class="icon-bar"></span>
                                  </button>      
                                </div>
                                <div class="collapse navbar-collapse" id="bs-sidebar-navbar-collapse-1">
                                  <ul class="nav navbar-nav">
                                    <li><a href="index.jsp">Página Inicial<span style="font-size:16px;" class="pull-right hidden-xs showopacity glyphicon glyphicon-home"></span></a></li>
                                    <li><a href="Loja?action=listar&depto=Quarto">Quartos<span style="font-size:16px;" class="pull-right hidden-xs showopacity glyphicon glyphicon-bed"></span></a></li>        
                                    <li><a href="Loja?action=listar&depto=Sala de Estar">Sala de Estar<span style="font-size:16px;" class="pull-right hidden-xs showopacity glyphicon glyphicon-book"></span></a></li>        
                                    <li ><a href="Loja?action=listar&depto=Sala de Jantar">Sala de Jantar<span style="font-size:16px;" class="pull-right hidden-xs showopacity glyphicon glyphicon-apple"></span></a></li>
                                    <li ><a href="Loja?action=listar&depto=Cozinha">Cozinha<span style="font-size:16px;" class="pull-right hidden-xs showopacity glyphicon glyphicon-cutlery"></span></a></li>
                                    <li><a href="Loja?action=listar&depto=Escritório">Escritório<span style="font-size:16px;" class="pull-right hidden-xs showopacity glyphicon  glyphicon-pencil"></span></a></li>      
                                    <li ><a href="Loja?action=listar&depto=Lavanderia">Lavanderia<span style="font-size:16px;" class="pull-right hidden-xs showopacity glyphicon glyphicon-tasks"></span></a></li>
                                    <li ><a href="Loja?action=listar&depto=Banheiro">Banheiro<span style="font-size:16px;" class="pull-right hidden-xs showopacity glyphicon glyphicon-scale"></span></a></li>
                                    <li ><a href="Loja?action=listar&depto=Jardim">Jardim<span style="font-size:16px;" class="pull-right hidden-xs showopacity glyphicon glyphicon-leaf"></span></a></li>
                                  </ul>
                                </div>
                            </div>
                        </nav>
                    </div>
                    
                        <!-- conteudo principal -->
                        <%  String action = request.getParameter("action");%>
                        <% if (action == null) { %>
                            <jsp:include page="home.jsp" flush="true" />
                        <% } else if (action.equals("exibir")) { %>
                            <jsp:include page="produto.jsp" flush="true" />
                        <% } else if (action.equals("buscar")) { %>
                            <jsp:include page="resultadoBusca.jsp" flush="true" />
                        <% } else if (action.equals("listar")) { %>
                            <jsp:include page="produtosDepto.jsp" flush="true" />
                        <% } else  { %>
                            <jsp:include page="mostrar.jsp" flush="true" />
                        <% } %>
                  
                    
                </div>
                
            </div>
	</body>
</html>
