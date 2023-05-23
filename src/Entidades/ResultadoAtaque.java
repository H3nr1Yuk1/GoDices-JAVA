package Entidades;

import java.util.ArrayList;

public class ResultadoAtaque extends Resultado {
	private Critico critico;
	private Dano dano;
	
	public ResultadoAtaque() {
		super();
	}

	public ResultadoAtaque(int valorEscolhido, ArrayList<Modificador> modificadores, Critico critico, Dano dano) {
		super("Ataque", valorEscolhido, modificadores);
		this.critico = critico;
		this.dano = dano;
	}
	
	public int gerarResultado() {
		int valor = 0;
        valor = valor + this.valorEscolhido;
        for(int i = 0; i < this.modificadores.size(); i++){
            valor = valor + modificadores.get(i).getValor();
        }
        return valor;
	}
	
	public boolean verificarCritico() {
		if(this.valorEscolhido >= this.critico.getMargem()) {
			return true;
		}
		return false;
	}
}