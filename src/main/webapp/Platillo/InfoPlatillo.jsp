<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="com.ipn.mx.modelo.entidades.PlatilloView"%>
<%@page import="com.ipn.mx.modelo.entidades.Restaurante"%>
<%@page import="com.ipn.mx.modelo.entidades.ComentarioView"%>
<%@page import="com.ipn.mx.modelo.entidades.Usuario"%>
<%@page import="com.ipn.mx.modelo.dao.PlatilloDao"%>
<%@page import="com.ipn.mx.modelo.dao.RestauranteDao"%>
<%@page import="com.ipn.mx.modelo.dao.ComentarioDao"%>
<%@page import="java.util.List" %>
<%@page import="java.util.Base64"%>

<%
    int idPlatillo = Integer.parseInt(request.getParameter("idPlatillo"));
    PlatilloDao platilloDao = new PlatilloDao();
    RestauranteDao restauranteDao = new RestauranteDao();
    ComentarioDao comentarioDao = new ComentarioDao();
    PlatilloView platilloView = platilloDao.readOnePlatilloView(idPlatillo);
    Restaurante restaurante = restauranteDao.readOneRestaurante(platilloView.getIdRestaurante());
    List<ComentarioView> comentarios = (List<ComentarioView>) comentarioDao.readAllComentarioView(platilloView.getIdPlatillo());
    
    HttpSession sesion = request.getSession();
    Usuario usuario = (Usuario)sesion.getAttribute("usuario");
    
    if(usuario == null){
        usuario = new Usuario();
        usuario.setUsuario("Invitado");
    }
%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <header>Bienvenido: <%=usuario.getUsuario()%></header>
        <main>
            <h1>Datos del platillo</h1>
            <%String encode = Base64.getEncoder().encodeToString(platilloView.getFoto());%>
            <img src="data:image/jpeg;base64,<%=encode%>"/>
            <div><%=platilloView.getNombrePlatillo()%></div>
            <div><%=platilloView.getDescripcion()%></div>
            
            <h2>Datos del restaurante</h2>
            <div><%=restaurante.getNombre()%></div>
            <div><%=restaurante.getDescripcion()%></div>
            <div><%=restaurante.getHorario()%></div>
            <div><%=restaurante.getWeb()%></div>
            <div><%=restaurante.getCorreo()%></div>
            <div><%=restaurante.getTelefono()%></div>
            
            <%if(usuario.getUsuario().equals("Invitado")){%>
            <h3><a href="../Usuario/loginUsuario.html">Inicia sesion para dejar un comentario</a></h3>
            <%}else{%>
                <h2>Deja un comentario</h2>
                <form action="/ProyectoFinal/ComentarioServlet" method="POST">
                    <input type="text" name="comentario" id="comentario">
                    <input type="number" name="calificacion" id="calificacion">
                    <input type="text" name="idUsuario" id="idUsuario" value="<%=usuario.getIdUsuario()%>" hidden>
                    <input type="text" name="idPlatillo" id="idPlatillo" value="<%=idPlatillo%>" hidden>
                    <input type="submit" name="accion" value="Comentar" />
                </form>
            <%}%>
            
            
            <h3>Comentarios de este platillo</h3>
            <%for(ComentarioView c: comentarios){%>
                <div><%=c.getUsuario()%> dice: <%=c.getComentario()%></div>
            <%}%>
        </main>
        <footer></footer>
    </body>
</html>
