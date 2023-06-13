package Persistencia;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import Entidades.PastaDados;

public class PastaDadosPersistencia {
	public static boolean criarPastaDados (PastaDados pasta) {
    	try {
    		EntityManager manager = EntityManagerFactory.getInstance();
    		manager.getTransaction().begin();
    		manager.persist(pasta);
    		manager.getTransaction().commit();
    		
    		return true;
    	} catch(Exception error) {
    		System.out.println(error.getMessage());
    		return false;
    	}
    }
    
    public static boolean atualizarPastaDados (PastaDados pasta) {
    	try {
    		EntityManager manager = EntityManagerFactory.getInstance();
    		manager.getTransaction().begin();
    		manager.persist(pasta);   		
    		manager.getTransaction().commit();
    		return true;
    	} catch(Exception error) {
    		System.out.println(error.getMessage());
    		return false;
    	}
    }
    
    public static boolean removerPastaDados (PastaDados pasta) {
    	try {
    		EntityManager manager = EntityManagerFactory.getInstance();
    		manager.getTransaction().begin();
    		manager.remove(pasta);
    		manager.getTransaction().commit();
    		
    		return true;
    	} catch(Exception error) {
    		System.out.println(error.getMessage());
    		return false;
    	}
    }
    
    public static PastaDados procurarPastaDados (PastaDados pasta) {
		EntityManager manager = EntityManagerFactory.getInstance();
		Query proq = manager.createQuery("from PastaDados where id = :param");
		proq.setParameter("param", pasta.getId());

		@SuppressWarnings("unchecked")
		List<PastaDados> pastas = proq.getResultList();
		
		if(!pastas.isEmpty()) {
			return pastas.get(0);
		}
		return null;
    }
    
    public static PastaDados procurarPastaDadosId (int id) {
		EntityManager manager = EntityManagerFactory.getInstance();
		Query proq = manager.createQuery("from PastaDados where id = :param");
		proq.setParameter("param", id);

		@SuppressWarnings("unchecked")
		List<PastaDados> pastas = proq.getResultList();
		
		if(!pastas.isEmpty()) {
			return pastas.get(0);
		}
		
		return null;
    }
    
    public static List<PastaDados> listarPastaDados () {
		EntityManager manager = EntityManagerFactory.getInstance();
		Query proq = manager.createQuery("from PastaDados");

		@SuppressWarnings("unchecked")
		List<PastaDados> pastas = proq.getResultList();
		
		if(!pastas.isEmpty()) {
			return pastas;
		}
		
		return null;
    }
}
