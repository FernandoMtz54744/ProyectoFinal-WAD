package com.ipn.mx.modelo.dao;

import com.ipn.mx.modelo.entidades.Platillo;
import com.ipn.mx.modelo.entidades.PlatilloView;
import com.ipn.mx.utilerias.HibernateUtil;
import java.io.File;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.Base64;
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
public class PlatilloDao {
    public void create(Platillo p) {
        Session session = HibernateUtil.getSessionFactory().getCurrentSession();
        Transaction transaction = session.getTransaction();
        try {
            transaction.begin();
            session.persist(p);
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
          Query q = session.createQuery("from Platillo", Platillo.class);
          resultados = q.list();
          transaction.commit();
      } catch (HibernateException he) {
          if (transaction != null && transaction.isActive()) {
              transaction.rollback();
          }
      }
      return resultados;
    }
    
    public void update(int idPlatillo, Platillo p){
        Session session = HibernateUtil.getSessionFactory().getCurrentSession();
        Transaction transaction = session.getTransaction();
        try {
            transaction.begin();
            p.setIdPlatillo(idPlatillo);
            session.merge(p);
            transaction.commit();
        } catch (HibernateException he) {
            if (transaction.isActive()) {
                transaction.rollback();
            }
            he.printStackTrace();
            System.out.println("Error: " + he.getMessage());
        }
    }
    
    public void delete(int idPlatillo){
        Session session = HibernateUtil.getSessionFactory().getCurrentSession();
        Transaction transaction = session.getTransaction();
        try {
            transaction.begin();
            Platillo p = (Platillo) session.find(Platillo.class, idPlatillo);
            session.remove(p);
            transaction.commit();
        } catch (HibernateException he) {
            if (transaction.isActive()) {
                transaction.rollback();
            }
            he.printStackTrace();
            System.out.println("Error: " + he.getMessage());
        }
    }
    
    public List readAllPlatilloView() {
        Session session = HibernateUtil.getSessionFactory().getCurrentSession();
        Transaction transaction = session.getTransaction();
        List resultados = new ArrayList();
        try {
            transaction.begin();
            Query q = session.createQuery("from PlatilloView", PlatilloView.class);
            resultados = q.list();
            transaction.commit();
        } catch (HibernateException he) {
            if (transaction != null && transaction.isActive()) {
                transaction.rollback();
            }
        }
        return resultados;
    }
    
    public PlatilloView readOnePlatilloView(int idPlatillo){
        String query = "From PlatilloView p Where p.idPlatillo = :idPlatillo";
        PlatilloView platilloView = null;
        Session session = HibernateUtil.getSessionFactory().getCurrentSession();
        Transaction transaction = session.getTransaction();
        try {
            transaction.begin();
            Query q = session.createQuery(query, PlatilloView.class);
            q.setParameter("idPlatillo", idPlatillo);
            platilloView = (PlatilloView) q.uniqueResult();
            transaction.commit();
        } catch (HibernateException he) {
            if (transaction != null && transaction.isActive()) {
                transaction.rollback();
            }
        }
        return platilloView;
    }
    
    public static void main(String[] args) { //Este main se puede eliminar, es de prueba
        Platillo p = new Platillo();
        PlatilloDao dao = new PlatilloDao();
        
        p.setNombre("Pizza cuatro quesos");
        p.setDescripcion("¡Artesanalmente italiana! Queso mozarella, parmesano, gorgonzola y queso cenizo de cabra, sobre salsa pomodoro con cebollín finamente picado.");
        p.setNombreFoto("pizza.jpg");
        try{
            p.setFoto(Files.readAllBytes(new File("D:\\Fer_Mtz\\Desktop\\td.png").toPath()));
        }catch(Exception e){
            System.out.println("Error al subir foto");
        }
        
        p.setIdRestaurante(6);
        p.setIdCategoria(3);
        
        dao.create(p);
        //System.out.println(dao.readOnePlatilloView(2));
        
    }
}
