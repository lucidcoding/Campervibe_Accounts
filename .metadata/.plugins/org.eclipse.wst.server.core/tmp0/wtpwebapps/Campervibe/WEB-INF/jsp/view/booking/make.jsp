<%--@elvariable id="viewModel" type="uk.co.lucditysoftware.campervibe.site.viewmodels.booking"--%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html>
    <head>
        <title>Make Booking</title>
    </head>
    <body>
    	<h1>Make Booking</h1>
		<form method="POST" action="make" role="form" class="form-horizontal" >
	        <input type="hidden" name="action" value="make"/>
			<div class="form-group" >
				<label class="control-label col-sm-3" for="vehicleId">Vehicle</label>
		        <div class="col-sm-6">
		        	<select id="vehicleId" name="vehicleId" class="form-control">
		    			<c:forEach items="${viewModel.vehicleOptions}" var="option">
		        			<option value="${option.value}" >${option.text}</option>
						</c:forEach>
		        	</select>
		        </div>
		    </div>
		    <div class="form-group" >
				<label class="control-label col-sm-3" for="startDate">Start Date</label>
		        <div class="col-sm-6">
		        	<input id="startDate" name="startDate" type="date" class="form-control" />
		        </div>
		    </div>
		    <div class="form-group" >
				<label class="control-label col-sm-3" for="endDate">End Date</label>
		        <div class="col-sm-6">
		        	<input id="endDate" name="endDate" type="date" class="form-control" />
		        </div>
		    </div>
		    <div class="form-group">        
		        <div class="col-sm-offset-3 col-sm-9">
		            <button type="submit" class="btn btn-success">Submit</button>
		        </div>
		    </div>
	    </form>
    </body>
</html>