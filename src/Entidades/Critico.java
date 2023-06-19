package Entidades;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;

@Entity
public class Critico {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
    private int margem;
    private int multiplicador;
    @OneToOne
    private Dano dano;
	
    public Critico() {
	}
    
    public Critico(int margem, int multiplicador) {
		this.margem = margem;
		this.multiplicador = multiplicador;
	}
    
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public int getMargem() {
		return margem;
	}

	public void setMargem(int margem) {
		this.margem = margem;
	}

	public int getMultiplicador() {
		return multiplicador;
	}

	public void setMultiplicador(int multiplicador) {
		this.multiplicador = multiplicador;
	}
        
}
