package Negocios;

import Entidades.DadoDano;
import Persistencia.DadoDanoPersistencia;

public class DadoDanoNegocios {

	public static boolean verificarDadoDano(DadoDano dado) {
		if(DadoDanoPersistencia.listarDadoDano() == null) {
			return true;
		} else {
			for(DadoDano dadoLista : DadoDanoPersistencia.listarDadoDano()) {
				if(dadoLista.getQuantidade() == dado.getQuantidade() && dadoLista.getFaces() == dado.getFaces() && dadoLista.getTipo().equalsIgnoreCase(dado.getTipo())) {
					return false;
				}
			}
			return true;
		}
	}
}
