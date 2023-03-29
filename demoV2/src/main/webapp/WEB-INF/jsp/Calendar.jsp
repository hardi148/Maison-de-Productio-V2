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
<%
    ArrayList<Timestamp> listDay = (ArrayList<Timestamp>) request.getAttribute("listDay");
%>
<body>

<div class="card">
    <div class="card-header">
        <div id="wrapper">
            <jsp:include page="common/Header.jsp"/>

            <div class="page-content-wrapper">
                <div class="container-fluid"><a class="btn btn-link" role="button" id="menu-toggle" href="#menu-toggle"><i
                        class="fa fa-bars"></i></a>
                    <div class="row">
                        <% for (Timestamp time : listDay) { %>
                        <div class="col-md-5">
                            <div class="card" style="box-shadow: 0px 0px 5px 0px rgba(0, 0, 0, 0.5);">
                                <div class="card-body">
                                    <span class="tag tag-teal"> <%= Suggestion.getDayOfDate(time) %>
                                    </span>
                                    <h3>date :<%= new Date(time.getTime()) %>
                                    </h3>
                                    <a href="<%= request.getContextPath() %>/calendar_plan/detail/list_calendar?day=<%= time.getDate() %>&date=<%= new Date(time.getTime()) %>">detail</a>
                                </div>
                            </div>
                            <br>
                        </div>
                        <% } %>
                    </div>

                </div>
            </div>
        </div>
    </div>

</div>
</body>
</html>
