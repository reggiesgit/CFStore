
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<div class = "col-sm-9">
    <!-- localizacao -->
    <div class = "row">
            <ul class="breadcrumb">
              <li><a href="#">Home</a></li>
              <li><a href="#">Categoria</a></li>
              <li class="active">Produto</li> 
            </ul>
    </div>
    <!-- conteudo -->
    <div class = "row">
        <div class="panel panel-default  panel--styled">
            <div class="panel-body">
                <div class="col-md-12 panelTop">	
                    <div class="col-md-6">	
                        <img class="img-responsive" src="<c:out value="${item.imagem}" />" alt=""/>
                    </div>
                    <div class="col-md-6">	
                        <h4><c:out value="${item.descricao}" /></h4>
                        <h5>Por: <span class="itemPrice"><fmt:formatNumber value="${item.precoUnitario}" type="currency"/></span> à vista</h5> 
                        <hr/>
                        <a href="#"><span class="glyphicon glyphicon glyphicon-collapse-down"> </span> Detalhes da entrega</a>
                        <hr />
                        <a href="#"><span class="glyphicon glyphicon glyphicon-collapse-down"></span> Detalhes do produto</a>
                        <hr />
                        <div class="col-md-4 text-center">
                                <button class="btn btn-lg btn-add-to-cart"><span class="glyphicon glyphicon-shopping-cart"></span> Adicionar ao carrinho  </button>						
                        </div>
                    </div>
                </div>

            </div>
        </div> 
    </div>
    <!-- recomendacoes -->
    <div class = "container col-sm-12">
        <h5><a href="#mais"><span class="itemPrice" id = "mais"><span class="glyphicon glyphicon-plus-sign"></span>Veja mais produtos</span></a></h5>
        <hr/>
        <c:forEach items="${recomendacoes}" var="recomendacao">
            <div class="col-sm-3 column productbox">
                <img src="<c:out value = "${recomendacao.imagem}"/>" class="img-responsive">
                <div class="producttitle"><c:out value = "${recomendacao.descricao}"/></div>
                <div class="productprice"><div class="pull-right"><a href="<c:out value = "${recomendacao.url}"/>" class="btn btn-danger btn-sm" role="button">Ver +</a></div><div class="pricetext"><fmt:formatNumber value="${recomendacao.precoUnitario}" type="currency"/></div></div>
            </div>
        </c:forEach>
    </div>
    <!-- paginacao -->
    <div class = "row">
    </div>					
</div>