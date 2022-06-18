package com.ipn.mx.modelo.dao;

import com.ipn.mx.modelo.entidades.Usuario;
import com.ipn.mx.utilerias.EnviarMail;
import com.ipn.mx.utilerias.HibernateUtil;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

/**
 * @author Cortes Lopez Jaime Alejandro
 * @author Godinez Montero Esmeralda
 * @author Fernando Mtz
 */
public class UsuarioDao {
    
    public void create(Usuario u) {
        Session session = HibernateUtil.getSessionFactory().getCurrentSession();
        Transaction transaction = session.getTransaction();
        try {
            transaction.begin();
            session.persist(u);
            transaction.commit();
        } catch (HibernateException he) {
            if (transaction.isActive()) {
                transaction.rollback();
            }
            he.printStackTrace();
            System.out.println("Error: " + he.getMessage());
        }
    }
    
     public List readAll() {
        Session session = HibernateUtil.getSessionFactory().getCurrentSession();
        Transaction transaction = session.getTransaction();
        List resultados = new ArrayList();
        try {
            transaction.begin();
            Query q = session.createQuery("from Usuario", Usuario.class);
            resultados = q.list();
            transaction.commit();
        } catch (HibernateException he) {
            if (transaction != null && transaction.isActive()) {
                transaction.rollback();
            }
        }
        return resultados;
    }
     
    public Usuario login(Usuario u){
        String query = "From Usuario u Where u.correo = :correo and u.pass = :pass";
        Usuario usuarioLogin = null;
        Session session = HibernateUtil.getSessionFactory().getCurrentSession();
        Transaction transaction = session.getTransaction();
        try {
            transaction.begin();
            Query q = session.createQuery(query, Usuario.class);
            q.setParameter("correo", u.getCorreo());
            q.setParameter("pass", u.getPass());
            usuarioLogin = (Usuario) q.uniqueResult();
            transaction.commit();
        } catch (HibernateException he) {
            if (transaction != null && transaction.isActive()) {
                transaction.rollback();
            }
        }
        return usuarioLogin;
    }
    
    private Connection conexion;
    public Connection obtenerConexion() {
        //obtener conexion
        String usuario = "dzcgkwhyvlgvpu";
        String clave = "ccac6815fa6a6b8438d9888b0940e3c7f71a48d7e310e4152a80faee024928d9";
        String url = "jdbc:postgresql://ec2-54-147-33-38.compute-1.amazonaws.com:5432/d2hqg08386du81";
        String driverBD = "org.postgresql.Driver";
        try {
            Class.forName(driverBD);
            conexion = DriverManager.getConnection(url, usuario, clave);
        } catch (ClassNotFoundException | SQLException ex) {
            System.out.println("Error obtener conexion " + ex.getMessage());
        }
        return conexion;
    }
    
     public static void main(String[] args) { //Este main se puede eliminar, es solo de prueba
        UsuarioDao dao  = new UsuarioDao();
        Usuario u = new Usuario();
        u.setUsuario("Fernando");
        u.setCorreo("fer_f@outlook.com");
        u.setPass("pass");
        //dao.create(u);
       
    } 
    
}
