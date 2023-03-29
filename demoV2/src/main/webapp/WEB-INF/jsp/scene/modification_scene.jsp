<%@ page import="com.spring.springmvc_v_finale.model.Scene" %>
<%@ page import="com.spring.springmvc_v_finale.controller.SceneController" %>
<%@ page import="java.sql.Time" %>
<%@ page import="java.time.LocalTime" %><%--
  Created by IntelliJ IDEA.
  User: 6805
  Date: 28/02/2023
  Time: 20:48
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>modification</title>
</head>
<body>
<%
    Scene scene = (Scene) request.getAttribute("scene");
    String mess = (String) request.getAttribute("mess");
%>
<div class="card">
    <div class="card-header">
        <div id="wrapper">
            <jsp:include page="../common/Header.jsp" />

            <div class="page-content-wrapper">
                <div class="container-fluid"><a class="btn btn-link" role="button" id="menu-toggle" href="#menu-toggle"><i class="fa fa-bars"></i></a>
                    <div class="row">
                        <div class="col-md-12">
                            <div>
                              <center>  <h3 style="color: #0d6efd"><%= mess %></h3></center>
                                <form action="<%= request.getContextPath() %>/update_scene/<%= scene.getIdscene() %>" class="card w-50 mt-3 p-3 mx-auto" method="post">
                                    <h1 class='mx-auto'>modification une  scene</h1>
                                    <hr width='100%'>
                                    <p>nom scene:<input type="text" value="<%= scene.getNom() %>" class="form-control" name="nom" ></p>
                                    <p class="row w-auto"><label >debut :</label><input type="date"  class="form-control w-50 m-2" value="<%= SceneController.convertTimestampTodate(scene.getDateDebut()) %>" name="debut">
                                        <input type="time" class="form-control w-auto" style="float: right" value="<%= String.valueOf(new Time(scene.getDateDebut().getTime())) %>" name="time_debut">
                                    </p>
                                    <p class="row w-auto"><label >fin :</label><input type="date" class="form-control w-50 m-2"  value="<%= SceneController.convertTimestampTodate(scene.getDateFin()) %>" name="fin">
                                        <input type="time" class="form-control w-auto" style="float: right" value="<%= String.valueOf(new Time(scene.getDateFin().getTime())) %>" name="time_fin"></p>
                                    <center> <input type="submit" class="btn btn-primary w-50" value="enregistrer"></center>
                                </form>

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
