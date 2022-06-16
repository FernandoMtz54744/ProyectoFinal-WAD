package com.ipn.mx.modelo.dao;

import com.ipn.mx.modelo.entidades.Comentario;
import com.ipn.mx.modelo.entidades.ComentarioView;
import com.ipn.mx.utilerias.HibernateUtil;
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
public class ComentarioDao {
    public void create(Comentario c) {
        Session session = HibernateUtil.getSessionFactory().getCurrentSession();
        Transaction transaction = session.getTransaction();
        try {
            transaction.begin();
            session.persist(c);
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
          Query q = session.createQuery("from Comentario", Comentario.class);
          resultados = q.list();
          transaction.commit();
      } catch (HibernateException he) {
          if (transaction != null && transaction.isActive()) {
              transaction.rollback();
          }
      }
      return resultados;
    }
    
    public void update(int idComentario, Comentario c){
        Session session = HibernateUtil.getSessionFactory().getCurrentSession();
        Transaction transaction = session.getTransaction();
        try {
            transaction.begin();
            c.setIdComentario(idComentario);
            session.merge(c);
            transaction.commit();
        } catch (HibernateException he) {
            if (transaction.isActive()) {
                transaction.rollback();
            }
            he.printStackTrace();
            System.out.println("Error: " + he.getMessage());
        }
    }
    
    public void delete(int idComentario){
        Session session = HibernateUtil.getSessionFactory().getCurrentSession();
        Transaction transaction = session.getTransaction();
        try {
            transaction.begin();
            Comentario c = (Comentario) session.find(Comentario.class, idComentario);
            session.remove(c);
            transaction.commit();
        } catch (HibernateException he) {
            if (transaction.isActive()) {
                transaction.rollback();
            }
            he.printStackTrace();
            System.out.println("Error: " + he.getMessage());
        }
    }
    
    //Lee los comentarios de un platillo dado
    public List readAllComentarioView(int idPlatillo) {
        String query = "From ComentarioView c Where c.idPlatillo= :idPlatillo";
        List resultados = new ArrayList();
        Session session = HibernateUtil.getSessionFactory().getCurrentSession();
        Transaction transaction = session.getTransaction();
        try {
            transaction.begin();
            Query q = session.createQuery(query, ComentarioView.class);
            q.setParameter("idPlatillo", idPlatillo);
            resultados = q.list();
            transaction.commit();
        } catch (HibernateException he) {
            if (transaction != null && transaction.isActive()) {
                transaction.rollback();
            }
        }
        return  resultados;
    }
    
    public static void main(String[] args) {//Este main se puede eliminar, es de prueba
        
        Comentario c = new Comentario();
        ComentarioDao dao = new ComentarioDao();
        /*
        c.setComentario("Muy buenos los takos de pastor");
        c.setIdUsuario(1);
        c.setIdPlatillo(1);
     
        dao.create(c);*/
        System.out.println(dao.readAllComentarioView(2));
       
    }
}
