package Entidades;

import java.util.ArrayList;

public abstract class DadoCustomizado {
    String tipo;
    String nome;
    D20 teste;
    ArrayList<Modificador> modificadores;

    public DadoCustomizado() {
    }

    public DadoCustomizado(String tipo, String nome, D20 teste, ArrayList<Modificador> modificadores) {
        this.tipo = tipo;
        this.nome = nome;
        this.teste = teste;
        this.modificadores = modificadores;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public D20 getTeste() {
        return teste;
    }

    public void setTeste(D20 teste) {
        this.teste = teste;
    }

    public ArrayList<Modificador> getModificadores() {
        return modificadores;
    }

    public void setModificadores(ArrayList<Modificador> modificadores) {
        this.modificadores = modificadores;
    }

    public abstract int rolarDado();

    public abstract int obterValor();
}
