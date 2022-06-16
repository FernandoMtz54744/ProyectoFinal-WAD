package com.ipn.mx.modelo.dao;

import com.ipn.mx.modelo.entidades.Restaurante;
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
public class RestauranteDao {
    
    public void create(Restaurante r) {
        Session session = HibernateUtil.getSessionFactory().getCurrentSession();
        Transaction transaction = session.getTransaction();
        try {
            transaction.begin();
            session.persist(r);
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
           Query q = session.createQuery("from Restaurante", Restaurante.class);
           resultados = q.list();
           transaction.commit();
       } catch (HibernateException he) {
           if (transaction != null && transaction.isActive()) {
               transaction.rollback();
           }
       }
       return resultados;
    }
     
    public Restaurante login(Restaurante r){
        String query = "From Restaurante r Where r.correo = :correo and r.pass = :pass";
        Restaurante restauranteLogin = null;
        Session session = HibernateUtil.getSessionFactory().getCurrentSession();
        Transaction transaction = session.getTransaction();
        try {
            transaction.begin();
            Query q = session.createQuery(query, Restaurante.class);
            q.setParameter("correo", r.getCorreo());
            q.setParameter("pass", r.getPass());
            restauranteLogin = (Restaurante) q.uniqueResult();
            transaction.commit();
        } catch (HibernateException he) {
            if (transaction != null && transaction.isActive()) {
                transaction.rollback();
            }
        }
        return restauranteLogin;
    }
    
    public Restaurante readOneRestaurante(int idRestaurante){
        String query = "From Restaurante r Where r.idRestaurante = :idRestaurante";
        Restaurante restaurante = null;
        Session session = HibernateUtil.getSessionFactory().getCurrentSession();
        Transaction transaction = session.getTransaction();
        try {
            transaction.begin();
            Query q = session.createQuery(query, Restaurante.class);
            q.setParameter("idRestaurante", idRestaurante);
            restaurante = (Restaurante) q.uniqueResult();
            transaction.commit();
        } catch (HibernateException he) {
            if (transaction != null && transaction.isActive()) {
                transaction.rollback();
            }
        }
        return restaurante;
    }
    
    public static void main(String[] args) {
        RestauranteDao dao = new RestauranteDao();
        System.out.println(dao.readOneRestaurante(1));
    }
}
