<%@ page import="java.util.ArrayList" %>
<%@ page import="com.spring.springmvc_v_finale.model.Notification" %><%--
  Created by IntelliJ IDEA.
  User: 6805
  Date: 01/03/2023
  Time: 20:11
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="java.util.List" %>
<%@ page import="java.util.ArrayList" %>
<%@ page import="java.lang.Object" %>
<%@ page import="com.spring.springmvc_v_finale.model.Acteur" %>

<%
   int id  = (int) request.getAttribute("id");
   String nom = request.getAttribute("nom").toString();
   List<Object[]> list = (List<Object[]>) request.getAttribute("list");
   ArrayList<Acteur> acteur = (ArrayList<Acteur>) request.getAttribute("acteur");
%>

<html>
<head>
    <title>notification</title>
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
                        <div class="col-md-12">

                                   <% for (Acteur a : acteur) { %>
                              <h4>Reference : <%= a.getIdperso() %></h4>
                              <h4>Acteur  : <%= a.getNomperso() %></h4>
                              <h4>Date Debut Indisponibilite : <%= a.getDateDebutIndisponibilite() %></h4>
                              <h4>Date Fin Indisponibilite : <%= a.getDateFinIndisponibile() %></h4>
                               <% } %>
                            <div>
                                <span class="tag tag-teal">Boite de Reception</span>
                             <% for (int i = 0; i < list.size(); i++) { %>
                              <% Object[] row = list.get(i); %>
                                <div class="card">
                                    <div class="card-body">

                <span class="tag tag-teal">Action Numero<%= row[9]  %></span>
                                        <h4>Scene Numero<%= row[10] %></h4>

  <a href="<%= request.getContextPath() %>/sendMail?idperso=<%= row[0]  %>&nomperso=<%= row[1]  %>&dateaction=<%= row[2]  %>&description=<%= row[3]  %>&duree=<%= row[4]  %>&nomplateau=<%= row[6]  %>&nom=<%= row[7]  %>&datedebut=<%= row[8]  %>&idaction=<%= row[9]  %>&idscene=<%= row[10]  %>&acteur=<%= row[11]  %>&datefin=<%= row[12]  %>&heure_debut=<%= row[13]  %>&heure_fin=<%= row[14]  %>">Voir l'email</a>
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
