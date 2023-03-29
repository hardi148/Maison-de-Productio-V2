<%@ page import="java.util.ArrayList" %>
<%@ page import="com.spring.springmvc_v_finale.model.Plateau" %><%--
  Created by IntelliJ IDEA.
  User: 6805
  Date: 14/03/2023
  Time: 13:50
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
  <title>Title</title>
</head>
<style>
  .modal {
    display: none; /* Hidden by default */
    position: fixed; /* Stay in place */
    z-index: 1; /* Sit on top */
    left: 0;
    top: 0;
    width: 100%; /* Full width */
    height: 100%; /* Full height */
    overflow: auto; /* Enable scroll if needed */
    background-color: rgb(0, 0, 0); /* Fallback color */
    background-color: rgba(0, 0, 0, 0.4); /* Black w/ opacity */
  }

  /* Modal Content */
  .modal-content {
    background-color: #fefefe;
    margin: 15% auto; /* 15% from the top and centered */
    padding: 20px;
    border: 1px solid #888;
    width: 80%; /* Could be more or less, depending on screen size */
  }

  /* The Close Button */
  .close {
    color: #aaa;
    /*background-color: ;*/
    float: right;
    font-size: 28px;
    width: 50px;
    font-weight: bold;
  }

  .close:hover,
  .close:focus {
    color: black;
    text-decoration: none;
    cursor: pointer;
  }

</style>
<body>
<%
    ArrayList<Plateau> list = (ArrayList<Plateau>) request.getAttribute("plateau_list");
%>

<div class="card">
  <div class="card-header">
    <div id="wrapper">
      <jsp:include page="../common/Header.jsp"/>
      <div class="page-content-wrapper">
        <div class="container-fluid"><a class="btn btn-link" role="button" id="menu-toggle" href="#menu-toggle"><i
                class="fa fa-bars"></i></a>


          <div class="row">
              <div id="myModal"  class="modal w-50 h-auto" style="margin-left: 25%;backface-visibility: hidden">
              <div class="modal-content w-auto h-auto">
                <span class="close">&times;</span>
                <form class="form-control" action="<%= request.getContextPath() %>/modify/plateau"
                      method="post">
                    <h2 id="nom_plateau"></h2>
                  <p> debut indisponibilite:  <input class="form-control" type="date" required name="debut_indisponibilite" ></p>
                  <p> fin indisponibilite :  <input class="form-control" type="date" required name="fin_indisponibilite" ></p>
                    <input type="number" name="idplateau" hidden id="plateau">
                  <center> <input type="submit" class="btn btn-outline-primary" value="modify"></center>
                </form>
              </div>
            </div>
            <div class="col-md-12">
              <div>
                <div class="table-responsive" style="margin-top: 10px;">
                  <table class="table table-hover table-responsive">
                    <thead>
                    <tr>
                      <th>Id Plateau</th>
                      <th>Nom du plateau</th>
                      <th>Date Debut Indisponibilte</th>
                      <th>Date Fin Indisponibilte</th>
                    </tr>
                    </thead>
                    <tbody>
                    <% for (int i=0;i< list.size();i++) { %>
                    <tr>
                      <td><%= list.get(i).getIdplateau() %></td>
                      <td><%= list.get(i).getNomplateau() %></td>
                      <td><%= list.get(i).getDateDebutIndisponibilite() %></td>
                      <td><%= list.get(i).getDateFinIndisponibile()  %></td>
                      <td>
<%--                        <button class="btn btn-info" id="btn" onclick="onAdd(<%= 1 %>)" type="button">modifier</button>--%>
                        <button type="button" onclick="onAdd(<%= list.get(i).getIdplateau() %>,'<%= list.get(i).getNomplateau() %>')" class="btn btn-primary">modifier</button>

                      </td>
                    </tr>

                    <% } %>

                    </tbody>
                  </table>
                </div>
              </div>
            </div>
          </div>
        </div>
      </div>
    </div>

  </div>
</div>

<script>

    var modal = document.getElementById("myModal");

    // Get the button that opens the modal
    var btn = document.getElementById("btn");

    // Get the <span> element that closes the modal
    var span = document.getElementsByClassName("close")[0];
    function onAdd(id_cp,nom) {
        modal.style.display = "block";
        document.getElementById("plateau").value = id_cp;
        document.getElementById("nom_plateau").innerHTML = nom;

    }


    span.onclick = function () {
        modal.style.display = "none";
    }
    window.onclick = function (event) {
        if (event.target == modal) {
            modal.style.display = "none";
        }

    };

</script>
</body>
</html>
