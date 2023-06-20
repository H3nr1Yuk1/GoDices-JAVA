package Persistencia;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import Entidades.Resultado;

public class ResultadoPersistencia {
	public static boolean criarResultado (Resultado resultado) {
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
    
    public static boolean atualizarResultado (Resultado resultado) {
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
    
    public static boolean removerResultado (Resultado resultado) {
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
    
    public static Resultado procurarResultado (Resultado resultado) {
		EntityManager manager = EntityManagerFactory.getInstance();
		Query proq = manager.createQuery("from Resultado where id = :param");
		proq.setParameter("param", resultado.getId());

		@SuppressWarnings("unchecked")
		List<Resultado> resultados = proq.getResultList();
		
		if(!resultados.isEmpty()) {
			return resultados.get(0);
		}
		return null;
    }
    
    public static Resultado procurarResultadoId (Long id) {
		EntityManager manager = EntityManagerFactory.getInstance();
		Query proq = manager.createQuery("from Resultado where id = :param");
		proq.setParameter("param", id);

		@SuppressWarnings("unchecked")
		List<Resultado> resultados = proq.getResultList();
		
		if(!resultados.isEmpty()) {
			return resultados.get(0);
		}
		
		return null;
    }
}
