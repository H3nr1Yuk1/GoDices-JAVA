package Entidades;

import java.util.ArrayList;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class DadoCustomizado {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
    @Column(unique = true)
	private String nome;
	private Dado teste;
	private ArrayList<Modificador> modificadores;
	private ResultadoAtaque resultado;
	private Critico critico;
	private Dano dano;

    public DadoCustomizado() {
    }

    public DadoCustomizado(String tipo, String nome, Dado teste, ArrayList<Modificador> modificadores, Critico critico, Dano dano) {
        this.nome = nome;
        this.teste = teste;
        this.modificadores = modificadores;
        this.critico = critico;
		this.dano = dano;
    }
    
    public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public ResultadoAtaque getResultado() {
		return resultado;
	}

	public void setResultado(ResultadoAtaque resultado) {
		this.resultado = resultado;
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

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public Dado getTeste() {
		return teste;
	}

	public void setTeste(Dado teste) {
		this.teste = teste;
	}

	public ArrayList<Modificador> getModificadores() {
		return modificadores;
	}

	public void setModificadores(ArrayList<Modificador> modificadores) {
		this.modificadores = modificadores;
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
