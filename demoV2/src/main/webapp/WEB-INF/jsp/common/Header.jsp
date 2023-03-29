<%@ page import="com.spring.springmvc_v_finale.controller.SceneController" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: 6805
  Date: 01/03/2023
  Time: 10:25
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
    <meta charset="UTF-8">
  <link rel="stylesheet" href="<c:url value='/ressources/theme/bootstrap/css/bootstrap.min.css' />">
  <link rel="stylesheet" href="<c:url value='/ressources/theme/fonts/font-awesome.min.css' />">
  <link rel="stylesheet" href="<c:url value='/ressources/theme/css/Login-Form-Basic-icons.css' />">
  <link rel="stylesheet" href="<c:url value='/ressources/theme/css/Sidebar-Menu-sidebar.css' />">
  <link rel="stylesheet" href="<c:url value='/ressources/theme/css/Sidebar-Menu.css' />">
</head>
<body>
<%
  int pages =  (request.getSession().getAttribute("page")==null)? SceneController.page: (int) request.getSession().getAttribute("page");

%>
<div id="sidebar-wrapper">
  <ul class="sidebar-nav">
    <li class="sidebar-brand"> <a href="#">Acceuill</a></li>

    <li> <a href="<%= request.getContextPath() %>/create_scene">ajout scene</a></li>
    <li> <a href="<%= request.getContextPath() %>/list_scene">Liste scene</a></li>
    <li> <a href="<%= request.getContextPath() %>/alert">notifications</a></li>
    <li> <a href="<%= request.getContextPath() %>/plateau">list plateau</a></li>
    <li> <a href="<%= request.getContextPath() %>/calendar">calendar planning</a></li>
    <li>  <a href="<%= request.getContextPath() %>/versActeur">Acteur</a></li>
  </ul>
</div>
</body>
</html>
