package Negocios;

import Entidades.Dano;
import Persistencia.DanoPersistencia;

public class DanoNegocios {
	public static boolean verificarDano(Dano danoEnviado) {
		
		if(DanoPersistencia.listarDano() == null) {
			return true;
		} else {
			for(Dano danos : DanoPersistencia.listarDano()) {
				if(danos.getCritico().equals(danoEnviado.getCritico()) && danos.getDados().equals(danoEnviado.getDados()) && danos.getFixos().equals(danoEnviado.getFixos())) {
					return false;
				}
			}
			return true;
		}
	}
}
