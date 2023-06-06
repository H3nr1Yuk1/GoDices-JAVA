package Persistencia;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import Entidades.RolagemDano;

public class RolagemDanoPersistencia {
	public static boolean criarRolagemDano (RolagemDano rolagem) {
    	try {
    		EntityManager manager = EntityManagerFactory.getInstance();
    		manager.getTransaction().begin();
    		manager.persist(rolagem);
    		manager.getTransaction().commit();
    		
    		return true;
    	} catch(Exception error) {
    		return false;
    	}
    }
    
    public static boolean atualizarRolagemDano (RolagemDano rolagem) {
    	try {
    		EntityManager manager = EntityManagerFactory.getInstance();
    		manager.getTransaction().begin();
    		manager.merge(rolagem);
    		
    		//RolagemDano entidade = manager.find(RolagemDano.class, rolagem.getId());
    		//entidade.setDadoUsado(rolagem.getDadoUsado());
    		//entidade.setDanos(rolagem.getDanos());
    		
    		manager.getTransaction().commit();
    		
    		return true;
    	} catch(Exception error) {
    		return false;
    	}
    }
    
    public static boolean removerRolagemDano (RolagemDano rolagem) {
    	try {
    		EntityManager manager = EntityManagerFactory.getInstance();
    		manager.getTransaction().begin();
    		manager.remove(rolagem);
    		manager.getTransaction().commit();
    		
    		return true;
    	} catch(Exception error) {
    		return false;
    	}
    }
    
    public static RolagemDano procurarRolagemDano (RolagemDano rolagem) {
		EntityManager manager = EntityManagerFactory.getInstance();
		manager.getTransaction().begin();
		Query proq = manager.createQuery("from RolagemDano where id = :param");
		proq.setParameter("param", rolagem.getId());

		@SuppressWarnings("unchecked")
		List<RolagemDano> pastas = proq.getResultList();
		
		if(!pastas.isEmpty()) {
			return pastas.get(0);
		}
		
		return null;
    }
    
    public static RolagemDano procurarRolagemDano (int id) {
		EntityManager manager = EntityManagerFactory.getInstance();
		manager.getTransaction().begin();
		Query proq = manager.createQuery("from RolagemDano where id = :param");
		proq.setParameter("param", id);

		@SuppressWarnings("unchecked")
		List<RolagemDano> pastas = proq.getResultList();
		
		if(!pastas.isEmpty()) {
			return pastas.get(0);
		}
		
		return null;
    }
}
