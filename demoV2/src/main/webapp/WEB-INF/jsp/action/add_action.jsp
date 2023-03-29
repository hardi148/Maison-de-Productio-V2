<%@ page import="java.util.ArrayList" %>
<%@ page import="java.sql.Date" %>
<%@ page import="com.spring.springmvc_v_finale.model.Plateau" %>
<%@ page import="com.spring.springmvc_v_finale.model.Personnage" %>
<%@ page import="java.time.Month" %><%--
  Created by IntelliJ IDEA.
  User: 6805
  Date: 28/02/2023
  Time: 21:29
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>ajout action</title>
</head>
<body>
<%
    ArrayList<Date> list_date = (ArrayList<Date>) request.getAttribute("list_date");
    int scene = (int) request.getAttribute("scene");
    ArrayList<Personnage> person = (ArrayList<Personnage>) request.getAttribute("person");
    ArrayList<Plateau> plateau = (ArrayList<Plateau>) request.getAttribute("plateau");
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

                                <form action="<%= request.getContextPath() %>/validate_action/<%= scene %>" method="post">
                                    <p>
                                        jour action
                                        <select name="dateAction">
                                            <option value="vide">choose day</option>
                                            <% for (Date d : list_date) { %>
                                            <option value="<%= d %>"><%= d.getDate() + " " + Month.of(d.getMonth()+1) %>
                                            </option>
                                            <% } %>
                                        </select>
                                        <input type="time" required name="fin">
                                    </p>
                                    <p>Description : <textarea name="description" rows="6" cols="40" placeholder="Enter your text here..."></textarea>
                                    </p>
                                    <p>Plateau :
                                        <select name="plateau">
                                            <% for (Plateau p : plateau) { %>
                                            <option value="<%= p.getIdplateau() %>"> <%= p.getNomplateau()  %></option>
                                            <% } %>
                                        </select>
                                    </p>
                                    <p>person :
                                        <select name="person">
                                            <option value="vide"> choose people</option>
                                            <% for (Personnage p : person) { %>
                                            <option value="<%= p.getIdperso() %>"> <%= p.getNomperso()  %></option>
                                            <% } %>
                                        </select>
                                    </p>
                                    <input type="submit" value="enregistrer">
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
