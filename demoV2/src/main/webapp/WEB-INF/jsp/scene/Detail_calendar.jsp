<%@ page import="java.util.ArrayList" %>
<%@ page import="com.spring.springmvc_v_finale.model.Notification" %>
<%@ page import="com.spring.springmvc_v_finale.model.Action" %>
<%@ page import="com.spring.springmvc_v_finale.model.suggestion.Suggestion" %>
<%@ page import="com.spring.springmvc_v_finale.model.Calendar" %>
<%@ page import="java.sql.Time" %>
<%@ page import="java.sql.Date" %>
<%--
  Created by IntelliJ IDEA.
  User: 6805
  Date: 01/03/2023
  Time: 20:11
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Calendar</title>
</head>
<%
    ArrayList<Calendar> list = (ArrayList<Calendar>) request.getAttribute("list");
    Date date_action = (Date) request.getAttribute("date_action");
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
                        <% try { %>
                        <h1><%= Suggestion.getDayOfDate(list.get(0).getDateCalendar()) %>
                        </h1>
                        <% } catch (Exception e) {}%>
                        <% for (Calendar act : list) { %>
                        <div class="col-md-4">
                            <div class="card" style="box-shadow: 0px 0px 5px 0px rgba(0, 0, 0, 0.5);">
                                <div class="card-body">
                                    <p>scene: <%= act.getAction().getScene().getNom() %>
                                    </p>
                                    <p>plateau : <%= act.getAction().getPlateau().getNomplateau() %></p>
                                    <p style="color: seagreen">idaction :<%= act.getIdaction() %></p>
                                    <p>Description : <%= act.getAction().getDescription() %>
                                    </p>
                                    <p>Date : <%= date_action %></p>
                                    <p>Heure debut :<%= new Time(act.getDateCalendar().getTime()) %></p>
                                    <p>duree : <%= act.getAction().getDateAction() %> h</p>
                                    <p>Heure fin : <%= new Time(act.getDatefin().getTime()) %>
                                    </p>
                                    <p>Acteur:  <%= act.getAction().getActeur() %></p>
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
