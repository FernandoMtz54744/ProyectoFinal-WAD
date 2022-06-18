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
    sesion.setAttribute("idPlatillo", idPlatillo);
    
    if(usuario == null){
        usuario = new Usuario();
        usuario.setUsuario("Invitado");
    }
%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
         <link rel="stylesheet" href="../css/platilloStyle.css">
        <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-EVSTQN3/azprG1Anm3QDgpJLIm9Nao0Yz1ztcQTwFspd3yD65VohhpuuCOmLASjC" crossorigin="anonymous">
        <title>Platillo</title>
    </head>
    <body>       
        <div class="container">
            <div class="header">
                <%if(usuario.getUsuario().equals("Invitado")){%>
                   <a href="../Usuario/loginUsuario.html">Entrar</a>
                <%}else{%>
                    <p>Bienvenido: <%=usuario.getUsuario()%></p>           
                <%}%>
            </div>
            <div class="info">
                <div class="infoRestaurante">
                    <a href="<%=restaurante.getWeb()%>" class="tRestaurante"><%=restaurante.getNombre()%></a>
                    <p class="letra white"><%=restaurante.getDescripcion()%></p>
                    <div id="hwt">
                        <div>
                            <p class="letra op bold white">Horario</p>
                            <p class="letra op white"><%=restaurante.getHorario()%></p>
                        </div>
                        <div>
                            <p class="letra op bold white">Correo</p>
                            <p class="letra op white"><%=restaurante.getCorreo()%></p>    
                        </div>
                        <div>
                            <p class="letra op bold white">Telefono</p>
                            <p class="letra op white"><%=restaurante.getTelefono()%></p>
                        </div>
                    </div>                                      
                </div>
                <div class="infoPlatillo">
                    <%String encode = Base64.getEncoder().encodeToString(platilloView.getFoto());%>
                    <img src="data:image/jpeg;base64,<%=encode%>" class="tamPlatillo" alt="platillo"/>
                    <!--<img src="../imagenes/imgPlatillos/Aqui va NombreFoto" alt="platillo" class="tamPlatillo">-->
                    
                    <div>
                        <p class="tPlatillo"><%=platilloView.getNombrePlatillo()%></p>
                        <p class="letra"><%=platilloView.getDescripcion()%></p>
                    </div>
                </div>

            </div>
            <div class="ccomentario">
                <%if(usuario.getUsuario().equals("Invitado")){%>
                    <p class="text-center">Inicia sesion para dejar un comentario</p>
                <%}else{%>               
                    <p class="letra">Queremos conocer tu opinión acerca del platillo. Cuentanos que tal te pareció.</p>
                    <form action="/ProyectoFinal/ComentarioServlet" method="POST">
                        <input type="text" name="idUsuario" id="idUsuario" value="<%=usuario.getIdUsuario()%>" hidden>
                        <input type="text" name="idPlatillo" id="idPlatillo" value="<%=idPlatillo%>" hidden>
                        <div class="form-check form-check-inline">
                            <input class="form-check-input" type="radio" name="calificacion" id="calificacion" value="0" checked>
                            <label class="form-check-label" for="calificacion">0</label>
                          </div>
                        <div class="form-check form-check-inline">
                            <input class="form-check-input" type="radio" name="calificacion" id="calificacion" value="1">
                            <label class="form-check-label" for="calificacion">1</label>
                          </div>
                        <div class="form-check form-check-inline">
                            <input class="form-check-input" type="radio" name="calificacion" id="calificacion" value="2">
                            <label class="form-check-label" for="calificacion">2</label>
                          </div>
                        <div class="form-check form-check-inline">
                            <input class="form-check-input" type="radio" name="calificacion" id="calificacion" value="3">
                            <label class="form-check-label" for="calificacion">3</label>
                        </div>
                        <div class="form-check form-check-inline">
                            <input class="form-check-input" type="radio" name="calificacion" id="calificacion" value="4">
                            <label class="form-check-label" for="calificacion">4</label>
                        </div>
                        <div class="form-check form-check-inline">
                            <input class="form-check-input" type="radio" name="calificacion" id="calificacion" value="5">
                            <label class="form-check-label" for="calificacion">5</label>
                        </div>
                        <br><br>
                        <textarea id="comentario" name="comentario" rows=5 cols=120 class="letra op"  ></textarea><br><br>
                        <input type="submit" name="accion" value="Comentar" class="btn btn-outline-dark"/>
                    </form>
                <%}%>    
            </div>
            
             <%for(ComentarioView c: comentarios){%>
                <div class="comentario">
                    <p><%=c.getUsuario()%></p> 
                    <%for(int i=0; i<c.getCalificacion();i++){%>
                    <img src="../imagenes/estrella.png" alt="estrellita" class="tamEstrellas">                       
                    <%}%>
                    <%for(int i=0; i<5-c.getCalificacion();i++){%>
                    <img src="../imagenes/vestrella.png" alt="estrellita" class="tamEstrellas">
                    <%}%>
                    <p class="letra mt-5"><%=c.getComentario()%></p>
                </div>                
            <%}%>                        
        </div> 
    </body>
</html>
