<%@page import="java.util.ArrayList"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="com.ipn.mx.modelo.entidades.PlatilloView"%>
<%@page import="com.ipn.mx.modelo.dao.PlatilloDao" %>
<%@page import="java.util.List" %>
<%@page import="java.util.Base64" %>


<%
    if(request.getParameter("idCategoria") == null || request.getParameter("categoria") == null){
        response.sendRedirect("/ProyectoFinal/");
    }
    int idCategoria = Integer.parseInt(request.getParameter("idCategoria"));
    String categoria  = request.getParameter("categoria");
    PlatilloDao platilloDao = new PlatilloDao();
    List<PlatilloView> platillosTemp = platilloDao.readAllPlatilloView();
    ArrayList<PlatilloView> platillos = new ArrayList<PlatilloView>();
    for(int i=0; i < platillosTemp.size(); i++){
        if(platillosTemp.get(i).getIdCategoria() == idCategoria){
            platillos.add(platillosTemp.get(i));
        }
    }
    
    System.out.println("Terminado");
%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Platillos</title>
    <link rel="stylesheet" href="../css/platillosStyle.css">
</head>
<body>
    <div class="wrapper">
        <div class="mantel">
            <p class="titulo"><%=categoria%></p>
        </div>
        <%if(platillos.size()==0){%>
           <p class="noplatillos">Por el momento no hay platillos en esta categoria</p>
        <%}else{%>
            <div class="platillos">
                <%for(int i=0; i<platillos.size(); i++){%>
                    <div class="platillo">
                        <div class="imgPlatilloContenedor">

                        <%String encode = Base64.getEncoder().encodeToString(platillos.get(i).getFoto());%>
                        <img src="data:image/jpeg;base64,<%=encode%>" class="imgPlatillo"/>

                            <!-- 
                            <img src="../imagenes/imgPlatillos/<%=platillos.get(i).getNombreFoto()%>" alt="platillo" srcset="" class="imgPlatillo">
                             -->
                        </div>
                        <p class="nombrePlatillo"><%=platillos.get(i).getNombrePlatillo()%></p>
                        <p class="restaurantePlatillo"><%=platillos.get(i).getNombreRestaurante()%></p>
                        <a href="InfoPlatillo.jsp?idPlatillo=<%=platillos.get(i).getIdPlatillo()%>" class="verPlatillo">Ver info</a>
                    </div> 
                <%}%>
            </div>
        <%}%>
    </div>
</body>
</html>