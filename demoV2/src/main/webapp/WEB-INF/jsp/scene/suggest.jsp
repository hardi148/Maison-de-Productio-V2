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
    ArrayList<Action> list = (ArrayList<Action>) request.getAttribute("list");
    ArrayList<Timestamp> listDay = (ArrayList<Timestamp>) request.getAttribute("listDay");
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
<%--                        <% for (Action act : list) { %>--%>
<%--                        <div class="col-md-6">--%>
<%--                            <div class="card" style="box-shadow: 0px 0px 5px 0px rgba(0, 0, 0, 0.5);">--%>
<%--                                <div class="card-body">--%>
<%--                                    <span class="tag tag-teal">scene: <%= act.getScene().getNom() %>--%>
<%--                                    </span>--%>
<%--                                    <h3>idaction :<%= act.getIdaction() %>--%>
<%--                                    </h3>--%>
<%--                                    <p style="color: seagreen">Description : <%= act.getDescription() %>--%>
<%--                                    </p>--%>
<%--                                    <p>plateau : <%= act.getPlateau().getNomplateau() %>--%>
<%--                                    </p>--%>
<%--                                    <h4>debut--%>
<%--                                        :<%= Suggestion.getDayOfDate(act.getPlateau().getDateDebutIndisponibilite()) %> <%= act.getPlateau().getDateDebutIndisponibilite() %>--%>
<%--                                        dure : <%= act.getDateAction() %> h</h4>--%>
<%--                                    <h4>fin--%>
<%--                                        : <%= Suggestion.getDayOfDate(act.getPlateau().getDateFinIndisponibile()) %> <%= act.getPlateau().getDateFinIndisponibile() %>--%>
<%--                                    </h4>--%>
<%--                                </div>--%>
<%--                            </div>--%>
<%--                            <br>--%>
<%--                        </div>--%>
<%--                        <% } %>--%>
                    <% for (Timestamp time : listDay) { %>
                        <div class="col-md-5">
                            <div class="card" style="box-shadow: 0px 0px 5px 0px rgba(0, 0, 0, 0.5);">
                                <div class="card-body">
                                    <span class="tag tag-teal"> <%= Suggestion.getDayOfDate(time) %>
                                    </span>
                                    <h3>date :<%= new Date(time.getTime()) %>
                                    </h3>
                                    <a href="<%= request.getContextPath() %>/calendar/detail/list_suggest?day=<%= time.getDate() %>">detail</a>
                                </div>
                            </div>
                            <br>
                        </div>
                        <% } %>
                    </div>
                    <form action="<%= request.getContextPath() %>/validate_suggest" method="post">
                        <input type="submit" class="btn btn-success" value="valider planning">
                    </form>
                </div>
            </div>
        </div>
    </div>

</div>
</body>
</html>
