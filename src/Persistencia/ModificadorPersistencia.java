package Persistencia;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import Entidades.Modificador;


public class ModificadorPersistencia {
	public static boolean criarModificador (Modificador modificador) {
    	try {
    		EntityManager manager = EntityManagerFactory.getInstance();
    		manager.getTransaction().begin();
    		manager.persist(modificador);
    		manager.getTransaction().commit();
    		
    		return true;
    	} catch(Exception error) {
    		System.out.println(error.getMessage());
    		return false;
    	}
    }
    
    public static boolean atualizarModificador (Modificador modificador) {
    	try {
    		EntityManager manager = EntityManagerFactory.getInstance();
    		manager.getTransaction().begin();
    		manager.persist(modificador);   		
    		manager.getTransaction().commit();
    		return true;
    	} catch(Exception error) {
    		System.out.println(error.getMessage());
    		return false;
    	}
    }
    
    public static boolean removerModificador (Modificador modificador) {
    	try {
    		EntityManager manager = EntityManagerFactory.getInstance();
    		manager.getTransaction().begin();
    		manager.remove(modificador);
    		manager.getTransaction().commit();
    		
    		return true;
    	} catch(Exception error) {
    		System.out.println(error.getMessage());
    		return false;
    	}
    }
    
    public static Modificador procurarModificador (Modificador modificador) {
		EntityManager manager = EntityManagerFactory.getInstance();
		Query proq = manager.createQuery("from Modificador where nome = :param and valor = :param1");
		proq.setParameter("param", modificador.getNome());
		proq.setParameter("param1", modificador.getValor());

		@SuppressWarnings("unchecked")
		List<Modificador> modificadores = proq.getResultList();
		
		if(!modificadores.isEmpty()) {
			return modificadores.get(0);
		}
		return null;
    }
    
    public static Modificador procurarModificadorId (int id) {
		EntityManager manager = EntityManagerFactory.getInstance();
		Query proq = manager.createQuery("from Modificador where id = :param");
		proq.setParameter("param", id);

		@SuppressWarnings("unchecked")
		List<Modificador> modificadores = proq.getResultList();
		
		if(!modificadores.isEmpty()) {
			return modificadores.get(0);
		}
		
		return null;
    }
    
    public static List<Modificador> listarModificador () {
		EntityManager manager = EntityManagerFactory.getInstance();
		Query proq = manager.createQuery("from Modificador");

		@SuppressWarnings("unchecked")
		List<Modificador> modificadores = proq.getResultList();
		
		if(!modificadores.isEmpty()) {
			return modificadores;
		}
		
		return null;
    }
}
