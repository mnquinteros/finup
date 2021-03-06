<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<div>
	<a href="carga-gastos.jsp" class="btn btn-circle btn-success" data-toggle="modal" data-target="#addGastoModal">+</a>

    <h1>Filtrar por categoria <small>(<i class="glyphicon glyphicon-filter"></i>)</small></h1>
    	<div class="row">
			<div class="col-md-6">
				<div class="panel panel-primary">
					<div class="panel-heading">
						<h3 class="panel-title">Categorias de gastos</h3>
						<div class="pull-right">
							<span class="clickable filter" data-toggle="tooltip" title="Toggle table filter" data-container="body">
								<i class="glyphicon glyphicon-filter"></i>
							</span>
						</div>
					</div>
					<div class="panel-body">
						<input type="text" class="form-control" id="dev-table-filter" data-action="filter" data-filters="#dev-table" placeholder="Filter Developers" />
					</div>
					<table class="table table-hover" id="dev-table">
						<thead>
            				<tr>
                				<th>Categoria</th>
                				<th>Descripcion</th>
                				<th>Monto</th>
                				<th colspan="2">Action</th>
            				</tr>
        				</thead>
        				
        				<tbody>
            				<c:forEach items="${gastos}" var="gasto">
                			<tr>
                    			<td><c:out value="${gasto.categoria }" /></td>
                    			<td><c:out value="${gasto.descripcion }" /></td>
                    			<td><c:out value="${gasto.monto }" /></td>
                    			<td><a class="editGasto" data-pid="${gasto.id}" href="#">Update</a></td>
                    			<td><a class="delGasto" data-pid="${gasto.id}" href="#">Delete</a></td>
                			</tr>
            			    </c:forEach>
        				</tbody>
					</table>
				</div>
			</div>
		</div>
	</div>