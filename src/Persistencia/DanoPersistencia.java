package Persistencia;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import javax.persistence.TypedQuery;

import Entidades.Dano;

public class DanoPersistencia {
	public static boolean criarDano (Dano dano) {
    	try {
    		EntityManager manager = EntityManagerFactory.getInstance();
    		manager.getTransaction().begin();
    		manager.persist(dano);
    		manager.getTransaction().commit();
    		
    		return true;
    	} catch(Exception error) {
    		System.out.println(error.getMessage());
    		return false;
    	}
    }
    
    public static boolean atualizarDano (Dano dano) {
    	try {
    		EntityManager manager = EntityManagerFactory.getInstance();
    		manager.getTransaction().begin();
    		manager.persist(dano);   		
    		manager.getTransaction().commit();
    		return true;
    	} catch(Exception error) {
    		System.out.println(error.getMessage());
    		return false;
    	}
    }
    
    public static boolean removerDano (Dano dano) {
    	try {
    		EntityManager manager = EntityManagerFactory.getInstance();
    		manager.getTransaction().begin();
    		manager.remove(dano);
    		manager.getTransaction().commit();
    		
    		return true;
    	} catch(Exception error) {
    		System.out.println(error.getMessage());
    		return false;
    	}
    }
    
    public static Dano procurarDano (Dano dano) {
		EntityManager manager = EntityManagerFactory.getInstance();
		Query proq = manager.createQuery("from Dano where id = :param");
		proq.setParameter("param", dano.getId());

		@SuppressWarnings("unchecked")
		List<Dano> danos = proq.getResultList();
		
		if(!danos.isEmpty()) {
			return danos.get(0);
		}
		return null;
    }
    
    public static Dano procurarDanoId (int id) {
		EntityManager manager = EntityManagerFactory.getInstance();
		Query proq = manager.createQuery("from Dano where id = :param");
		proq.setParameter("param", id);

		@SuppressWarnings("unchecked")
		List<Dano> danos = proq.getResultList();
		
		if(!danos.isEmpty()) {
			return danos.get(0);
		}
		
		return null;
    }
    
    public static List<Dano> listarDano() {
        EntityManager manager = null;
        try {
            manager = EntityManagerFactory.getInstance();
            
            if (!manager.isOpen()) {
                manager = EntityManagerFactory.getInstance();
            }
            
            manager.getTransaction().begin();

            TypedQuery<Dano> query = manager.createQuery("SELECT d FROM Dano d", Dano.class);
            List<Dano> danos = query.getResultList();

            manager.getTransaction().commit();
            return danos;
        } catch (Exception e) {
            e.printStackTrace();
            if (manager != null && manager.getTransaction().isActive()) {
                manager.getTransaction().rollback();
            }
            return null;
        }
    }



}
