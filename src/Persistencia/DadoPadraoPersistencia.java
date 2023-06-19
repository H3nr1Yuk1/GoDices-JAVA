package Persistencia;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import Entidades.DadoPadrao;


public class DadoPadraoPersistencia {
	public static boolean criarDadoPadrao (DadoPadrao dado) {
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
    
    public static boolean atualizarDadoPadrao (DadoPadrao dado) {
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
    
    public static boolean removerDadoPadrao (DadoPadrao dado) {
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
    
    public static DadoPadrao procurarDadoPadrao (DadoPadrao dado) {
    	EntityManager manager = EntityManagerFactory.getInstance();
    	Query proq = manager.createQuery("from Dado where dtype = :param1 and quantidade = :param2");
    	proq.setParameter("param1", "DadoPadrao");
    	proq.setParameter("param2", dado.getQuantidade());

    	@SuppressWarnings("unchecked")
    	List<DadoPadrao> dados = proq.getResultList();
    		
    	if (!dados.isEmpty()) {
    		return dados.get(0);
    	}
    	return null;
    }
    
    public static List<DadoPadrao> listarDadoPadrao () {
		EntityManager manager = EntityManagerFactory.getInstance();
		Query proq = manager.createQuery("from Dado where dtype = :param");
		proq.setParameter("param", "DadoPadrao");

		@SuppressWarnings("unchecked")
		List<DadoPadrao> dados = proq.getResultList();
		
		if(!dados.isEmpty()) {
			return dados;
		}
		
		return null;
    }
}
