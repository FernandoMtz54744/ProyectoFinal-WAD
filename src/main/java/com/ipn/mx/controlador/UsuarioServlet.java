package com.ipn.mx.controlador;

import com.ipn.mx.modelo.dao.UsuarioDao;
import com.ipn.mx.modelo.entidades.Usuario;
import com.ipn.mx.utilerias.EnviarMail;
import jakarta.servlet.RequestDispatcher;
import java.io.File;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperRunManager;

/**
 * @author Cortes Lopez Jaime Alejandro
 * @author Godinez Montero 
 * @author Fernando Mtz
 */

@WebServlet(name = "UsuarioServlet", urlPatterns = {"/UsuarioServlet"})
public class UsuarioServlet extends HttpServlet {

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
        if(accion.equals("Registrar Usuario")){
            registrarUsuario(request, response);
        }else if(accion.equals("Login")){
            login(request,response);
        }else if(accion.equals("reporte")){
            mostrarReporte(request,response);
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

    private void registrarUsuario(HttpServletRequest request, HttpServletResponse response) {
        UsuarioDao dao = new UsuarioDao();
        Usuario u = new Usuario();
        u.setUsuario(request.getParameter("usuario"));
        u.setCorreo(request.getParameter("correo"));
        u.setPass(request.getParameter("pass"));
        dao.create(u);

        try {
            EnviarMail mail = new EnviarMail();
            mail.enviarMail(u.getCorreo(), "Bienvenido " + u.getUsuario(), "Tu correo se ha registrado correctamente en nuestra aplicación https://pf-wad-fej.herokuapp.com/");
            response.sendRedirect(request.getContextPath() + "/Usuario/loginUsuario.html");
        } catch (IOException ex) {
            System.out.println("Error registrarUsuario en UsuarioServlet: " + ex.getMessage());
        }
       
    }
    
    private void login(HttpServletRequest request, HttpServletResponse response) {
            UsuarioDao dao = new UsuarioDao();
            Usuario u = new Usuario();
            Usuario usuarioLogin = null;
            u.setCorreo(request.getParameter("correo"));
            u.setPass(request.getParameter("pass"));
            usuarioLogin = dao.login(u);
            try{
                if(usuarioLogin != null){//Login exitoso
                    //Se sube el usuario a la sesion
                    HttpSession sesion = request.getSession();
                    sesion.setAttribute("usuario", usuarioLogin);
                    int idPlatillo = (int)sesion.getAttribute("idPlatillo");
                    //Se redirecciona al inicio del usuario
                    response.sendRedirect(request.getContextPath() + "/Platillo/InfoPlatillo.jsp?idPlatillo="+idPlatillo);
                }else{//Login incorrecto
                    response.sendRedirect(request.getContextPath() + "/Usuario/loginUsuario.html");
                }
            }catch(Exception e){
                System.out.println("Error login en UsuarioServlet");
            }
    }
    
     private void mostrarReporte(HttpServletRequest request, HttpServletResponse response){
        UsuarioDao dao = new UsuarioDao();
       ServletOutputStream sos = null;
       try{
            sos = response.getOutputStream(); 
            File reporte = new File(getServletConfig().getServletContext().getRealPath("/reportes/ReportePlatillos.jasper"));   
            byte[] b = JasperRunManager.runReportToPdf(reporte.getPath(), null, dao.obtenerConexion()); 
            response.setContentType("application/pdf");
            response.setContentLength(b.length);
            sos.write(b,0,b.length);
            sos.flush();
            sos.close();
        }catch(IOException e){
            System.out.println("Error al mostrar reporte");
            e.getStackTrace();
       } catch (JRException ex) {
            Logger.getLogger(UsuarioServlet.class.getName()).log(Level.SEVERE, null, ex);
        }finally{
           try{
               sos.close();
           }catch(IOException ex){
                System.out.println("Error al cerrar sos");
           }
       }
       

    }
}
