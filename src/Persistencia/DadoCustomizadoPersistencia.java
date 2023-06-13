package Persistencia;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import Entidades.DadoCustomizado;

public class DadoCustomizadoPersistencia {
	public static boolean criarDadoCustomizado (DadoCustomizado dado) {
    	try {
    		EntityManager manager = EntityManagerFactory.getInstance();
    		manager.getTransaction().begin();
    		manager.persist(dado);
    		manager.getTransaction().commit();
    		
    		return true;
    	} catch(Exception error) {
    		System.out.println(error.getMessage());
    		return false;
    	}
    }
    
    public static boolean atualizarDadoCustomizado (DadoCustomizado dado) {
    	try {
    		EntityManager manager = EntityManagerFactory.getInstance();
    		manager.getTransaction().begin();
    		manager.persist(dado);   		
    		manager.getTransaction().commit();
    		return true;
    	} catch(Exception error) {
    		System.out.println(error.getMessage());
    		return false;
    	}
    }
    
    public static boolean removerDadoCustomizado (DadoCustomizado dado) {
    	try {
    		EntityManager manager = EntityManagerFactory.getInstance();
    		manager.getTransaction().begin();
    		manager.remove(dado);
    		manager.getTransaction().commit();
    		
    		return true;
    	} catch(Exception error) {
    		System.out.println(error.getMessage());
    		return false;
    	}
    }
    
    public static DadoCustomizado procurarDadoCustomizado (DadoCustomizado dado) {
		EntityManager manager = EntityManagerFactory.getInstance();
		Query proq = manager.createQuery("from RolagemDano where id = :param");
		proq.setParameter("param", dado.getId());

		@SuppressWarnings("unchecked")
		List<DadoCustomizado> pastas = proq.getResultList();
		
		if(!pastas.isEmpty()) {
			return pastas.get(0);
		}
		return null;
    }
    
    public static DadoCustomizado procurarDadoCustomizado (int id) {
		EntityManager manager = EntityManagerFactory.getInstance();
		Query proq = manager.createQuery("from RolagemDano where id = :param");
		proq.setParameter("param", id);

		@SuppressWarnings("unchecked")
		List<DadoCustomizado> pastas = proq.getResultList();
		
		if(!pastas.isEmpty()) {
			return pastas.get(0);
		}
		
		return null;
    }
    
    public static List<DadoCustomizado> listarDadoCustomizado () {
		EntityManager manager = EntityManagerFactory.getInstance();
		Query proq = manager.createQuery("* from DadoCustomizado");

		@SuppressWarnings("unchecked")
		List<DadoCustomizado> pastas = proq.getResultList();
		
		if(!pastas.isEmpty()) {
			return pastas;
		}
		
		return null;
    }

}
