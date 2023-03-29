
<%@ page import="java.util.ArrayList" %>
<%@ page import="com.spring.springmvc_v_finale.model.Notification" %>
<%@ page import="com.spring.springmvc_v_finale.model.Action" %>
<%@ page import="com.spring.springmvc_v_finale.model.suggestion.Suggestion" %>
<%@ page import="java.sql.Timestamp" %>
<%@ page import="java.sql.Date" %><%--
  Created by IntelliJ IDEA.
  User: 6805
  Date: 01/03/2023
  Time: 20:11
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>suggestion</title>
</head>
<body>
<div class="card">
    <div class="card-header">
        <div id="wrapper">
         <jsp:include page="../common/Header.jsp"/>
            <div class="page-content-wrapper">
                <div class="container-fluid"><a class="btn btn-link" role="button" id="menu-toggle" href="#menu-toggle"><i
                        class="fa fa-bars"></i></a>
                    <div class="row">
                                   <form action="<%= request.getContextPath() %>/Login" method="post"  style="height: 525px;">

                                                <h2 class="text-center" style="margin-top: 25%;"><strong>Login Acteur</strong></h2>
                                                <div class="form-group mb-3"><input class="form-control" type="text" name="id" value="1" placeholder="Votre reference"></div>
                                                <div class="form-group mb-3"><input class="form-control" type="password" name="nom" value="Alice"  placeholder="Votre Pseudo"></div>
  <div class="form-group mb-3"><button class="btn btn-primary d-block w-100" id="submitButton" type="submit" style="color: rgb(255,255,255);background: var(--bs-gray);font-weight: bold;">SE CONNECTER</button></div>
                                            </form>
                    </div>

                </div>
            </div>
        </div>
    </div>

</div>
</body>
</html>
