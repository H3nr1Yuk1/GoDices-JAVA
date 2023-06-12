package Persistencia;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import Entidades.ResultadoTeste;

public class ResultadoPersistencia {
	public static boolean criarResultadoTeste (ResultadoTeste resultado) {
    	try {
    		EntityManager manager = EntityManagerFactory.getInstance();
    		manager.getTransaction().begin();
    		manager.persist(resultado);
    		manager.getTransaction().commit();
    		
    		return true;
    	} catch(Exception error) {
    		System.out.println(error.getMessage());
    		return false;
    	}
    }
    
    public static boolean atualizarResultadoTeste (ResultadoTeste resultado) {
    	try {
    		EntityManager manager = EntityManagerFactory.getInstance();
    		manager.getTransaction().begin();
    		manager.persist(resultado);   		
    		manager.getTransaction().commit();
    		return true;
    	} catch(Exception error) {
    		System.out.println(error.getMessage());
    		return false;
    	}
    }
    
    public static boolean removerResultadoTeste (ResultadoTeste resultado) {
    	try {
    		EntityManager manager = EntityManagerFactory.getInstance();
    		manager.getTransaction().begin();
    		manager.remove(resultado);
    		manager.getTransaction().commit();
    		
    		return true;
    	} catch(Exception error) {
    		System.out.println(error.getMessage());
    		return false;
    	}
    }
    
    public static ResultadoTeste procurarResultadoTeste (ResultadoTeste resultado) {
		EntityManager manager = EntityManagerFactory.getInstance();
		Query proq = manager.createQuery("from RolagemDano where id = :param");
		proq.setParameter("param", resultado.getId());

		@SuppressWarnings("unchecked")
		List<ResultadoTeste> pastas = proq.getResultList();
		
		if(!pastas.isEmpty()) {
			return pastas.get(0);
		}
		return null;
    }
    
    public static ResultadoTeste procurarRolagemDano (int id) {
		EntityManager manager = EntityManagerFactory.getInstance();
		Query proq = manager.createQuery("from RolagemDano where id = :param");
		proq.setParameter("param", id);

		@SuppressWarnings("unchecked")
		List<ResultadoTeste> pastas = proq.getResultList();
		
		if(!pastas.isEmpty()) {
			return pastas.get(0);
		}
		
		return null;
    }
}
