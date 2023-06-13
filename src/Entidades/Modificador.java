package Entidades;

import javax.persistence.Column;
import javax.persistence.Entity;

@Entity
public class Modificador {
	private String nome;
    @Column(unique = true)
	private int valor;

    public Modificador() {
    }
    
    public Modificador(String nome, int valor) {
        this.nome = nome;
        this.valor = valor;
    }

    public String getNome() {
        return nome;
    }
    public void setNome(String nome) {
        this.nome = nome;
    }
    public int getValor() {
        return valor;
    }
    public void setValor(int valor) {
        this.valor = valor;
    }
    
}
