package Negocios;

import Entidades.DadoCustomizado;
import Persistencia.DadoCustomizadoPersistencia;

public class DadoCustomizadoNegocios {
    public static boolean verificarDadoExistente(DadoCustomizado dadoEnviado) {    	

    	if(DadoCustomizadoPersistencia.listarDadoCustomizado().equals(null)) {
    		return true;
    	} else {
    		for(DadoCustomizado listaDados : DadoCustomizadoPersistencia.listarDadoCustomizado()) {
        		if(listaDados.getCritico().equals(dadoEnviado.getCritico()) && listaDados.getDano().equals(dadoEnviado.getDano()) && listaDados.getTeste().equals(dadoEnviado.getTeste()) && listaDados.getModificadores().equals(dadoEnviado.getModificadores())) {
        			return false;
        		}
        	}
    	}
    	return true;
    }
}
