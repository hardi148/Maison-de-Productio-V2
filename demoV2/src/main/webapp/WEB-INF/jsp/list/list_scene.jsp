<%@ page import="java.util.ArrayList" %>
<%@ page import="com.spring.springmvc_v_finale.model.View_scene" %>
<%@ page import="com.spring.springmvc_v_finale.model.Scene" %>
<%@ page import="com.spring.springmvc_v_finale.controller.SceneController" %><%--
  Created by IntelliJ IDEA.
  User: 6805
  Date: 01/03/2023
  Time: 10:57
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
<meta charset="UTF-8">
    <title>Title</title>
</head>
<style>
    .pagination {
        display: flex;
        justify-content: center;
        align-items: center;
        margin-top: 20px;
    }

    .page-link {
        color: #007bff;
        border-color: #007bff;
    }

    .page-link:hover {
        color: #fff;
        background-color: #007bff;
        border-color: #007bff;
    }

    .page-item.active .page-link {
        background-color: #007bff;
        border-color: #007bff;
    }

    .page-item.disabled .page-link {
        color: #6c757d;
        pointer-events: none;
        cursor: not-allowed;
        background-color: #fff;
        border-color: #dee2e6;
    }

    /* Custom styles for our table */
    .table {
        border-collapse: collapse;
        width: 100%;
    }

    .table td,
    .table th {
        border: 1px solid #dee2e6;
        padding: 8px;
        text-align: center;
    }

    .table thead th {
        background-color: #007bff;
        color: #fff;
    }
</style>
<%
    ArrayList<Scene> list = (ArrayList<Scene>) request.getAttribute("list");
    int pages = (request.getSession().getAttribute("page") == null) ? SceneController.page : (int) request.getSession().getAttribute("page");
    int count_page = (int) request.getAttribute("count");
    String action = (String) request.getAttribute("action");
%>
<body>

<div class="card">
    <div class="card-header">
        <div id="wrapper">
            <jsp:include page="../common/Header.jsp"/>
            <div class="page-content-wrapper">
                <div class="container-fluid"><a class="btn btn-link" role="button" id="menu-toggle" href="#menu-toggle"><i
                        class="fa fa-bars"></i></a>

                    <center>
                        <div class="header">
                            <div style="align-items: center;align-content: center" class="row w-25">
                                <%--    <div class="col-md-12">--%>
                                <form action="<%= request.getContextPath() %>/changepage" method="post">
                                    <p class="row w-auto">
                                        nombre a afficher : <input style="padding: 1%" type="number"
                                                                   value="<%= pages %>" min="1"
                                                                   class="form-control w-25 h-25 " name="page"/>--
                                        <input type="submit" style="padding: 2" class="btn btn-outline-primary w-25"
                                               value="valider">
                                    </p>
                                </form>
                            </div>
                        </div>
                    </center>
                    <div class="row">
                        <div class="col-md-12">
                            <div>
                                <h1>Recherhe</h1>
                                <form action='<%= request.getContextPath() %>/search' method="get">
                                    <input type="text" name="search">
                                    <input type="submit" value="search">
                                </form>
                                <br>
                                <div class="table-responsive" style="margin-top: 10px;">
                                    <table class="table table-hover table-responsive">
                                        <thead>
                                        <tr>
                                            <th>Id Scene</th>
                                            <th>Nom du scene</th>
                                            <th>Date Debut du scene</th>
                                        </tr>
                                        </thead>
                                        <tbody>
                                        <% for (Scene v : list) { %>
                                        <tr>
                                            <td><a style="text-decoration: none;color: #4d5154"
                                                   href="<%= request.getContextPath() %>/planning/<%= v.getIdscene() %>"><%= v.getIdscene() %>
                                            </a></td>
                                            <td><a style="text-decoration: none;color: #4d5154"
                                                   href="<%= request.getContextPath() %>/planning/<%= v.getIdscene() %>"><%= v.getNom() %>
                                            </a></td>
                                            <td><a style="text-decoration: none;color: #4d5154"
                                                   href="<%= request.getContextPath() %>/planning/<%= v.getIdscene() %>"><%= v.getDateDebut() %>
                                            </a></td>
                                            <td><a class="btn btn-primary"
                                                   href="<%= request.getContextPath() %>/modify/<%= v.getIdscene() %>">Modifier</a>
                                            </td>
                                        </tr>
                                        <% } %>
                                        </tbody>
                                    </table>
                                </div>
                                <!-- Pagination -->
                                <nav aria-label="Page navigation">
                                    <ul class="pagination">
                                        <%--//                                        <li class="page-item active"><a class="page-link" href="#">1</a></li>--%>
                                        <% int first = 0;
                                            int last = pages;
                                            for (int i = 0; i < count_page; i++) {
                                                if (action.equals("search")) { %>
                                        <li><a class="page-link"
                                               href="<%= request.getContextPath() %>/search?first=<%= first %>"><%= i + 1%>
                                        </a></li>
                                        <% } else { %>

                                        <li><a class="page-link"
                                               href="<%= request.getContextPath() %>/list_scene?first=<%= first %>"><%= i + 1%>
                                        </a></li>

                                        <%
                                                }
                                                first = last;
                                                last = pages + first;
                                            } %>
                                    </ul>
                                </nav>
                            </div>
                            <div class="mb-3">
                                <a  class="btn btn-success" href="<%= request.getContextPath() %>/suggest">suggestion de planning</a>
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
