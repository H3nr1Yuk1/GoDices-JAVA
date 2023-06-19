package Negocios;

import Entidades.Modificador;
import Persistencia.ModificadorPersistencia;

public class ModificadorNegocios {
	public static boolean verificarModificador(Modificador modEnviado) {
		if(ModificadorPersistencia.listarModificador() == null) {
			return true;
		} else {
			for(Modificador modLista : ModificadorPersistencia.listarModificador()) {
				if(modLista.getNome().equalsIgnoreCase(modEnviado.getNome()) && modLista.getValor() == modEnviado.getValor()) {
					return false;
				}
			}
			return true;
		}
	}
}
