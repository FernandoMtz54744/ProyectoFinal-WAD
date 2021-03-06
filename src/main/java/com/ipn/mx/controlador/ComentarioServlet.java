package com.ipn.mx.controlador;

import com.ipn.mx.modelo.dao.ComentarioDao;
import com.ipn.mx.modelo.entidades.Comentario;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.annotation.WebServlet;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @author Cortes Lopez Jaime Alejandro
 * @author Godinez Montero 
 * @author Fernando Mtz
 */

@WebServlet(name = "ComentarioServlet", urlPatterns = {"/ComentarioServlet"})
public class ComentarioServlet extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        String accion = request.getParameter("accion");
        if(accion.equals("Comentar")){
            registrarComentario(request, response);
        }
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>
    
    private void registrarComentario(HttpServletRequest request, HttpServletResponse response) {
        ComentarioDao dao = new ComentarioDao();
        Comentario c = new Comentario();
        c.setComentario(request.getParameter("comentario"));
        c.setCalificacion(Integer.parseInt(request.getParameter("calificacion")));
        c.setIdUsuario(Integer.parseInt(request.getParameter("idUsuario")));
        c.setIdPlatillo(Integer.parseInt(request.getParameter("idPlatillo")));
        dao.create(c);
        
        try {
            response.sendRedirect(request.getContextPath() + "/Platillo/InfoPlatillo.jsp?idPlatillo="+c.getIdPlatillo());
        } catch (IOException ex) {
            System.out.println("Error registrarComentario en ComentarioServlet: " + ex.getMessage());
        }
    }

}
