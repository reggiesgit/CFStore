<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<div class = "container col-sm-9">
    <div class = "row">
        <c:forEach items="${dbProdutos}" var="produto">
            <div class="col-sm-3 column productbox">
                <img class="img-responsive" src="<c:out value = "${produto.imagem}"/>" >
                <div class="producttitle"><c:out value = "${produto.nome}"/></div>
                <div class="productprice"><div class="pull-right"><a href="<c:out value = "${produto.url}"/>" class="btn btn-danger btn-sm" role="button">Ver +</a></div><div class="pricetext"><fmt:formatNumber value="${produto.precoUnitario}" type="currency"/></div></div>
            </div>
        </c:forEach>
    </div>
    <div>
        <nav aria-label="Page navigation">
            <ul class="pagination">
              <li>
                <a href="#" aria-label="Previous">
                  <span aria-hidden="true">&laquo;</span>
                </a>
              </li>
              <li><a href="Loja?action=listar&depto=<c:out value="${depto}"/>&p=1">1</a></li>
              <li><a href="Loja?action=listar&depto=<c:out value="${depto}"/>&p=2">2</a></li>
              <li><a href="Loja?action=listar&depto=<c:out value="${depto}"/>&p=3">3</a></li>
              <li><a href="Loja?action=listar&depto=<c:out value="${depto}"/>&p=4">4</a></li>
              <li><a href="Loja?action=listar&depto=<c:out value="${depto}"/>&p=5">5</a></li>
              <li>
                  <a href="Loja?action=listar&depto=<c:out value="${depto}"/>&p=<c:out value="${p + 1}"/>" aria-label="Next">
                  <span aria-hidden="true">&raquo;</span>
                </a>
              </li>
            </ul>
          </nav>
    </div>
    
</div>