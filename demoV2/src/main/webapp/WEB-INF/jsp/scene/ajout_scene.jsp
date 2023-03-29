<%--
  Created by IntelliJ IDEA.
  User: 6805
  Date: 28/02/2023
  Time: 20:04
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>

<%--    <link rel="stylesheet" href="assets/css/Sidebar-Menu.css">--%>
</head>
<body>
<div class="card">
    <div class="card-header">
        <div id="wrapper">
            <jsp:include page="../common/Header.jsp" />

            <div class="page-content-wrapper">
                <div class="container-fluid"><a class="btn btn-link" role="button" id="menu-toggle" href="#menu-toggle"><i class="fa fa-bars"></i></a>
                    <div class="row">
                        <div class="col-md-12">
                            <div>

                                <form method="POST" action="<%= request.getContextPath() %>/validate_scene" class="card w-50 mt-3 p-3 mx-auto">
                                    <h1 class='mx-auto'>Ajouter une  scene</h1>
                                    <hr width='100%'>
                                    <p >Nom du scene :<input type="text" class="form-control" required name="nom" placeholder="nom du scene"></p>
                                    <p  class="row w-auto"><label >debut :</label><input type="date" class="form-control w-50 m-2" required name="debut">
                                        <input type="time" class="form-control w-auto" style="float: right" required name="time_debut">
                                    </p>
                                    <p  class="row w-auto"><label >fin :</label><input type="date" class="form-control w-50 m-2" required name="fin">
                                        <input type="time" required class="form-control w-auto" style="float: right" name="time_fin"></p>
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
