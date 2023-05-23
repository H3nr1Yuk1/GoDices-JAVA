package Entidades;

import java.util.ArrayList;

public class RolagemDano {
	private String dadoUsado;
	private ArrayList<Integer> danos;
	
	public RolagemDano() {
		
	}
	
	public RolagemDano(String dadoUsado, ArrayList<Integer> danos) {
		this.dadoUsado = dadoUsado;
		this.danos = danos;
	}
	
	public String getDadoUsado() {
		return dadoUsado;
	}
	public void setDadoUsado(String dadoUsado) {
		this.dadoUsado = dadoUsado;
	}
	public ArrayList<Integer> getDanos() {
		return danos;
	}
	public void setDanos(ArrayList<Integer> danos) {
		this.danos = danos;
	}
}
