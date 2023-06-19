package Negocios;

import Entidades.DadoPadrao;
import Persistencia.DadoPadraoPersistencia;

public class DadoPadraoNegocios {

	public static boolean verificarDadoPadrao(DadoPadrao dado) {
		if(DadoPadraoPersistencia.listarDadoPadrao() == null) {
			return true;
		} else {
			for(DadoPadrao dadoLista : DadoPadraoPersistencia.listarDadoPadrao()) {
				if(dadoLista.getQuantidade() == dado.getQuantidade() && dadoLista.getFaces() == dado.getFaces()) {
					return false;
				}
			}
			return true;
		}
	}
}
