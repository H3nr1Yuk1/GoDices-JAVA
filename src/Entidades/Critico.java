package Entidades;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Critico {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	@Column(unique = true)
    private int margem;
	@Column(unique = true)
    private int multiplicador;
	
    public Critico() {
	}
    
    public Critico(int margem, int multiplicador) {
		this.margem = margem;
		this.multiplicador = multiplicador;
	}
    
	public int getId() {
		return id;
	}

	public void setId(int id) {
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
