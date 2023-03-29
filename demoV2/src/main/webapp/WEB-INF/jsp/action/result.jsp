<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page import="java.util.ArrayList" %>
<%@ page import="java.time.Month" %>
<%@ page import="java.sql.Date" %>
<%@ page import="com.spring.springmvc_v_finale.model.*" %>
<%@ page import="java.sql.Time" %><%--
  Created by IntelliJ IDEA.
  User: 6805
  Date: 01/03/2023
  Time: 11:41
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
<%--    <link href="<c:url value='/ressources/theme/css/CardStyle.css' />" rel="stylesheet">--%>

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
</head>
<body>
<%
    ArrayList<Date> list_date = (ArrayList<Date>) request.getAttribute("list_date");
    ArrayList<Personnage> person = (ArrayList<Personnage>) request.getAttribute("person");
    ArrayList<Plateau> plateau = (ArrayList<Plateau>) request.getAttribute("plateau");
    ArrayList<Action> list = (ArrayList<Action>) request.getAttribute("plan");
    int scene = (int) request.getAttribute("scene");
%>
<div class="card">
    <div class="card-header">
        <div id="wrapper">
            <jsp:include page="../common/Header.jsp"/>

            <div class="page-content-wrapper">
                <div class="container-fluid"><a class="btn btn-link" role="button" id="menu-toggle" href="#menu-toggle"><i
                        class="fa fa-bars"></i></a>
                    <div class="row">
                        <div class="col-md-12">
                            <h3>
                                <button class="btn btn-info" id="btn" onclick="onAdd(<%= 1 %>)" type="button">ajouter
                                    action
                                </button>
                                 <h1>Recherhe</h1>

                             <form action='<%= request.getContextPath() %>/search_is_validate' method="post">
                                <select name="search">
                                <option>Choisir l'etat de l'action</option>
                                <option value="1">Validee</option>
                                <option value="0">Non Validee</option>
                                </select>
                                <input type="submit" value="search">
                             </form>


                            </h3>
                            <div id="myModal"  class="modal w-50 h-auto" style="margin-left: 25%;backface-visibility: hidden">
                                <div class="modal-content w-auto h-auto">
                                    <span class="close">&times;</span>
                                    <form class="form-control" action="<%= request.getContextPath() %>/validate_action/<%= scene %>"
                                          method="post">
