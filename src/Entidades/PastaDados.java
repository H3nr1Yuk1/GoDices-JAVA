package Entidades;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class PastaDados {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	@Column(unique = true)
    private String nomePasta;
    private List<DadoCustomizado> dadosPasta;
	
    public PastaDados() {
	}
    
    public PastaDados(String nomePasta) {
		this.nomePasta = nomePasta;
	}
    
    public PastaDados(String nomePasta, List<DadoCustomizado> dadosPasta) {
		this.nomePasta = nomePasta;
		this.dadosPasta = dadosPasta;
	}
    
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNomePasta() {
		return nomePasta;
	}

	public void setNomePasta(String nomePasta) {
		this.nomePasta = nomePasta;
	}

	public List<DadoCustomizado> getDadosPasta() {
		return dadosPasta;
	}

	public void setDadosPasta(List<DadoCustomizado> dadosPasta) {
		this.dadosPasta = dadosPasta;
	}    
}
