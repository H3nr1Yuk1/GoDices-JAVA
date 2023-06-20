package Persistencia;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import javax.persistence.TypedQuery;

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
		Query proq = manager.createQuery("from DadoCustomizado where id = :param");
		proq.setParameter("param", dado.getId());

		@SuppressWarnings("unchecked")
		List<DadoCustomizado> dadosCustom = proq.getResultList();
		
		if(!dadosCustom.isEmpty()) {
			return dadosCustom.get(0);
		}
		return null;
    }
    
    public static DadoCustomizado procurarDadoCustomizadoId (Long id) {
		EntityManager manager = EntityManagerFactory.getInstance();
		Query proq = manager.createQuery("from DadoCustomizado where id = :param");
		proq.setParameter("param", id);

		@SuppressWarnings("unchecked")
		List<DadoCustomizado> dadosCustom = proq.getResultList();
		
		if(!dadosCustom.isEmpty()) {
			return dadosCustom.get(0);
		}
		
		return null;
    }
    
    public static DadoCustomizado procurarDadoCustomizadoNome (String nome) {
		EntityManager manager = EntityManagerFactory.getInstance();
		Query proq = manager.createQuery("from DadoCustomizado where nome = :param");
		proq.setParameter("param", nome);

		@SuppressWarnings("unchecked")
		List<DadoCustomizado> dadosCustom = proq.getResultList();
		
		if(!dadosCustom.isEmpty()) {
			return dadosCustom.get(0);
		}
		
		return null;
    }
    
    public static List<DadoCustomizado> listarDadoCustomizado() {
        EntityManager manager = null;
        try {
            manager = EntityManagerFactory.getInstance();
            TypedQuery<DadoCustomizado> query = manager.createQuery("SELECT dc FROM DadoCustomizado dc", DadoCustomizado.class);
            List<DadoCustomizado> dadosCustom = query.getResultList();

            return dadosCustom;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }



}