<%--                                        <p>--%>
<%--                                            jour action--%>
<%--                                        <div class="mb-3">--%>
<%--                                        <select id="date" class="form-control" name="dateAction" >--%>
<%--                                            <% for (Date d : list_date) { %>--%>
<%--                                            <option value="<%= d %>"> <%= d.getDate() + " " + Month.of(d.getMonth() + 1) %></option>--%>
<%--&lt;%&ndash;                                            <div class="form-check">&ndash;%&gt;--%>
<%--&lt;%&ndash;                                                <input class="form-check-input" type="radio" value="<%= d %>"&ndash;%&gt;--%>
<%--&lt;%&ndash;                                                       name="dateAction">&ndash;%&gt;--%>
<%--&lt;%&ndash;                                                <label class="form-check-label">&ndash;%&gt;--%>
<%--&lt;%&ndash;                                                    <%= d.getDate() + " " + Month.of(d.getMonth() + 1) %>&ndash;%&gt;--%>
<%--&lt;%&ndash;                                                </label>&ndash;%&gt;--%>
<%--&lt;%&ndash;                                            </div>&ndash;%&gt;--%>
<%--                                            &lt;%&ndash;                                        </option>&ndash;%&gt;--%>
<%--                                            <% } %>--%>
<%--                                        </select>--%>
<%--                                        </div>--%>
<%--                                        <label for="date">fin</label>--%>
<%--                                        <input class="form-control" type="time" required name="fin">--%>
<%--                                        </p>--%>

                                      <p>duree:  <input class="form-control" type="number" min="1" required name="dure"></p>
                                        <input class="form-control" type="file" class="form-control" id="selectImage" name="images">
                                        <input type="hidden" name="image" id="imageCode">
                                        <p>Description : <textarea class="form-control" name="description" rows="3" cols="40"
                                                                   placeholder="Enter your text here..."></textarea>
                                        </p>
                                        <p>Plateau :
                                            <select class="form-control" name="plateau">
                                                <% for (Plateau p : plateau) { %>
                                                <option value="<%= p.getIdplateau() %>"><%= p.getNomplateau()  %>
                                                </option>
                                                <% } %>
                                            </select>
                                        </p>
                                        <p>person :
                                            <%--                                            <select name="person">--%>
                                            <%--                                                <option value="vide"> choose people</option>--%>
                                            <% for (Personnage p : person) { %>
                                            <input  type="checkbox" id="perso" name="person"
                                                   value="<%= p.getIdperso() %>">
                                            <label for="perso"><%= p.getNomperso()  %>
                                            </label>
                                            <%--                                                <option value="<%= p.getIdperso() %>"> <%= p.getNomperso()  %></option>--%>
                                            <% } %>
                                            <%--                                            </select>--%>
                                        </p>
                                       <center> <input type="submit" class="btn btn-outline-primary" value="enregistrer"></center>
                                    </form>
                                </div>
                            </div>
                            <div>

                                <% for (Action act : list) { %>
                                <div class="card">
                                    <div class="card-header">
                                        <% for (Photos e:act.getList_photo()) { %>
                                            <img class="w-50 h-50 img-fluid" alt="rover" src="<%= e.getImage() %>"/>
                                       <% } %>
                                    </div>
                                    <div class="card-body">
                <span class="tag tag-teal">dure: <%= act.getDateAction() %>
<%--                <span class="tag tag-teal">jour: <%= act.getDateAction().getDate() + " " + Month.of(act.getDateAction().getMonth() + 1) %>--%>
                </span>
                                        <h4>Personnage :
                                            <% for (Mis_en_Action m : act.getMis_en_actions()) { %>
                                            <%= m.getPerso().getNomperso() %> ,
                                            <% } %>
                                        </h4>
<%--                                        <p>Plateau :--%>
<%--                                            <% for (Mis_en_Action m : act.getMis_en_actions()) { %>--%>
<%--                                            <%= m.getPlate().getNomplateau() %> ,--%>
<%--                                            <% } %>--%>
<%--                                        </p>--%>
                                        <p>Description : <%= act.getDescription() %>
                                        </p>
                                        <div class="user">
                                            <div class="user-info">
<%--                                                <h5>heure fin action: <small><%= String.valueOf(new Time(act.getDateAction().getTime())) %>--%>
<%--                                                </small></h5>--%>
                                                <% if (act.getFinished()==0) {%>
                                                <h3>
                                                    <small class="even-row-color" style="color: red">non fini</small>
                                                </h3>
                                                    <a type="button" class="btn btn-outline-info" href="<%= request.getContextPath() %>/changestatus/<%= act.getIdaction() %>">terminer action</a>
                                                <% } else {%>
                                               <h2> <small class="even-row-color" style="color: #0d6efd"> fini</small></h2>
                                                <% } %>
                                            </div>
                                        </div>
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

<script>

    var modal = document.getElementById("myModal");

    // Get the button that opens the modal
    var btn = document.getElementById("btn");

    // Get the <span> element that closes the modal
    var span = document.getElementsByClassName("close")[0];

    function onAdd(id_cp) {
        // document.getElementById('id_cp').value = id_cp;
        // alert(document.getElementById('id_cp').value)
        modal.style.display = "block";

    }

    // btn.onclick = function() {
    //     modal.style.display = "block";
    // }

    span.onclick = function () {
        modal.style.display = "none";
    }
    window.onclick = function (event) {
        if (event.target == modal) {
            modal.style.display = "none";
        }
    }
    const input = document.getElementById("selectImage");
    const imageCode = document.getElementById("imageCode");
    const convertBase64 = (file) => {
        return new Promise((resolve, reject) => {
            const fileReader = new FileReader();
            fileReader.readAsDataURL(file);
            fileReader.onload = () => {
                resolve(fileReader.result);
            };
            fileReader.onerror = (error) => {
                reject(error);
            };
        });
    };
    const uploadImage = async (event) => {
        const file = event.target.files[0];
        const base64 = await convertBase64(file);
        imageCode.value = base64;
        console.log(imageCode.value);
    };
    input.addEventListener("change", (e) => {
        uploadImage(e);
    });
</script>
</body>
</html>
