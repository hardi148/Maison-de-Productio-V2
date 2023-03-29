<%@ page import="java.util.ArrayList" %>
<%@ page import="com.spring.springmvc_v_finale.model.Notification" %><%--
  Created by IntelliJ IDEA.
  User: 6805
  Date: 01/03/2023
  Time: 20:11
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>notification</title>
</head>
<%
    ArrayList<Notification> notif = (ArrayList<Notification>) request.getAttribute("notif");
%>
<body>

<div class="card">
    <div class="card-header">
        <div id="wrapper">
            <jsp:include page="../common/Header.jsp"/>

            <div class="page-content-wrapper">
                <div class="container-fluid"><a class="btn btn-link" role="button" id="menu-toggle" href="#menu-toggle"><i
                        class="fa fa-bars"></i></a>
                    <div class="row">
                        <div class="col-md-12">
                            <div>
                                <% for (Notification act : notif) { %>
                                <div class="card">
                                    <div class="card-body">
                <span class="tag tag-teal">date: <%= act.getDatenotif() %>
                </span>
                                        <h4>idaction :<%= act.getIdaction() %></h4>

                                        <p style="color: red">Description : <%= act.getMessage() %>
                                        </p>
                                    </div>
                                </div>

                                <% } %>

                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>

    </div>
    </div>
</body>
</html>
