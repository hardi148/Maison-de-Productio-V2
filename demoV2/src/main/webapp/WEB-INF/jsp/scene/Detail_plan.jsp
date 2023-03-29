<%@ page import="java.util.ArrayList" %>
<%@ page import="com.spring.springmvc_v_finale.model.Notification" %>
<%@ page import="com.spring.springmvc_v_finale.model.Action" %>
<%@ page import="com.spring.springmvc_v_finale.model.suggestion.Suggestion" %>
<%@ page import="java.sql.Timestamp" %>
<%@ page import="java.sql.Time" %><%--
  Created by IntelliJ IDEA.
  User: 6805
  Date: 01/03/2023
  Time: 20:11
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>detail</title>
</head>
<%
    ArrayList<Action> list = (ArrayList<Action>) request.getAttribute("list");
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
                                                <% for (Action act : list) { %>
                                                <div class="col-md-6">
                                                    <div class="card" style="box-shadow: 0px 0px 5px 0px rgba(0, 0, 0, 0.5);">
                                                        <div class="card-body">
                                                            <span class="tag tag-teal">scene: <%= act.getScene().getNom() %>
                                                            </span>
                                                            <h3>idaction :<%= act.getIdaction() %>
                                                            </h3>
                                                            <p style="color: seagreen">Description : <%= act.getDescription() %>
                                                            </p>
                                                            <p>plateau : <%= act.getPlateau().getNomplateau() %>
                                                            </p>
                                                            <h4>debut :<%= new Time(act.getPlateau().getDateDebutIndisponibilite().getTime()) %> dure
                                                                : <%= act.getDateAction() %> h</h4>
                                                            <h4>fin : <%= new Time(act.getPlateau().getDateFinIndisponibile().getTime()) %>
                                                        </div>
                                                    </div>
                                                    <br>
                                                </div>
                                                <% } %>
                    </div>
<%--                    <form action="<%= request.getContextPath() %>/validate_suggest" method="post">--%>
<%--                        <input type="submit" class="btn btn-success" value="valider planning">--%>
<%--                    </form>--%>
                </div>
            </div>
        </div>
    </div>

</div>
</body>
</html>
