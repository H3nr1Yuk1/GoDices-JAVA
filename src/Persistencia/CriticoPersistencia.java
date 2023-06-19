package Persistencia;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import Entidades.Critico;

public class CriticoPersistencia {
	public static boolean criarCritico (Critico critico) {
    	try {
    		EntityManager manager = EntityManagerFactory.getInstance();
    		manager.getTransaction().begin();
    		manager.persist(critico);
    		manager.getTransaction().commit();
    		
    		return true;
    	} catch(Exception error) {
    		System.out.println(error.getMessage());
    		return false;
    	}
    }
    
    public static boolean atualizarCritico (Critico critico) {
    	try {
    		EntityManager manager = EntityManagerFactory.getInstance();
    		manager.getTransaction().begin();
    		manager.persist(critico);   		
    		manager.getTransaction().commit();
    		return true;
    	} catch(Exception error) {
    		System.out.println(error.getMessage());
    		return false;
    	}
    }
    
    public static boolean removerCritico (Critico critico) {
    	try {
    		EntityManager manager = EntityManagerFactory.getInstance();
    		manager.getTransaction().begin();
    		manager.remove(critico);
    		manager.getTransaction().commit();
    		
    		return true;
    	} catch(Exception error) {
    		System.out.println(error.getMessage());
    		return false;
    	}
    }
    
    public static Critico procurarCritico (Critico critico) {
		EntityManager manager = EntityManagerFactory.getInstance();
		Query proq = manager.createQuery("from Critico where margem = :param and multiplicador = :param1");
		proq.setParameter("param", critico.getMargem());
		proq.setParameter("param1", critico.getMultiplicador());

		@SuppressWarnings("unchecked")
		List<Critico> criticos = proq.getResultList();
		
		if(!criticos.isEmpty()) {
			return criticos.get(0);
		}
		return null;
    }
    
    public static Critico procurarCriticoId (int id) {
		EntityManager manager = EntityManagerFactory.getInstance();
		Query proq = manager.createQuery("from Critico where id = :param");
		proq.setParameter("param", id);

		@SuppressWarnings("unchecked")
		List<Critico> criticos = proq.getResultList();
		
		if(!criticos.isEmpty()) {
			return criticos.get(0);
		}
		
		return null;
    }
    
    public static List<Critico> listarCritico () {
		EntityManager manager = EntityManagerFactory.getInstance();
		Query proq = manager.createQuery("from Critico");

		@SuppressWarnings("unchecked")
		List<Critico> criticos = proq.getResultList();
		
		if(!criticos.isEmpty()) {
			return criticos;
		}
		
		return null;
    }
}
