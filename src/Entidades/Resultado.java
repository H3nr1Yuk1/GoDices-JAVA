package Entidades;

import java.util.ArrayList;

public abstract class Resultado {
    String tipo;
    int valorEscolhido;
    ArrayList<Modificador> modificadores;
    
    public Resultado() {
    }

    public Resultado(String tipo, int valorEscolhido, ArrayList<Modificador> modificadores) {
        this.tipo = tipo;
        this.valorEscolhido = valorEscolhido;
        this.modificadores = modificadores;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public int getValorEscolhido() {
        return valorEscolhido;
    }

    public void setValorEscolhido(int valorEscolhido) {
        this.valorEscolhido = valorEscolhido;
    }

    public ArrayList<Modificador> getModificadores() {
        return modificadores;
    }

    public void setModificadores(ArrayList<Modificador> modificadores) {
        this.modificadores = modificadores;
    }
    
    public abstract int gerarResultado();
}
