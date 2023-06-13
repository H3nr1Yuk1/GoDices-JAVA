package Persistencia;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import Entidades.Resultado;

public class ResultadoPersistencia {
	public static boolean criarResultadoTeste (Resultado resultado) {
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
    
    public static boolean atualizarResultadoTeste (Resultado resultado) {
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
    
    public static boolean removerResultadoTeste (Resultado resultado) {
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
    
    public static Resultado procurarResultadoTeste (Resultado resultado) {
		EntityManager manager = EntityManagerFactory.getInstance();
		Query proq = manager.createQuery("from RolagemDano where id = :param");
		proq.setParameter("param", resultado.getId());

		@SuppressWarnings("unchecked")
		List<Resultado> resultados = proq.getResultList();
		
		if(!resultados.isEmpty()) {
			return resultados.get(0);
		}
		return null;
    }
    
    public static Resultado procurarRolagemDano (int id) {
		EntityManager manager = EntityManagerFactory.getInstance();
		Query proq = manager.createQuery("from RolagemDano where id = :param");
		proq.setParameter("param", id);

		@SuppressWarnings("unchecked")
		List<Resultado> resultados = proq.getResultList();
		
		if(!resultados.isEmpty()) {
			return resultados.get(0);
		}
		
		return null;
    }
}
