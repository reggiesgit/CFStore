<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<div class = "container col-sm-9">
        
    <c:forEach items="${dbProdutos}" var="produto">
        <div class="col-sm-3 column productbox">
            <img src="http://placehold.it/210x210" class="img-responsive">
            <div class="producttitle"><c:out value = "${produto.nome}"/></div>
            <div class="productprice"><div class="pull-right"><a href="<c:out value = "${produto.url}"/>" class="btn btn-danger btn-sm" role="button">Ver +</a></div><div class="pricetext"><fmt:formatNumber value="${produto.precoUnitario}" type="currency"/></div></div>
        </div>
    </c:forEach>
</div>