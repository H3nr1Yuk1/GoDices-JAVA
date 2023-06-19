package Entidades;

import java.util.List;

import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class RolagemDano {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private String dadoUsado;
	@ElementCollection
	private List<Integer> danos;
	
	public RolagemDano() {
		
	}
	
	public RolagemDano(String dadoUsado, List<Integer> danos) {
		this.dadoUsado = dadoUsado;
		this.danos = danos;
	}
		
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getDadoUsado() {
		return dadoUsado;
	}
	public void setDadoUsado(String dadoUsado) {
		this.dadoUsado = dadoUsado;
	}
	public List<Integer> getDanos() {
		return danos;
	}
	public void setDanos(List<Integer> danos) {
		this.danos = danos;
	}
}
