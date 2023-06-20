package Entidades;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;

@Entity
public class Resultado {
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private int valorEscolhido;
    @ManyToMany
    private List<Modificador> modificadores;
        
	public Resultado() {
	}
	
	public Resultado(int valorEscolhido) {
		this.valorEscolhido = valorEscolhido;
	}
		
	public Resultado(int valorEscolhido, List<Modificador> modificadores) {
		this.valorEscolhido = valorEscolhido;
		this.modificadores = modificadores;
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

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public int gerarResultado() {
		int valor = this.valorEscolhido;
		if(this.modificadores == null) {
			this.modificadores = new ArrayList<Modificador>();
		}
        for(int i = 0; i < this.modificadores.size(); i++){
            valor = valor + this.modificadores.get(i).getValor();
        }
        return valor;
	}
    
}
