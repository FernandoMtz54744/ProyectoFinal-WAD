package com.ipn.mx.modelo.dao;


import com.ipn.mx.modelo.entidades.Categoria;
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

public class CategoriaDao {
    
     public List readAll() {
      Session session = HibernateUtil.getSessionFactory().getCurrentSession();
      Transaction transaction = session.getTransaction();
      List resultados = new ArrayList();
      try {
          transaction.begin();
          Query q = session.createQuery("from Categoria", Categoria.class);
          resultados = q.list();
          transaction.commit();
      } catch (HibernateException he) {
          if (transaction != null && transaction.isActive()) {
              transaction.rollback();
          }
      }
      return resultados;
    }
     
     public static void main(String[] args) {
       
    }
}
