package Entidades;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

@Entity
public class DadoCustomizado {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	private String nome;
    @OneToOne
	private Dado teste;
	private ArrayList<Modificador> modificadores;
	@OneToMany
	private List<Resultado> resultado;
	@OneToOne
	private Critico critico;
	@OneToOne
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

	public List<Resultado> getResultado() {
		return resultado;
	}

	public void setResultado(List<Resultado> resultado) {
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
            Resultado ultimoResultado = new Resultado(escolhido, this.modificadores, this.critico, this.dano);
            this.resultado.add(ultimoResultado);
            return ultimoResultado.gerarResultado();
        } else {
            int escolhido = 0;
            this.teste.rolarDado();
            for(int i = 0; i < this.teste.getRolagens().size(); i++){
                if(this.teste.getRolagens().get(i) > escolhido){
                    escolhido = this.teste.getRolagens().get(i);
                }
            }
            Resultado ultimoResultado = new Resultado(escolhido, this.modificadores, this.critico, this.dano);
            this.resultado.add(ultimoResultado);
            return ultimoResultado.gerarResultado();
        }
	}

    public int obterValor(){
    	int num = this.resultado.size() - 1;
    	Resultado ultimoResultado = this.resultado.get(num);
    	return ultimoResultado.gerarResultado();
    }
}
