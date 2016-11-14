
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<div class="container">
    <div class="row">
        <div class="col-md-8">
            <div class="panel panel-default  panel--styled">
                <div class="panel-body">
                    <div class="col-md-12 panelTop">
                        <div class="col-md-4">
                                <img class="img-responsive" src= "<c:out value="${item.imagem}" />" alt=""/>
                        </div>
                        <div class="col-md-8">
                                <h3><c:out value="${item.descricao}" /></h3>
                                <p>Por:  <fmt:formatNumber value="${item.precoUnitario}" type="currency"/></p>
                                <div class="col-md-4 text-center">
                                    <button class="btn btn-lg btn-add-to-cart"><span class="glyphicon glyphicon-shopping-cart"></span>   Adicionar ao carrinho</button>
                                </div>
                        </div>
                    </div>

                </div>
            </div>
        </div>
    </div>
</div>
<div class="container">
    <div class="row">
      <c:forEach items="${recomendacoes}" var="recomendacao">
          <div class="col-md-3">
              <a href="<c:out value = "${recomendacao.url}"/>">
                  <div class="panel panel-primary">
                        <div class="panel-heading"><c:out value = "${recomendacao.descricao}"/></div>
                        <div class="panel-body"><img src="<c:out value = "${recomendacao.imagem}"/>" class="img-responsive" style="width:100%" alt=""></div>
                        <div class="panel-footer"><fmt:formatNumber value="${recomendacao.precoUnitario}" type="currency"/></div>
                  </div>
              </a>
          </div>
      </c:forEach>
    </div>
</div>
