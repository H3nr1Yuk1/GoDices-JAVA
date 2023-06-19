package Entidades;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.OneToOne;

@SuppressWarnings("serial")
@Entity
public class DadoCustomizado implements Serializable {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private String nome;
    @OneToOne
	private DadoPadrao teste;
    @ManyToMany
	private List<Modificador> modificadores;
    private ArrayList<Resultado> resultado;
	@OneToOne
	private Critico critico;
	@OneToOne
	private Dano dano;

    public DadoCustomizado() {
    }

    public DadoCustomizado(String tipo, String nome, DadoPadrao teste, ArrayList<Modificador> modificadores, Critico critico, Dano dano) {
        this.nome = nome;
        this.teste = teste;
        this.modificadores = modificadores;
        this.critico = critico;
		this.dano = dano;
    }
    
    public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public ArrayList<Resultado> getResultado() {
		return resultado;
	}

	public void setResultado(ArrayList<Resultado> resultado) {
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

	public DadoPadrao getTeste() {
		return teste;
	}

	public void setTeste(DadoPadrao teste) {
		this.teste = teste;
	}

	public List<Modificador> getModificadores() {
		return modificadores;
	}

	public void setModificadores(List<Modificador> modificadores) {
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
