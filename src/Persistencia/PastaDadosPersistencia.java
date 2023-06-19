package Persistencia;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import Entidades.DadoCustomizado;
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
    
    public static boolean removerDadoDaPasta(PastaDados pasta, DadoCustomizado dado) {
        try {
            pasta.getDadosPasta().remove(dado);
            EntityManager manager = EntityManagerFactory.getInstance();
            manager.getTransaction().begin();
            manager.merge(pasta);
            manager.getTransaction().commit();
            return true;
        } catch (Exception e) {
            e.printStackTrace();
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
    
    public static PastaDados procurarPastaDadosId (Long id) {
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
    
    public static PastaDados procurarPastaDadosIndex(int index) {
    	int i = 1;
    	if(PastaDadosPersistencia.listarPastaDados() != null) {
    		for(PastaDados listaPastas : PastaDadosPersistencia.listarPastaDados()) {
    			if(i == index) {
    				PastaDados pasta = listaPastas;
    				return pasta;
    			}
    			i++;
    		}
    		return null;
    	}
    	return null;
    }

}
