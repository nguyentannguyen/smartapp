<%@ page import="com.smartapp.openid.CustomUserDetails" %>
<%@ page import="org.springframework.security.core.context.SecurityContextHolder" %>
<%@ page import="org.springframework.security.core.Authentication" %>
<%@ page import="org.springframework.security.authentication.AnonymousAuthenticationToken" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<body>

<h2>Hello World!</h2>

<%
    CustomUserDetails userDetails = null;
    Authentication auth = SecurityContextHolder.getContext().getAuthentication();
    if (!(auth instanceof AnonymousAuthenticationToken)) {
        userDetails = (CustomUserDetails) auth.getPrincipal();
    }
%>

<% if (userDetails!=null) {%>

<p>
<table>
    <tr>
        <td colspan="2"><b>You have been authenticated!</b></td>
    </tr>
    <tr>
        <td>Name:</td>
        <td><%= userDetails.getName() %></td>
    </tr>
    <tr>
        <td>User email</td>
        <td><%= userDetails.getEmail() %></td>
    </tr>
    <tr>
        <td width="300">OpenId:</td>
        <td><%= userDetails.getUsername() %></td>
    </tr>
</table>
</p>

<% } %>

</body>
</html>
