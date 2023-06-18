package Negocios;

import Entidades.Critico;
import Persistencia.CriticoPersistencia;

public class CriticoNegocios {
	public static boolean verificarCritico(Critico criticoEnviado) {
		
		if(CriticoPersistencia.listarCritico() == null) {
			return true;
		} else {
			for(Critico criticos : CriticoPersistencia.listarCritico()) {
				if(criticos.getMargem() == criticoEnviado.getMargem() && criticos.getMultiplicador() == criticoEnviado.getMultiplicador()) {
					return false;
				}
			}
		}
		return true;
	}
}
