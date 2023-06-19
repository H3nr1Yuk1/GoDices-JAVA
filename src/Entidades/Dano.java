package Entidades;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;

@Entity
public class Dano {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
    private List<Dado> dados;
    private List<Modificador> fixos;
    @OneToOne
	private Critico critico;
	private List<RolagemDano> rolagemDano;
	
    public Dano() {
	}
    
    public Dano(List<Dado> dados, List<Modificador> fixos, Critico critico) {
		this.dados = dados;
		this.fixos = fixos;
		this.critico = critico;
	}

	public List<Dado> getDados() {
		return dados;
	}

	public void setDados(List<Dado> dados) {
		this.dados = dados;
	}

	public List<Modificador> getFixos() {
		return fixos;
	}

	public void setFixos(List<Modificador> fixos) {
		this.fixos = fixos;
	}
		
	public Critico getCritico() {
		return critico;
	}

	public void setCritico(Critico critico) {
		this.critico = critico;
	}
	
	public List<RolagemDano> getRolagemDano() {
		return rolagemDano;
	}

	public void setRolagemDano(List<RolagemDano> rolagemDano) {
		this.rolagemDano = rolagemDano;
	}
	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public List<RolagemDano> rolarDano(){
		this.rolagemDano = new ArrayList<RolagemDano>();
		List<Integer> danoCausado = new ArrayList<Integer>();
        Random random = new Random();

        for(int i = 0; i < this.getDados().size(); i++) {
        	for(int j = 0; j < this.getDados().get(i).getQuantidade(); j++){
                int valor = random.nextInt(this.getDados().get(i).getFaces()) + 1;
                danoCausado.add(valor);
            }
        	String nome = this.getDados().get(i).getClass().getSimpleName();
        	RolagemDano dice = new RolagemDano(nome, danoCausado);
        	danoCausado = new ArrayList<Integer>();
        	this.rolagemDano.add(dice);
        }
        return this.rolagemDano;
	}
    
	public List<RolagemDano> rolarDanoCritico(){
		rolagemDano = new ArrayList<RolagemDano>();
		List<Integer> danoCausado = new ArrayList<Integer>();
        Random random = new Random();
        
        for(int i = 0; i < this.getDados().size(); i++) {
        	for(int j = 0; j < (this.getDados().get(i).getQuantidade() * this.critico.getMultiplicador()); j++){
                int valor = random.nextInt(this.getDados().get(i).getFaces()) + 1;
                danoCausado.add(valor);
            }
        	String nome = this.getDados().get(i).getClass().getSimpleName();
        	RolagemDano dice = new RolagemDano(nome, danoCausado);
        	danoCausado = new ArrayList<Integer>();
        	this.rolagemDano.add(dice);
        }
        return this.rolagemDano;
	}

}
