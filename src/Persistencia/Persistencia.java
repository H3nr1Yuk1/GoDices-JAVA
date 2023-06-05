package Persistencia;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import Entidades.PastaDados;

public class Persistencia {
    public static boolean criarPasta (PastaDados pasta) {
    	try {
    		EntityManager manager = EntityManagerFactory.getInstance();
    		manager.getTransaction().begin();
    		manager.persist(pasta);
    		manager.getTransaction().commit();
    		
    		return true;
    	} catch(Exception error) {
    		return false;
    	}
    }
    
    public static boolean atualizarPasta (PastaDados pasta) {
    	try {
    		EntityManager manager = EntityManagerFactory.getInstance();
    		manager.getTransaction().begin();
    		manager.merge(pasta);
    		manager.getTransaction().commit();
    		
    		return true;
    	} catch(Exception error) {
    		return false;
    	}
    }
    
    public static boolean removerPasta (PastaDados pasta) {
    	try {
    		EntityManager manager = EntityManagerFactory.getInstance();
    		manager.getTransaction().begin();
    		manager.remove(pasta);
    		manager.getTransaction().commit();
    		
    		return true;
    	} catch(Exception error) {
    		return false;
    	}
    }
    
    public static PastaDados procurarPasta (PastaDados pasta) {
    	try {
    		EntityManager manager = EntityManagerFactory.getInstance();
    		manager.getTransaction().begin();
    		Query proq = manager.createQuery("from PASTADADOS where id = :param");
    		proq.setParameter("param", pasta.getId());
    		
    		List<PastaDados> pastas = proq.getResultList();
    		
    		return pastas.get(0);
    	} catch(Exception error) {
    		return null;
    	}
    }
}
