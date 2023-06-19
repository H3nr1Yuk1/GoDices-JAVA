package Entidades;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

@Entity
public class Resultado {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private int valorEscolhido;
	@OneToMany
	private List<Modificador> modificadores;
    @OneToOne
	private Critico critico;
    @OneToOne
    private Dano dano;
    
    public Resultado() {
    }

    public Resultado(int valorEscolhido, List<Modificador> modificadores2, Critico critico, Dano dano) {
        this.valorEscolhido = valorEscolhido;
        this.modificadores = modificadores2;
        this.critico = critico;
        this.dano = dano;
    }
        
    public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public int getValorEscolhido() {
		return valorEscolhido;
	}

	public void setValorEscolhido(int valorEscolhido) {
		this.valorEscolhido = valorEscolhido;
	}

	public List<Modificador> getModificadores() {
		return modificadores;
	}

	public void setModificadores(List<Modificador> modificadores) {
		this.modificadores = modificadores;
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
