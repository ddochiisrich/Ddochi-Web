<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page isErrorPage="true" %>
<% response.setStatus(200); %>
<h1>😭 SORRY 😭</h1>
<h2>The following error occurred while processing your request</h2><br>
<b>ERROR Message</b> : <%= exception.getMessage() %> <br>
<b>ERROR Type</b> : <%= exception.getClass().getName() %>