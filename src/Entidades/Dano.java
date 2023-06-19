package Entidades;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.OneToOne;

@SuppressWarnings("serial")
@Entity
public class Dano implements Serializable {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
    @ManyToMany
	private List<DadoDano> dados;
    @ManyToMany
    private List<Modificador> fixos;
    @OneToOne
	private Critico critico;
	private ArrayList<RolagemDano> rolagemDano;
	
    public Dano() {
	}
    
    public Dano(List<DadoDano> dados, ArrayList<Modificador> fixos, Critico critico) {
		this.dados = dados;
		this.fixos = fixos;
		this.critico = critico;
	}

	public List<DadoDano> getDados() {
		return dados;
	}

	public void setDados(ArrayList<DadoDano> dadosDano) {
		this.dados = dadosDano;
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

	public void setRolagemDano(ArrayList<RolagemDano> rolagemDano) {
		this.rolagemDano = rolagemDano;
	}
	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public ArrayList<RolagemDano> rolarDano(){
		this.rolagemDano = new ArrayList<RolagemDano>();
		ArrayList<Integer> danoCausado = new ArrayList<Integer>();
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
    
	public ArrayList<RolagemDano> rolarDanoCritico(){
		rolagemDano = new ArrayList<RolagemDano>();
		ArrayList<Integer> danoCausado = new ArrayList<Integer>();
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
