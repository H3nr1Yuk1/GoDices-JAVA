package Persistencia;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import Entidades.DadoDano;


public class DadoDanoPersistencia {
	public static boolean criarDadoDano (DadoDano dado) {
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
    
    public static boolean atualizarDadoDano (DadoDano dado) {
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
    
    public static boolean removerDadoDano (DadoDano dado) {
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
    
    public static DadoDano procurarDadoDano (DadoDano dado) {
    	EntityManager manager = EntityManagerFactory.getInstance();
    	Query proq = manager.createQuery("from Dado where dtype = :param1 and faces = :param2 and quantidade = :param3 and tipo = :param4");
    	proq.setParameter("param1", "DadoDano");
    	proq.setParameter("param2", dado.getFaces());
    	proq.setParameter("param3", dado.getQuantidade());
    	proq.setParameter("param4", dado.getTipo());

    	@SuppressWarnings("unchecked")
    	List<DadoDano> dados = proq.getResultList();
    		
    	if (!dados.isEmpty()) {
    		return dados.get(0);
    	}
    	return null;
    }
    
    public static List<DadoDano> listarDadoDano () {
		EntityManager manager = EntityManagerFactory.getInstance();
		Query proq = manager.createQuery("from Dado where dtype = :param");
		proq.setParameter("param", "DadoDano");

		@SuppressWarnings("unchecked")
		List<DadoDano> dados = proq.getResultList();
		
		if(!dados.isEmpty()) {
			return dados;
		}
		
		return null;
    }
}
