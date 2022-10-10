<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="security" uri="http://www.springframework.org/security/tags" %>
<%@ page import="com.spring.crmapp.utils.SortUtils" %>

<!-- Main Page display Client list | show content based on rights -->

<!DOCTYPE html>
<html>
<head>	
	<title> Client List</title>
	
		<link type="text/css" rel="stylesheet"
		 		 href="${pageContext.request.contextPath}/resources/css/style.css" />
</head>
<body>
		<div id="wrapper">
			<div id="header">
				<h1>Client Relationship Manager</h1>
			</div>
		</div>
		<div id="container">
			<div id="content">
				<p>
				User: <security:authentication property="principal.username" />, Role(s): <security:authentication property="principal.authorities" />
				</p>
				
				<security:authorize access="hasAnyRole('MANAGER', 'ADMIN')">
				<input type="button" value="Add Client"
					   onclick="window.location.href='addClient'; return false;"
					   class="add-button"/>
			</security:authorize>
				
				 <!--  search box -->
            <form:form action="search" method="GET">
                Search client: <input type="text" name="clientName" />
                
                <input type="submit" value="Search" class="add-button" />
            </form:form>
            
            <!-- config sort links in table headers -->
          	  <c:url var="sortLinkFirstName" value="/client/list">
					<c:param name="sort" value="<%= Integer.toString(SortUtils.FIRST_NAME) %>" />
				</c:url>
				<c:url var="sortLinkLastName" value="/client/list">
					<c:param name="sort" value="<%= Integer.toString(SortUtils.LAST_NAME) %>" />
				</c:url>
				<c:url var="sortLinkEmail" value="/client/list">
					<c:param name="sort" value="<%= Integer.toString(SortUtils.EMAIL) %>" />
				</c:url>
				
					<table>
				<tr>
					<th><a href="${sortLinkFirstName}">First Name</a></th>
					<th><a href="${sortLinkLastName}">Last Name</a></th>
					<th><a href="${sortLinkEmail}">Email</a></th>
					
					<%-- This section is only for ADMIN and MANAGER Role--%>
					<security:authorize access="hasAnyRole('MANAGER', 'ADMIN')">	
					<th>Action</th>
					</security:authorize>
				</tr>
				
				<!--  populating table -->
				<c:forEach var="tempClient" items="${clients}">
				
					<!-- construct an "update" link with client id -->
					<c:url var="updateLink" value="/client/updateClient">
						<c:param name="clientId" value="${tempClient.id}" />
					</c:url>					

					<!-- construct an "delete" link with client id -->
					<c:url var="deleteLink" value="/client/delete">
						<c:param name="clinetId" value="${tempClient.id}" />
					</c:url>
					
						<tr>
						<td> ${tempClient.firstName} </td>
						<td> ${tempClient.lastName} </td>
						<td> ${tempClient.email} </td>

						<security:authorize access="hasAnyRole('MANAGER', 'ADMIN')">						
							<td>
								<security:authorize access="hasAnyRole('MANAGER', 'ADMIN')">
									<!-- display the update link -->
									<a href="${updateLink}">Update</a>
								</security:authorize>
								<security:authorize access="hasAnyRole('ADMIN')">
									<a href="${deleteLink}"
									   onclick="if (!(confirm('Do you want to delete this client?'))) return false">Delete</a>
								</security:authorize>
							</td>
						</security:authorize>						
					</tr>
				</c:forEach>	
			</table>
			</div>
		</div>
			<p></p>
		
	<!-- logout & submit button -->
	<form:form action="${pageContext.request.contextPath}/logout" 
			   method="POST">
		<input type="submit" value="Logout" class="add-button" />
	</form:form>
</body>
</html>