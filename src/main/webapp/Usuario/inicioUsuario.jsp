<%-- 
    Document   : inicioUsuario
    Created on : 11/06/2022, 07:43:22 PM
    Author     : Cortes Lopez Jaime Alejandro
    Author     : Godinez Montero Esmeralda
    Author     : Fernando Mtz
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ page import="com.ipn.mx.modelo.entidades.Usuario" %>

<%
    HttpSession sesion = request.getSession(false);
    Usuario usuario = (Usuario) sesion.getAttribute("usuario");
%>

<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <h1>Menu principal usuario</h1>
        <h2>Bienvenido: <%=usuario%></h2>
    </body>
</html>
