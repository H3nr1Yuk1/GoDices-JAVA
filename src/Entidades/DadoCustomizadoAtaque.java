package Entidades;

import java.util.ArrayList;

public class DadoCustomizadoAtaque extends DadoCustomizado {
	private ResultadoAtaque resultado;
	private Critico critico;
	private Dano dano;
	
	public DadoCustomizadoAtaque() {
		this.tipo = "Ataque";
	}	

	public DadoCustomizadoAtaque(String nome, D20 teste, ArrayList<Modificador> modificadores, Critico critico, Dano dano) {
		super("Ataque", nome, teste, modificadores);
		this.critico = critico;
		this.dano = dano;
	}

	public ResultadoAtaque getResultado() {
        return this.resultado;
    }
	
	public Critico getCritico() {
		return critico;
	}

	public void setCritico(Critico critico) {
		this.critico = critico;
	}

	public Dano getDano() {
		return dano;
	}

	public void setDano(Dano dano) {
		this.dano = dano;
	}

	public int rolarDado(){
		if(this.teste.getQuantidade() <= 0){
            int escolhido = 20;
            this.teste.rolarDado();
            for(int i = 0; i < this.teste.getRolagens().size(); i++){
                if(this.teste.getRolagens().get(i) < escolhido){
                    escolhido = this.teste.getRolagens().get(i);
                }
            }
            this.resultado = new ResultadoAtaque(escolhido, this.modificadores, this.critico, this.dano);
            return this.resultado.gerarResultado();
        } else {
            int escolhido = 0;
            this.teste.rolarDado();
            for(int i = 0; i < this.teste.getRolagens().size(); i++){
                if(this.teste.getRolagens().get(i) > escolhido){
                    escolhido = this.teste.getRolagens().get(i);
                }
            }
            this.resultado = new ResultadoAtaque(escolhido, this.modificadores, this.critico, this.dano);
            return this.resultado.gerarResultado();
        }
	}

    public int obterValor(){
    	return this.resultado.gerarResultado();
    }
}
