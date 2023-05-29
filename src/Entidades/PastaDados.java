package Entidades;

import java.util.ArrayList;

public class PastaDados {
    private String nomePasta;
    private ArrayList<DadoCustomizado> dadosPasta;
	
    public PastaDados() {
	}
    
    public PastaDados(String nomePasta) {
		this.nomePasta = nomePasta;
	}
    
    public PastaDados(String nomePasta, ArrayList<DadoCustomizado> dadosPasta) {
		this.nomePasta = nomePasta;
		this.dadosPasta = dadosPasta;
	}

	public String getNomePasta() {
		return nomePasta;
	}

	public void setNomePasta(String nomePasta) {
		this.nomePasta = nomePasta;
	}

	public ArrayList<DadoCustomizado> getDadosPasta() {
		return dadosPasta;
	}

	public void setDadosPasta(ArrayList<DadoCustomizado> dadosPasta) {
		this.dadosPasta = dadosPasta;
	}    
}
